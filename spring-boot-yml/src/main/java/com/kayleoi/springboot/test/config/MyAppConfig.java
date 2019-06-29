package com.kayleoi.springboot.test.config;

import com.kayleoi.springboot.test.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author kay三石
 * @date:2019/6/29
 * @Configuration指明当前的类是一个配置类代替之前的spring配置问阿金
 * 在bean是<bean></bean>
 */
@Configuration
public class MyAppConfig {

    @Bean
    public HelloService helloService(){
        System.out.printf("配置类的使用");
        return  new HelloService();
    }
}
