package com.kaysanshi.springbootjpa.repository;

import com.kaysanshi.springbootjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author kay三石
 * @date:2019/6/29
 */
//继承JpaRepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User,Integer> {
}
