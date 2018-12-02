package com.example.demo;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.beans.factory.config.YamlProcessor.MatchCallback;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.yaml.SpringProfileDocumentMatcher;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.resolver.Resolver;

public class MyYaml implements PropertySourceLoader{
	@Override
	public String[] getFileExtensions() {
		return new String[] { "yml", "yaml" };
	}

	@Override
	public PropertySource<?> load(String name, Resource resource, String profile)
			throws IOException {
		if (ClassUtils.isPresent("org.yaml.snakeyaml.Yaml", null)) {
			Processor processor = new Processor(resource, profile);
			Map<String, Object> source = processor.process();
			if (!source.isEmpty()) {
				Object version = source.get("service.version");
				if (version != null) {
					System.setProperty("service.version", version.toString());
				}
				System.out.println(11);
				return new MapPropertySource(name, source);
			}
		}
		return null;
	}

	/**
	 * {@link YamlProcessor} to create a {@link Map} containing the property values.
	 * Similar to {@link YamlPropertiesFactoryBean} but retains the order of entries.
	 */
	private static class Processor extends YamlProcessor {

		Processor(Resource resource, String profile) {
			if (profile == null) {
				setMatchDefault(true);
				setDocumentMatchers(new SpringProfileDocumentMatcher());
			}
			else {
				setMatchDefault(false);
				setDocumentMatchers(new SpringProfileDocumentMatcher(profile));
			}
			setResources(resource);
		}

		@Override
		protected Yaml createYaml() {
			return new Yaml(new StrictMapAppenderConstructor(), new Representer(),
					new DumperOptions(), new Resolver() {
						@Override
						public void addImplicitResolver(Tag tag, Pattern regexp,
								String first) {
							if (tag == Tag.TIMESTAMP) {
								return;
							}
							super.addImplicitResolver(tag, regexp, first);
						}
					});
		}

		public Map<String, Object> process() {
			final Map<String, Object> result = new LinkedHashMap<String, Object>();
			process(new MatchCallback() {
				@Override
				public void process(Properties properties, Map<String, Object> map) {
					result.putAll(getFlattenedMap(map));
				}
			});
			return result;
		}

	}
}
