package com.kayleoi.springboot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author kay三石
 * @date:2019/6/29
 * 导入spring的配置文件，这样的不建议使用。
 * @ImportResource(locations = {"classpath:beans.xml"})
 */

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
