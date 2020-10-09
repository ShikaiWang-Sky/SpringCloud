package com.wang.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

//设置为SpringBoot注解
@Configuration
//启用Swagger2
@EnableSwagger2
//指定Swagger扫描的API的路径
@ComponentScan(basePackages = "com.wang.springcloud.controller")
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .groupName("SpringCloud-Provider_Api")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("wang", "https://www.cnblogs.com/wang-sky/", "715180879@qq.com");

        return new ApiInfo(
                "Api for SpringCloud",
                "这是我的SpringCloud项目的Api",
                "V 1.0",
                "https://www.cnblogs.com/wang-sky/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }
}
