package com.kaysanshi.springbootcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 默认使用时ConcurrentMapCacheManager == ConcurrentMapCache 将数据保存到ConcurrentMap<Object,Object>
 *  实际开发中使用的是 中间件， redis,ehcache
 *
 *  redis 测试缓存，原理 CacheManager === Cache 将组件来实际给缓存存取数据
 *  引入redis的start后容器所用的是RedisCacheManager
 *  RedisCacheManager 帮我们创建 RedisCache来作为缓存组件
 *  默认保存数据 k,v 都是object 利用序列化转化为json
 *      1.引入redis的start后CacheManager变为 RedisCacheManager
 *      2.默认创建的 RedisCacheManager 操作redis的时候使用的是 RedisTemplate<Object,Object>
 *      3.RedisTemplate<Object, Object>是默认使用jdk的序列化机制
 *   自定义CacheManager:
 *
 */

@SpringBootApplication
@MapperScan("com.kaysanshi.springbootcache.mapper")
@EnableCaching
public class SpringBootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }

}
