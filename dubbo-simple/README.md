# 目录介绍 
这是eclipse创建的maven项目，主要目录文件如下(cmd命令：tree DubboDemo /f >D:/tt.txt)：
DUBBODEMO
│  pom.xml
│  │  
├─dubbo-api
│  │  pom.xml 
│  │  
│  ├─src
│  │  └─main
│  │      ├─java
│  │      │  └─com
│  │      │      └─alibaba
│  │      │          └─dubbo
│  │      │              └─demo
│  │      │                      DemoService.java
│  │      │                      
│  │      └─resources
│  │              index.html
├─dubbo-consumer
│  │  pom.xml
│  │    
│  ├─src
│  │  └─main
│  │      ├─java
│  │      │  └─com
│  │      │      └─alibaba
│  │      │          └─dubbo
│  │      │              └─consumer
│  │      │                      Consumer.java
│  │      │                      
│  │      └─resources
│  │          │  log4j.properties
│  │          │  
│  │          └─META-INF
│  │              └─spring
│  │                      dubbo-demo-consumer.xml
└─dubbo-provider
    │  pom.xml
    │  
    ├─src
    │  └─main
    │      ├─java
    │      │  └─com
    │      │      └─alibaba
    │      │          └─dubbo
    │      │              └─demo
    │      │                  └─impl
    │      │                          DemoServiceImpl.java
    │      │                          Provider.java
    │      │                          
    │      └─resources
    │          │  log4j.properties
    │          │  
    │          └─META-INF
    │              └─spring
    │                      dubbo-demo-provider.xml
# Dubbo入门Demo
要了解Dubbo，自然要搭建一个简单的Demo实现。本文采用Dubbo与Zookeeper、Spring框架的整合。
主要是以下几个步骤：
1. 安装Zookeeper,启动；
2. 创建MAVEN项目，构建Dubbo+Zookeeper+Spring实现的简单Demo；
3. 安装Dubbo-admin，实现监控。

## 1 Zookeeper介绍与安装

本Demo中的Dubbo注册中心采用的是Zookeeper。为什么采用Zookeeper呢？
具体的安装方法在此不一一叙述，可参考博文：
http://blog.csdn.net/tlk20071/article/details/52028945

安装完成后，进入到bin目录，并且启动zkServer.cmd，这个脚本中会启动一个java进程：
(注：需要先启动zookeeper后，后续dubbo demo代码运行才能使用zookeeper注册中心的功能) 

## 2 创建MAVEN项目

项目结构：
主要分三大模块：
dubbo-api : 存放公共接口；
dubbo-consumer :　调用远程服务；
dubbo-provider : 提供远程服务。 

下面将详细叙述代码构建过程。
1) 首先构建MAVEN项目，导入所需要的jar包依赖。
需要导入的有spring, dubbo, zookeeper等jar包。
(详情参看后面提供的项目代码)

2)创建dubbo-api的MAVEN项目(有独立的pom.xml，用来打包供提供者消费者使用)。
在项目中定义服务接口：该接口需单独打包，在服务提供方和消费方共享。 

3)创建dubbo-provider的MAVEN项目(有独立的pom.xml，用来打包供消费者使用)。
实现公共接口，此实现对消费者隐藏：

需加入公共接口所在的依赖 

用Spring配置声明暴露服务

启动远程服务：

启动Consumer,调用远程服务：

5）运行项目，先确保provider已被运行后再启动consumer模块：
运行提供者： 

消费者成功调用提供者所提供的远程服务： 

当然，这只是一个模拟的项目，实际中有多提供者多消费者情况，比这要复杂的多，当然只有这样才能体现dubbo的特性。

# Dubbo管理控制台介绍

4)创建dubbo-consumer的MAVEN项目(可以有多个consumer，但是需要配置好)。
调用所需要的远程服务：

通过Spring配置引用远程服务：

管理控制台功能

下载dubbo-admin，可自行根据网上介绍安装。大致做法就是将dubbo-admin中 的某个文件夹内容替换到tomcat的conf中，再运行tomcat即可。但我在实际操作中发现JDK8无法运行，后来找到一个JDK8可以实现的dubbo-admin版本，下载地址：http://www.itmayun.com/it/files/226631678709806/resource/901920001882583/1.html。

成功开启输入用户名密码root后，即可进入控制台首页查看消费者提供者情况：
查看提供者： 

查看消费者： 

很遗憾，官方Dubbo网址早已不维护了，也出现了很多更新的Dubbo，比如当当网的Dubbox，可以自行了解。

整个项目的代码已经上传到我的github上 https://github.com/wangkun268/DubboDemo ，欢迎查看。
(github上项目中的图片为博客中内容，可全部删除)。
