package com.kaysanshi.springbootconfig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author kay三石
 * @date:2019/6/29
 */
@Controller
public class HelloController {

    @RequestMapping("/helldo")
    @ResponseBody
    public String toHello() {
        return "hello";
    }
}
