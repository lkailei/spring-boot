package com.kayleoi.springbootwebrestfulcurd.config;

import com.kayleoi.springbootwebrestfulcurd.component.LoginHandlerInterceptor;
import com.kayleoi.springbootwebrestfulcurd.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author kay三石
 * @date:2019/6/30
 *
//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc全面接管SpringMVC，不需要springboot的自动配合
 @EnableWebMvc所有的springbootweb模块都失效了 自动配置都失效了
 */

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    public MyMvcConfig() {
        super();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/index").setViewName("success");
    }

    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                super.addViewControllers(registry);
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index").setViewName("login");
            }

        };
         return  adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){

        return new MyLocaleResolver();
    }
}
