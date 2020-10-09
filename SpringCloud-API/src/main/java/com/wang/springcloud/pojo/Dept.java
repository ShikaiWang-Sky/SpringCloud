package com.wang.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
//支持链式写法
@Accessors(chain = true)
public class Dept implements Serializable {

    private Long deptno;
    private String dname;
    //这个数据存在哪个数据库的字段 ==> 微服务, 一个服务对应一个数据库, 同一个数据可能存在不同的数据库
    private String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }
}
