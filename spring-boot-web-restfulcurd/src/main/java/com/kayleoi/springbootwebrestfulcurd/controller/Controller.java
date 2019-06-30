package com.kayleoi.springbootwebrestfulcurd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @Author kay三石
 * @date:2019/6/30
 */
@org.springframework.stereotype.Controller
public class Controller {

    /**
     * 单独的显示
     * @return
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return  "Hello Word!";
    }
    //查出一些数据，在主页显示
    @RequestMapping("/th")
    public String thymeleaf(Map<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("user", Arrays.asList("zhangshamn","lisi ","hueh"));
        return "success";
    }
}
