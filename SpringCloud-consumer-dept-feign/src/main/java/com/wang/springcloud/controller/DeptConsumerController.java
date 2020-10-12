package com.wang.springcloud.controller;

import com.wang.springcloud.pojo.Dept;
import com.wang.springcloud.service.DeptClientService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ApiModel("Consumer Controller")
@RestController
public class DeptConsumerController {

    @Autowired
    private DeptClientService service = null;

    @ApiOperation("通过部门编号查询一个部门")
    @RequestMapping(value = "/consumer/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") @ApiParam("部门编号") Long id) {
        return this.service.queryById(id);
    }

    @ApiOperation("通过部门名称添加一个部门")
    @RequestMapping(value = "/consumer/dept/add", method = RequestMethod.POST)
    public boolean add(@ApiParam("部门的名称") Dept dept){
        return this.service.addDept(dept);
    }

    @ApiOperation("查询全部的部门")
    @RequestMapping(value = "consumer/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return this.service.queryAll();
    }

}
