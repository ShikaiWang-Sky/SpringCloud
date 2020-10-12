package com.wang.springcloud.service;

import com.wang.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//value为微服务的名称
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory = DeptClientFallbackFactory.class)
@Service
public interface DeptClientService {

    @GetMapping("/dept/get/{id}")
    Dept queryById(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    List<Dept> queryAll();

    @PostMapping("/dept/add")
    Boolean addDept(Dept dept);

}
