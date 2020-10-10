package com.wang.springcloud.controller;

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

    @ApiOperation("通过部门的名字添加一个部门")
    @PostMapping("/dept/add")
    public boolean addDept(@RequestBody Dept dept){
        return deptService.addDept(dept);
    }

    @ApiOperation("通过部门编号获得一个部门的信息")
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") @ApiParam("部门的id") Long id){
        return deptService.queryById(id);
    }

    @ApiOperation("查询全部的部门")
    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
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
