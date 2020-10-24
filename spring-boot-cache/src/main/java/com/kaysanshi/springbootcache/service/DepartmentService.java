package com.kaysanshi.springbootcache.service;

import com.kaysanshi.springbootcache.bean.Department;
import com.kaysanshi.springbootcache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * @Author kay三石
 * @date:2019/8/3
 *  *  当使用了不同的管理器时，需要指定一个默认的缓存管理器，要不然不会找到自己写的缓存管理器
 */
@CacheConfig(cacheNames = "dept", cacheManager = "departmentCacheManager")
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 缓存的数据存入 redis
     * 第二次
     * @param id
     * @return
     */
    @Cacheable(value = "dept")
    public Department getDeptById(Integer id){
        System.out.println("查询部门" + id);
        Department department =departmentMapper.getDeptById(id);
        return department;
    }
}
