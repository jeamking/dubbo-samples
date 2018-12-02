package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.alibaba.dubbo.consumer.ConsumerCenter;
import com.alibaba.dubbo.demo.impl.ProviderCenter;

@SpringBootApplication
public class HelloworldApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HelloworldApplication.class, args);
				ClassLoader cl = HelloworldApplication.class.getClassLoader();
		new Thread() {
			public void run() {
				try {
					ProviderCenter.start();
					while(true) {
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}.start();
		Thread.sleep(2000);
		ConsumerCenter.testSayHello();		
	}
}
