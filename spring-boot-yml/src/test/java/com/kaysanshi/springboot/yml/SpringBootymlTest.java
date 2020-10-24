package com.kaysanshi.springboot.yml;

import com.kaysanshi.springboot.test.Application;
import com.kaysanshi.springboot.test.bean.Person;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author kay三石
 * @date:2019/6/29
 * 单元测试，类似编码一样的注入
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringBootymlTest {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;
    @Test
    public void testHelloService(){
        ioc.getContextPath();

    }
    @Test
    public void contentLoads(){
        System.out.println(person);
    }
}
