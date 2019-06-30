package com.kayleoi.springbootdataredis.bean;

import java.io.Serializable;

/**
 * @Author kay三石
 * @date:2019/6/30
 */
public class Member  implements Serializable {
    private String mid;
    private Integer age;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Member(String mid, Integer age) {
        this.mid = mid;
        this.age = age;
    }

    public Member() {

    }

    @Override
    public String toString() {
        return "Member{" +
                "mid='" + mid + '\'' +
                ", age=" + age +
                '}';
    }
}
