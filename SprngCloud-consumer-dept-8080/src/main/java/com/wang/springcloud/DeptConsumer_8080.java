package com.wang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//Ribbon 和 Eureka 整合以后, 客户端可以直接调用, 不用关心IP地址和端口号
@SpringBootApplication
@EnableEurekaClient
public class DeptConsumer_8080 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_8080.class, args);
    }
}
