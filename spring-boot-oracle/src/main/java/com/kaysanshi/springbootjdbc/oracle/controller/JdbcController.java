package com.kaysanshi.springbootjdbc.oracle.controller;

import com.kaysanshi.springbootjdbc.oracle.bean.Article;
import com.kaysanshi.springbootjdbc.oracle.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author kay三石
 * @date:2019/6/30
 */
@Controller
@RequestMapping("/test")
public class JdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

//    execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句；
//
//    update方法及batchUpdate方法：update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批处理相关语句；
//
//    query方法及queryForXXX方法：用于执行查询相关语句；
//
//    call方法：用于执行存储过程、函数相关语句。

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> add(){
        Map<String,Object> map=new HashMap();
        jdbcTemplate.execute("insert into \"student\"(\"id\", \"name\", \"sex\",\"age\", \"phone\") values('5','skdin','男','12','12345678910')");
        map.put("code","0");
        map.put("data","success");
        return map;
    }

    /**
     * 必须使用""把字段名字给包裹住
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> update(){
        Map<String,Object> map=new HashMap();
        jdbcTemplate.execute("UPDATE \"student\" SET  \"name\"='doelo', \"sex\"='男', \"age\"='12', \"phone\"='123456910' WHERE \"id\"= '5'");
        map.put("code","0");
        map.put("data","success");
        return map;
    }

    @ResponseBody
    @GetMapping("/querylist")
    public Map<String,Object> map(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM \"student\"");
        return list.get(0);
    }
    @ResponseBody
    @GetMapping("/query")
    public List<Student> getForList(){
        return  jdbcTemplate.query("select * FROM \"student\"", new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student model = new Student();
                model.setId(resultSet.getInt("Id"));
                model.setAge(resultSet.getInt("Age"));
                model.setName(resultSet.getString("Name"));
                model.setSex(resultSet.getString("Sex"));
                model.setPhone(resultSet.getString("Phone"));
                return model;
            }
        });
    }


    @ResponseBody
    @GetMapping("/queryobj")
    public Map<String,Object> getForObject(){
        Map<String,Object> map=new HashMap();
        //  最后一个参数是返回值的类型（只能是基本数据类型的封装类，如Integer、String）
        Student list = jdbcTemplate.queryForObject("select \"id\",\"name\",\"sex\",\"age\",\"phone\" from  \"student\"  where \"id\" ='3'", new BeanPropertyRowMapper<Student>(Student.class));
        map.put("code","0");
        map.put("data",list);
        return map;
    }


}
