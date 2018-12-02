package com.alibaba.dubbo.demo.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author www.mincode.cn
 *
 */
public class Provider {
//	public static void main(String[] args) throws IOException {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
//		System.out.println(context.getDisplayName() + ": here");
//		context.start();
//		System.out.println("服务已经启动...");
//		System.in.read();
//	}
	
	public static void main(String[] args) throws Exception {
        //Prevent to get IPV6 address,this way only work in debug mode
        //But you can pass use -Djava.net.preferIPv4Stack=true,then it work well whether in debug mode or not
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("service.version", "1.1");
        @SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-provider.xml"});
        context.start();
        
        System.out.println("服务已经启动...");
        System.in.read(); // press any key to exit
    }
}
