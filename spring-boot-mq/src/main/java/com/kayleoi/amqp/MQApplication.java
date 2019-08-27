package com.kayleoi.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author kay三石
 * @date:2019/8/25
 * 自动配置
 * 1.RabbitAutoConfiguration
 * 2.有自动配置了连接工厂ConnectionFactory
 * 3.RabbitProperties 封装了RabbitMQ的配置
 * 4.RabbitTemplate:给RabbitMQ发送和接受消息
 * 5.AMqpAdmin: rabbitMQ系统管理功能组件
 * 6.EnableRabbit+ @RabbitListener 监听消息对列
 *
 */
@EnableRabbit //开启基于注解的rabbitMQ模式
@SpringBootApplication
public class MQApplication {
    public static void main(String[] args) {
        SpringApplication.run(MQApplication.class);
    }
}
