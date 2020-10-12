package com.wang.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wang.springcloud.pojo.Dept;
import com.wang.springcloud.service.DeptService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//提供RestFul服务!
@RestController
@ApiModel("Provider Controller")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //注册DiscoveryClient, 注意此时要导入的包是SpringCloud的
    //获取一些配置的信息, 得到具体的微服务
    @Autowired
    private DiscoveryClient client;

    @ApiOperation("通过部门编号获得一个部门的信息")
    @GetMapping("/dept/get/{id}")
    //只要失败, 调用对应的方法
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") @ApiParam("部门的id") Long id){
        Dept dept = deptService.queryById(id);

        //如果id不存在, 会返回null, 这里抛出异常
        if (dept == null) {
            throw new RuntimeException("id => " + id + " , 不存在该用户, 或者信息无法找到!");
        }

        return dept;
    }

    //备选方法 ==> 当查询的id不存在, 创建对应id的对象, name字段放入提示信息, 失败时返回我们创建的对象!
    public Dept hystrixGet(@PathVariable("id") @ApiParam("部门的id") Long id){
        //这里可以用链式编程, 是因为我们在pojo的lombok中开启了链式编程的支持
        return new Dept()
                .setDeptno(id)
                .setDname("id => " + id + " , 没有对应的信息, null ---- @Hystrix")
                .setDb_source("This database is not exist in Mysql");
    }

    //注册进来的微服务, 获得一些信息(得到配置文件中的info的信息)
    @ApiOperation("微服务的信息")
    @GetMapping("/dept/discovery")
    public Object discovery() {
        //获取微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discovery => services: " + services);

        //得到一个具体的微服务, 通过具体的微服务ID, applicationName(即为在配置文件中配置的该SpringBoot的名字!)
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");

        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost() + "\t" +
                    instance.getPort() + "\t" +
                    instance.getUri() + "\t" +
                    instance.getServiceId()
            );
        }

        //返回这个client就可以了
        return this.client;
    }

}
