package com.wang.springcloud.controller;

import com.wang.springcloud.pojo.Dept;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@ApiModel("Consumer Controller")
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
    @ApiOperation("通过部门编号查询一个部门")
    @RequestMapping(value = "/consumer/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") @ApiParam("部门编号") Long id) {
        //由于我们在provider中的list是get方法, 这里调用getForObject方法
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @ApiOperation("通过部门名称添加一个部门")
    @RequestMapping(value = "/consumer/dept/add", method = RequestMethod.POST)
    public boolean add(@ApiParam("部门的名称") Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @ApiOperation("查询全部的部门")
    @RequestMapping(value = "consumer/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

}
