- Title: 在项目中配置 Swagger
- Category:
- Tag: Java
- Author: Kahle
- Creation Time: 2018-11-24T10:13:35.666+0800
- Update Time: 2018-11-24T10:13:35.666+0800
- Original:
- Reference:
    - [Swagger 使用指南](https://blog.csdn.net/sanyaoxu_2/article/details/80555328)
    - [Spring Boot 整合 Swagger2](https://www.cnblogs.com/jtlgb/p/8532433.html)
    - [Spring Boot swagger-ui.html 404](https://blog.csdn.net/xtj332/article/details/80595768)
    - [Spring Boot 使用 Swagger2 出现 Unable to infer base url](https://blog.csdn.net/jiang948/article/details/79914446)

---


### 导语

什么是 Swagger ？粗浅的理解的话，Swagger 就是 Java Web 项目中 http 接口文档的一个工具。用于给“用户”提供对应的接口文档的展示的工具把。为什么选择 Swagger ？首先市面上貌似还没有其他的用于生成 http 接口文档的工具。Java doc 也仅仅是用于 Java 类的文档的生成的。注意，在本文中不会出现任何 Swagger 的高级玩法，只是最基本的入门教程。


### 正文

#### 1. Maven 导包

如果你用的是 Maven ，可以直接在 pom 文件中增加以下内容。

```
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

检查了一下，貌似暂时没有更佳好的导包方案。


#### 2. Swagger 配置类

在可以被扫描到的包下，创建 Swagger 配置类，用于配置 Swagger 的信息，大致内容如下。

```
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
/**
 * Swagger2 配置类
 * 通过 @Configuration 注解，让 Spring 来加载该类配置。
 * 再通过 @EnableSwagger2 注解来启用 Swagger2 功能。
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    /**
     * 创建API应用
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.createApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.swaggerTest.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该 API 的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     */
    private ApiInfo createApiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot 中使用 Swagger2 构建 RESTful APIs")
                .description("Spring Boot 中使用 Swagger2 构建 RESTful APIs.")
                .termsOfServiceUrl("http://www.swagger.com")
                .contact("swagger")
                .version("1.0")
                .build();
    }

}
```

只要确保这个类能够被扫描到，并且托管到 Spring 容器中，大致来说 Swagger 就配置成功了。当然由于不同的项目配置，势必会出现一些坑。


#### 3. 常用 Swagger 注解简介

Swagger 的方便之处就是在于可以通过注解，来描述接口信息，然后一启动就可以看到对应的接口文档了。

```
// Controller 类的描述信息
@Api(value = "XXXXX")

// Controller 方法，即接口的描述信息
@ApiOperation(value="XXX", notes="XXXXX")

// Controller 方法中参数的描述信息
@ApiParam("XXXXX")

// Model 对象的描述信息
@ApiModel("XXXXX")

// Model 对象中字段的描述信息
@ApiModelProperty(value="XXXXX")
```

常用的差不多就这些了。


#### 4. 踩过的坑

由于项目中重写了 WebMvcConfigurationSupport 类，导致 Swagger 页面出现大大的 404 错误，解决方案如下。

```
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    /**
     * 这个方法解决了 Swagger 页面出现的 404 错误
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    /**
     * 这个方法是为了解决 跨域 所写的，和 Swagger 无关。
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }

}
```

访问 Swagger 的页面出现“Unable to infer base url ... ...”的错误，原因为忘记加“@EnableSwagger2”注解，或者这个注解没有被扫描到，只要能被扫描到了，即可解决。


