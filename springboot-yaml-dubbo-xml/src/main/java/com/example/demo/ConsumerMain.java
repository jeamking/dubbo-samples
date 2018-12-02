package com.example.demo;

import com.alibaba.dubbo.consumer.ConsumerCenter;

public class ConsumerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("service.version", "1.3");
		ConsumerCenter.testSayHello();
	}

}
