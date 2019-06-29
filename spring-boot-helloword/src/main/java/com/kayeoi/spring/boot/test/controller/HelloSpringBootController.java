package com.kayeoi.spring.boot.test.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author kay三石
 * @date:2019/6/29
 */
@Controller
public class HelloSpringBootController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello spring boot!";
    }
}
