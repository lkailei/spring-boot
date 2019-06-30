package com.kayleoi.springbootdataredis.config;

import com.kayleoi.springbootdataredis.utils.RedisObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author kay三石
 * @date:2019/6/30
 * 为了让RedisTemplate操作模板知道有这样的一个序列程序类存在，这里定义配置
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> getRedisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate <>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer()); //key的序列化类型
        redisTemplate.setValueSerializer(new RedisObjectSerializer());//value的序列化类型
        return  redisTemplate;
    }
}
