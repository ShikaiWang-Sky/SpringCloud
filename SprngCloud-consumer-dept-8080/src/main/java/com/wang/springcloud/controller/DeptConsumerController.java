package com.wang.springcloud.controller;

import com.wang.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {
    //消费者, 不应该有service层!(为了实现解耦)
    //RestFul模板 ==> RestTemplate, 里面有很多方法供我们直接调用, 需要注册到Spring中
    //远程调用provider的url

    //在ConfigBean中注册了, 这里可以直接自动装配(AutoWired是按照类型自动装配)
    //RestTemplate的方法 ==> url, 请求的实体(request), 响应的类型(response)Class<T>
    //RestTemplate实质上就是提供多种便捷访问远程http服务的方法, 是一个简单的RestFul服务模板
    @Autowired
    private RestTemplate restTemplate;

    //这里去http://localhost:8001/dept/{id}这个url拿数据
    //由于前缀是固定的, 我们这里利用常量写死
    private static final String REST_URL_PREFIX = "http://localhost:8001";
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        //由于我们在provider中的list是get方法, 这里调用getForObject方法
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @RequestMapping("consumer/dept/list")
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

}
