package com.alibaba.dubbo.demo;

import java.util.List;

/**
 * @author www.mincode.cn
 *
 */
public interface DemoService {
	String sayHello(String name);
    List<String> getPermissions(Long id);
}
