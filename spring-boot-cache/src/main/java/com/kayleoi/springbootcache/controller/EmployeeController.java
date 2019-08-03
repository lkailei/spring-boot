package com.kayleoi.springbootcache.controller;

import com.kayleoi.springbootcache.bean.Employee;
import com.kayleoi.springbootcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author kay三石
 * @date:2019/7/22
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getEmployee/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee employee= employeeService.getEmployee(id);
        return  employee;
    }
    @GetMapping("/getEmployee/lastName/{lastName}")
    public Employee getEmpByName(@PathVariable("lastName")String lastName){
        return employeeService.getEmployeeByName(lastName);
    }
}
