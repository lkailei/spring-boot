package com.kayleoi.springbootwebjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value = "abc")
    public String hello(Model model){
        System.out.println("方法L");
        model.addAttribute("msg","你好");
        return "success";
    }
}
