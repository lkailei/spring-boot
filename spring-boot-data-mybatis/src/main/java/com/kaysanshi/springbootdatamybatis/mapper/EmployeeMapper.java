package com.kaysanshi.springbootdatamybatis.mapper;


import com.kaysanshi.springbootdatamybatis.bean.Employee;
import org.springframework.stereotype.Repository;

//@Mapper或者@MapperScan将接口扫描装配到容器中
@Repository
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
