package com.kayleoi.springbootdataredis.config.Lettuce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import static io.lettuce.core.ReadFrom.SLAVE_PREFERRED;

/**
 * @Author kay三石
 * @date:2019/7/1
 * 创建lettuce连接工厂
 * LettuceConnectionFactory创建的所有LettuceConnection实例共享所有非阻塞的相同线程安全本机连接要每次使用专用连接，请将shareNativeConnection设置为false。
 *
 */
@Configuration
public class AppConfig {
    /**
     * Lettuce是一个基于Netty的开源连接器，由Spring Data Redis通过org.springframework.data.redis.connection.lettuce包支持。
     * 以下示例显示如何创建新的Lettuce连接工厂：
     * @return
     */
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("server", 6379));
//    }

//    //以下示例显示如何在/var/run/redis.sock中为Unix域套接字创建Lettuce连接工厂
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//
//        return new LettuceConnectionFactory(new RedisSocketConfiguration("/var/run/redis.sock"));
//    }
    /**
     * 您可以使用LettuceClientConfiguration设置要使用的读/写策略，如以下示例所示
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(SLAVE_PREFERRED)
                .build();

        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration("server", 6379);

        return new LettuceConnectionFactory(serverConfig, clientConfig);
    }
}
