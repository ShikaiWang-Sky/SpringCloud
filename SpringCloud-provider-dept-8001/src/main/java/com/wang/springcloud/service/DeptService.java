package com.wang.springcloud.service;

import com.wang.springcloud.pojo.Dept;

import java.util.List;

public interface DeptService {

    boolean addDept(Dept dept);

    Dept queryById(Long deptno);

    List<Dept> queryAll();
}