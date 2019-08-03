package com.kayleoi.springbootdataredis.config.sentinel;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @Author kay三石
 * @date:2019/7/1
 * 为了处理高可用性Redis，Spring Data Redis使用RedisSentinelConfiguration支持Redis Sentinel，如以下示例所示
 */
public class AppConfig {
    /**
     * RedisSentinelConfiguration也可以使用PropertySource定义，它允许您设置以下属性
     * spring.redis.sentinel.master：主节点的名称。
     * spring.redis.sentinel.nodes：逗号分隔的主机：端口对列表。
     * 有时，需要与其中一个Sentinels直接交互。使用RedisConnectionFactory.getSentinelConnection（）
     * 或RedisConnection.getSentinelCommands（）可以访问配置的第一个活动Sentinel。
     */
    /**
     * Jedis
     */
    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel("127.0.0.1", 26379)
                .sentinel("127.0.0.1", 26380);
        return new JedisConnectionFactory(sentinelConfig);
    }

    /**
     * Lettuce
     */
    @Bean
    public RedisConnectionFactory lettuceConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel("127.0.0.1", 26379)
                .sentinel("127.0.0.1", 26380);
        return new LettuceConnectionFactory(sentinelConfig);
    }
}
