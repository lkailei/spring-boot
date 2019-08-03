package com.kayleoi.springbootcache;

import com.kayleoi.springbootcache.bean.Employee;
import com.kayleoi.springbootcache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate; // 简化操作字符串
    @Autowired
    RedisTemplate redisTemplate; //对象形式

    @Autowired
    RedisTemplate<Object, Employee> emRedisTemplate; // 自动注入这个自己实力化的

    @Test
    public void contextLoads() {
        Employee empById = employeeMapper.getEmpById(1);
        System.out.println(empById);
    }

    /**
     * redis可以操作五大数据类型
     * string,list,set,hash,zset
     *  stringRedisTemplate.opsForValue()
     *  stringRedisTemplate.opsForList()
     *  ....
     */
    @Test
    public void test01(){
        //给redis中保存一个数据
        stringRedisTemplate.opsForValue().append("keys1","message");
        String string=stringRedisTemplate.opsForValue().get("keys1");
        System.out.println(string);
        //存放到列表
        stringRedisTemplate.opsForList().leftPush("mylist","zhangsan");
        stringRedisTemplate.opsForList().leftPush("mylist","lisi");
        //取出
        stringRedisTemplate.opsForList().leftPop("mylist");
    }
    public void test02(){
        Employee empById = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用序列化然后保存到redis中
        //redisTemplate.opsForValue().set("emp-01", empById);
        //将数据以json的方式保存(将对象转化为json,redisTemplete的默认的序列化规则)
        emRedisTemplate.opsForValue().set("emp-01",empById);

    }
}
