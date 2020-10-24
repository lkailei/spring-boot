package com.kaysanshi.springbootcache.config;

import com.kaysanshi.springbootcache.bean.Department;
import com.kaysanshi.springbootcache.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @Author kay三石
 * @date:2019/8/3
 * 当有多个缓存管理器时，必须指定一个默认的缓存管理器
 *     @Primary 指定默认的缓存管理器，这里我们话应该使用spring中自带的(不加会出现以下错误)
 *     java.lang.IllegalStateException: No CacheResolver specified, and no unique bean of type CacheManager found.
 *     Mark one as primary or declare a specific CacheManager to use.
 *需要使用低版本的对于2.0.0以上的版本使用方法不是这样的
 */
@Configuration
public class MyRedisConfig {
    /**
     * 用自己的CacheManager 为 employee序列化缓存
     *@Primary 将某个缓存管理器设为默认的
     * @param
     * @return
     */
    @Bean
    public RedisCacheManager employeeCacheManager(RedisTemplate<Object,Employee> empRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(empRedisTemplate);
        //key 使用前缀，将CacheName最为前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
    /**
     * 用自己的CacheManager 为 depart序列化缓存管理器
     * @param
     * @return
     */
    @Bean
    public RedisCacheManager departmentCacheManager(RedisTemplate<Object,Department> depRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(depRedisTemplate);
        //key 使用前缀，将CacheName最为前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    /**
     * 使用自己的将object对象转化为json
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<Object,Employee> empRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate <>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer <Employee>(Employee.class));
        return template;
    }

    @Bean
    public RedisTemplate<Object, Department> depRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate <>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer <Department>(Department.class));
        return template;
    }

    /**
     * 选择redis作为默认缓存工具
     * @param redisTemplate
     * @return
     */
    @Primary
    @Bean
    public RedisCacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager caheManager = new RedisCacheManager(redisTemplate);
        return caheManager;
    }
}
