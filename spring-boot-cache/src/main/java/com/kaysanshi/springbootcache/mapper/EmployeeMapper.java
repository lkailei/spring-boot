package com.kaysanshi.springbootcache.mapper;

import com.kaysanshi.springbootcache.bean.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author kay三石
 * @date:2019/7/22
 * @Mapper 标注为是一个mapper
 */
@Mapper
@Repository
public interface EmployeeMapper {

    @Select("select * from employee where id= #{id}")
    public Employee getEmpById(Integer id);

    @Update("update employee set lastName = #{lastName},email = #{email},gender = #{gender},d_id = #{dId} where id = #{id}")
    public void updateEmp(Employee employee);

    @Delete("Delete from employee where id=#{id}")
    public void deleteEmById(Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);

    @Select("select * from employee where lastName= #{lastName}")
    public Employee getEmpByName(String lastName);
}
