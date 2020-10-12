package com.wang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//开启Hystrix Dashboard
@EnableHystrixDashboard
public class DeptConsumerDashboard_9002 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerDashboard_9002.class, args);
    }
}
