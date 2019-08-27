package com.kayleoi.amqp;

import com.kayleoi.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author kay三石
 * @date:2019/8/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MQApplicationTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    /**
     * 以declare开头是创建的机制
     */
    @Test
    public void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
    }

    /**
     * 创建对列
     */
    @Test
    public void createExchangeQueue(){
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqpadmin.rout",null));

    }
    /**
     * 删除
     */
    public void deleteExchange(){
        amqpAdmin.deleteQueue("amqpadmin.exchange");
    }
    /**
     * 1.单播(点兑点)
     */
    @Test
    public void contextLoads(){
        //Message需要自己构造一个，自定义的消息头，和消息内容
        //rabbitTemplate.send(exchange,routerKey,message);

        //object默认当成消息体，只需要传入发送的对象，自动序列发送
        //rabbitTemplate.convertAndSend(exchange,routerKey,object);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","rabbitQ消息");
        map.put("data", Arrays.asList("helloworld",123,true));
        //对象被默认序列化后发送
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);

    }

    /**
     *接受数据
     */
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");

        System.out.println(o.getClass());
        System.out.println(o);
    }
    /***
     * 序列化数据，序列化为json数据
     */

    /***
     * 广播
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("北京姑娘","那方"));
    }

}
