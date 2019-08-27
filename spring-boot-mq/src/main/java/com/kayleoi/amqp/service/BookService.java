package com.kayleoi.amqp.service;

import com.kayleoi.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author kay三石
 * @date:2019/8/26
 */
@Service
public class BookService {

    @RabbitListener(queues = "atguigu.new")
    public  void receive(Book book){
        System.out.println("收到消息："+ book);

    }

    @RabbitListener(queues = "atguigu")
    public void receive1(Message message){
        System.out.println(message.getBody());
    }
}
