package com.kayleoi.springbootdataredis.config.jedis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @Author kay三石
 * @date:2019/7/1
 * Jedis是一个社区驱动的连接器，
 * 由Spring Data Redis模块通过org.springframework.data.redis.connection.jedis包支持。在最简单的形式中，Jedis配置如下所示
 */
@Configuration
public class Appconfig {
    /**
     * 标准配置
     * @return
     */
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
    /**
     *  但是，对于生产用途，您可能需要调整主机或密码等设置，如以下示例所示：
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("server", 6379);
        return new JedisConnectionFactory(config);
    }
}
