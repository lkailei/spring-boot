package com.kayleoi.springbootdatajdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author kay三石
 * @date:2019/6/30
 */
@Controller
public class JdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

//    execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句；
//
//    update方法及batchUpdate方法：update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批处理相关语句；
//
//    query方法及queryForXXX方法：用于执行查询相关语句；
//
//    call方法：用于执行存储过程、函数相关语句。

    @ResponseBody
    @GetMapping("/query")
    public Map<String,Object> map(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM department");
        return list.get(0);
    }
}
