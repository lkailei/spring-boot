package com.kayleoi.springbootcache.mapper;

import com.kayleoi.springbootcache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author kay三石
 * @date:2019/7/22
 */
@Mapper
@Repository
public interface DepartmentMapper {

    @Select("select * from department where id= #{id}")
    public Department getDeptById(Integer id);
}
