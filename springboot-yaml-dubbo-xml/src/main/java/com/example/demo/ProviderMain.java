package com.example.demo;

import com.alibaba.dubbo.demo.impl.ProviderCenter;

public class ProviderMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("service.version", "1.3");
		ProviderCenter.start();
		while(true){
			Thread.sleep(1000);
		}
	}

}
