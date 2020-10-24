package com.kaysanshi.springboot.test.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author kay三石
 * @date:2019/6/29
 * @ConfigurationProperties(prefix = "person")将配置文件映射到本类
 * prefix="person"配置文件下面的属性一一对应。
 *  只有这个组件是容器中的组件才能提供@ConfigurationProperties功能
 *  指定加载的配置文件
 *  @PropertySource(value="classpath:person.properties")
 */
@Component
@ConfigurationProperties(prefix = "person")
@PropertySource(value="classpath:application.properties")
//@Validated数据校验
public class Person {
    //@value("{person.name}")
    //@Email:这里必须是email才可以
    private String name;
    //@value("#{11*2}")
    private Integer aga;
    //@value("true")
    private boolean boss;
    private Date birth;
    private Map maps;
    private List lists;
    private Dog dog;



    public Person() {

    }

    public Person(String name, Integer aga, boolean boss, Date birth, Map maps, List lists, Dog dog) {
        this.name = name;
        this.aga = aga;
        this.boss = boss;
        this.birth = birth;
        this.maps = maps;
        this.lists = lists;
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", aga=" + aga +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }

    public String getName() {
        return name;
    }

    public Integer getAga() {
        return aga;
    }

    public boolean isBoss() {
        return boss;
    }

    public Date getBirth() {
        return birth;
    }

    public Map getMaps() {
        return maps;
    }

    public List getLists() {
        return lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAga(Integer aga) {
        this.aga = aga;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setMaps(Map maps) {
        this.maps = maps;
    }

    public void setLists(List lists) {
        this.lists = lists;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
