package com.kayleoi.springbootcache.service;

import com.kayleoi.springbootcache.bean.Employee;
import com.kayleoi.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @Author kay三石
 * @date:2019/7/22
 * @CacheConfig(cacheNames = "emp") 抽取缓存的公共配置
 * 原理：
 *  1.自动配置类：CacheAutoConfiguration
 *  2.缓存的配置类：
 *
 *  3.默认的配置类：SimpleCaCheConfiguration
 *  当写了多个cacheManager 的需要指定哪个管理器使用的
 *  当使用了不同的管理器时，需要指定一个默认的缓存管理器，要不然不会找到自己写的缓存管理器
 *
 */
@CacheConfig(cacheNames = "emp", cacheManager = "employeeCacheManager")
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 属性：
     * cacheNames/value: 执行缓存组件的名字，将方法的返回结果放到哪个缓存中是数组方式，可指定多个缓存
     * key: 缓存数据使用的key，可以用来指定默认使用方法参数的值，1-方法的返回值
     * 编写spel： # id: 参数id的值， #a0 #p0  #root.args[0]
     * 执行使用的key ：key = "#root.methodName+'['+#id+']'" ====getEmployee(id)
     * keyGenerator:key的生成器，可以自己指定key的生成器的组件的id
     * key/keyGenerator二则选一使用
     * <p>
     * cacheManager: 指定缓存管理器，或者cacheResolver指定获取解析器
     * condition: 指定符合条件的情况下才缓存 condion= "#id >0 and #id <10"
     * unless: 否定缓存，当unless指定条件为true时，方法的返回值就不会被缓存，可以获取到结果进行判断
     * unless = "#result == null"
     * unless = "#a0==2" 如果第一个参数为2时则不缓存
     * sync:  是否使用异步的方式
     *
     * @param id
     * @return
     */
    @Cacheable(value = {"emp"}, key = "#root.methodName+'['+#id+']'")
    public Employee getEmployee(Integer id) {
        //用默认的需要序列化
        System.out.println("查询数据库");
        Employee empById = employeeMapper.getEmpById(id);
        return empById;
    }

    /**
     * 指定缓存
     *
     * @param id
     * @return
     */
    @Cacheable(value = {"emp2"}, keyGenerator = "myKeyGenerator", condition = "#a0>0", unless = "#a0==2")
    public Employee getEmployee2(Integer id) {
        Employee empById = employeeMapper.getEmpById(id);
        return empById;
    }

    /**
     * @CachePut: 调用方法，又更新缓存  ，修改的同时更新(同步更新缓存)
     * 运行实机：
     *  1.先调用目标方法
     *  2.将目标方法的结果缓存起来
     *      方法运行后将结果放入
     *
     * 调式步骤：
     *  1.查询1号员工，查到的结果放入缓存中：
     *      key :1 value: lastName:
     *  2. 以后查询还是之前的结果
     *  3. 更新1号员工，
     *      将方法的返回值放入了缓存 key:传入的emplyee对象，返回的也是对象
     *  4. 查询1号员工
     *      应该是更新后的员工
     *          key = "#employee.id" 使用传入参数的id
     *          key = "#result.id" 使用返回值的id
     * @Caheable的key不能使用#result
     *      但为什么是没有更新前呢？【1号员工没有在缓冲中更新,就是缓存中没有这个key】
     */
    @CachePut(value = {"emp"}, key = "#employee.id")
    public Employee update(Employee employee){
        System.out.println("employee = [" + employee + "]");
        employeeMapper.updateEmp(employee);
        return employee;

    }

    /**
     * 清除缓存
     *  key:指定清除的数据
     *  allEntries = true 指定清除这缓存中的所有的数据
     *  beforeInvocation = false：
     *          默认是方法执行之后执行，如果出现异常缓存不会清除
     *
     *  beforeInvocation = true
     *          代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
     *
     *
     * @param id
     */
    @CacheEvict(value = "emp",beforeInvocation = true, key = "#id")
    public void deleteEmp(Integer id){
        employeeMapper.deleteEmById(id);
    }
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp", key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.email")
            }
    )
    public Employee getEmployeeByName(String lastName){
        return employeeMapper.getEmpByName(lastName);
    }
}
