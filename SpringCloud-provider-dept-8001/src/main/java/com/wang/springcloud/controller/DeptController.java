package com.wang.springcloud.controller;

import com.wang.springcloud.pojo.Dept;
import com.wang.springcloud.service.DeptService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//提供RestFul服务!
@RestController
@ApiModel("Provider Controller")
public class DeptController {

    @Autowired
    private DeptService deptService;

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
}
