package com.wang.springcloud.dao;

import com.wang.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DeptService {

    boolean addDept(Dept dept);

    Dept queryById(Long deptno);

    List<Dept> queryAll();
}