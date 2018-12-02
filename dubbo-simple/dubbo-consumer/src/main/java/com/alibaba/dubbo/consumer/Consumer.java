package com.alibaba.dubbo.consumer;

import com.alibaba.dubbo.demo.DemoService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author www.mincode.cn
 *
 */
public class Consumer {
//    public static void main(String[] args) {
//        //测试常规服务
//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("consumer.xml");
//        context.start();
//        System.out.println("consumer start");
//        DemoService demoService = context.getBean(DemoService.class);
//        System.out.println("consumer");
//        System.out.println(demoService.getPermissions(1L));
//    }

	public static void main(String[] args) {
        //Prevent to get IPV6 address,this way only work in debug mode
        //But you can pass use -Djava.net.preferIPv4Stack=true,then it work well whether in debug mode or not
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("service.version", "1.1");
        @SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService"); // get remote service proxy

        while (true) {
            try {
                Thread.sleep(1000);
                String hello = demoService.sayHello(" www.mincode.cn "); // call remote method
                System.out.println(hello); // get result

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

    }
}
