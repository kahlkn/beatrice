- Title: 解决 Feign Client 对象是必须的但是却找不到
- Category:
- Tag: Spring Cloud
- Author: Kahle
- Creation Time: 2018-07-25T09:21:43.666+0800
- Update Time: 2018-07-25T09:21:43.666+0800
- Original:
- Reference:
    - [Spring Cloud配置遇到的bean not found问题](https://www.jianshu.com/p/551c7c251f91)

---


### 导语

Spring Cloud 在启动时出现：“Field userFeignClient in cn.tearn.testa.demoa.DemoaController required a bean of type 'cn.tearn.demobsdk.restful.UserFeignClientB' that could not be found.”这样的错误。由于本文章事解决后整理的，所以这个错误信息是 Copy 了引用的这篇文章的，错误内容总体来说是差不多的。就是 Feign 客户端的某个类是必须的，但是又找不到，所以出现了这样的错误。


### 正文

其主要原因大致是这个 Feign Client 对象没有被托管到 spring 容器中。由于 Feign Client 对象上的注解为“@FeignClient”所以会出现在启动类上加“@ComponentScans”或者“@ComponentScan”并不能扫描到 Feign Client 对象并托管到容器中。

这时应该使用注解“@EnableFeignClients({"Feign 客户端的包名"})”。这样可以将 Feign Client 对象扫描到并托管到 Spring 容器中。当然还有另外一个方案，在 Feign Client 对象所在的包中，创建一个 AutoConfiguration 性质的类，用来将 Feign Client 对象托管到 Spring 容器中。然后再在 /resources/META-INF 目录下创建“spring.factories”文件，文件中写入以下内容。

```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.domain.configuration.Xxxx1AutoConfiguration,\
com.domain.configuration.Xxxx2AutoConfiguration,\
com.domain.configuration.Xxxx3AutoConfiguration
```

这样也可以达到在 Spring 启动后，将对应的 Feign 客户端 托管到 Spring 容器中。



