package com.kayleoi.springbootdataredis;

import com.kayleoi.springbootdataredis.bean.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes =SpringBootDataRedisApplication.class)
public class SpringBootDataRedisApplicationTests {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void contextLoads() {
        this.redisTemplate.opsForValue().set("student","james");
        System.out.println(this.redisTemplate.opsForValue().get("student"));

    }

    /**
     * 这里使用的是对象转换为string类型
     */
    @Test
    public void testSet() {
        Member vo = new Member() ;
        vo.setMid("studyjava");
        vo.setAge(19);
        this.redisTemplate.opsForValue().set("study", vo);
    }

    /**
     * 测试得到的值
     */
    @Test
    public void getSet() {
        System.out.println(this.redisTemplate.opsForValue().get("study"));
        //结果：Member{mid='studyjava', age=19}
    }


}
