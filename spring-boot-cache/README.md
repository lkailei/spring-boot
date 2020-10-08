1.JSR-107

Java Caching定义了5个核心接口，分别是CachingProvider, CacheManager, Cache, Entry 和 Expiry。

- CachingProvider定义了创建、配置、获取、管理和控制多个CacheManager。一个应用可 以在运行期访问多个CachingProvider。
- CacheManager定义了创建、配置、获取、管理和控制多个唯一命名的Cache，这些Cache 存在于CacheManager的上下文中。一个CacheManager仅被一个CachingProvider所拥有。
- Cache是一个类似Map的数据结构并临时存储以Key为索引的值。一个Cache仅被一个 CacheManager所拥有。
- Entry是一个存储在Cache中的key-value对。
- Expiry 每一个存储在Cache中的条目有一个定义的有效期。一旦超过这个时间，条目为过期 的状态。一旦过期，条目将不可访问、更新和删除。缓存有效期可以通过ExpiryPolicy设置。

![0w6PQP.png](https://s1.ax1x.com/2020/10/08/0w6PQP.png)

2.Spring缓存抽象

Spring从3.1开始定义了org.springframework.cache.Cache

和org.springframework.cache.CacheManager接口来统一不同的缓存技术；

并支持使用JCache（JSR-107）注解简化我们开发；

•Cache接口为缓存的组件规范定义，包含缓存的各种操作集合；

•Cache接口下Spring提供了各种xxxCache的实现；如RedisCache，EhCacheCache , ConcurrentMapCache等；

•每次调用需要缓存功能的方法时，Spring会检查检查指定参数的指定的目标方法是否已经被调用过；如果有就直接从缓存中获取方法调用后的结果，如果没有就调用方法并缓存结果后返回给用户。下次调用直接从缓存中获取。

•使用Spring缓存抽象时我们需要关注以下两点；

1、确定方法需要被缓存以及他们的缓存策略

2、从缓存中读取之前缓存存储的数据

![0w69zt.png](https://s1.ax1x.com/2020/10/08/0w69zt.png)


3.概念和缓存注解

  Cache         	缓存接口，定义缓存操作。实现有：**RedisCache、EhCacheCache、ConcurrentMapCache等**
  CacheManager  	缓存管理器，管理各种缓存（**Cache）组件**               
  @Cacheable    	主要针对方法配置，能够根据方法的请求参数对其结果进行缓存            
  @CacheEvict   	清空缓存                                    
  @CachePut     	保证方法被调用，又希望结果被缓存。                       
  @EnableCaching	开启基于注解的缓存                               
  keyGenerator  	缓存数据时key生成策略                            
  serialize     	缓存数据时value序列化策略                         

  @Cacheable/@CachePut/ @CacheEvict主要的参数	                                        	                                        
  value                                 	缓存的名称，在   spring 配置文件中定义，必须指定至少一个       	例如：      @Cacheable(value=”mycache”) 或者       @Cacheable(value={”cache1”,”cache2”}
  key                                   	缓存的   key，可以为空，如果指定要按照   SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合	例@Cacheable(value=”testcache”,key=”#userName”)
  condition                             	缓存的条件，可以为空，使用   SpEL 编写，返回   true 或者 false，只有为   true 才进行缓存/清除缓存，在调用方法之前之后都能判断	例如：      @Cacheable(value=”testcache”,condition=”#userName.length()>2”)
  allEntries   (@CacheEvict   )         	是否清空所有缓存内容，缺省为   false，如果指定为 true，则方法调用后将立即清空所有缓存	例如：      @CachEvict(value=”testcache”,allEntries=true)
  unless   (@CachePut)   (@Cacheable)   	用于否决缓存的，不像condition，该表达式只在方法执行之后判断，此时可以拿到返回值result进行判断。条件为true不会缓存，fasle才缓存	例如：      @Cacheable(value=”testcache”,unless=”#result   == null”)
  beforeInvocation   (@CacheEvict)      	是否在方法执行前就清空，缺省为   false，如果指定为 true，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存	例如：   @CachEvict(value=”testcache”，beforeInvocation=true)

  名字           	位置                	描述                                      	示例                  
  methodName   	root object       	当前被调用的方法名                               	#root.methodName    
  method       	root object       	当前被调用的方法                                	#root.method.name   
  target       	root object       	当前被调用的目标对象                              	#root.target        
  targetClass  	root object       	当前被调用的目标对象类                             	#root.targetClass   
  args         	root object       	当前被调用的方法的参数列表                           	#root.args[0]       
  caches       	root object       	当前方法调用使用的缓存列表（如@Cacheable(value={"cache1",   "cache2"})），则有两个cache	#root.caches[0].name
  argument name	evaluation context	方法参数的名字. 可以直接 #参数名 ，也可以使用 #p0或#a0 的形式，0代表参数的索引；	#iban 、 #a0 、  #p0  
  result       	evaluation context	方法执行后的返回值（仅当方法执行之后的判断有效，如‘unless’，’cache put’的表达式 ’cache evict’的表达式beforeInvocation=false）	#result             

4.缓存的使用

• 1、引入spring-boot-starter-cache模块

 • 2、@EnableCaching开启缓存

 • 3、使用缓存注解

 • 4、切换为其他缓存
5.SpringBoot整合Redis实现缓存

1.引入pom.xml。前提说下这里使用的mybatis的文件是这样的：

    <dependency>   
        <groupId>org.mybatis.spring.boot</groupId>    
        <artifactId>mybatis-spring-boot-starter</artifactId>   
        <version>2.0.1</version>
    </dependency>

如果这里是高版本的当我们自定义配置cache的方式就不是本文章所看到的那样了

2.spring.application的配置文件

    spring.datasource.url=jdbc:mysql://localhost:3306/spring-boot-cache?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    spring.datasource.password=123
    spring.datasource.username=root
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    #开启驼峰mybatis.configuration.map-underscore-to-camel-case=trues
    pring.redis.host=127.0.0.1

3.redis的配置类：

    package com.kayleoi.springbootcache.config;
    
    import com.kayleoi.springbootcache.bean.Department;
    import com.kayleoi.springbootcache.bean.Employee;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.Primary;
    import org.springframework.core.io.ResourceLoader;
    import org.springframework.data.redis.cache.RedisCacheManager;
    import org.springframework.data.redis.connection.RedisConnectionFactory;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
    
    import java.net.UnknownHostException;
    import java.util.LinkedHashSet;
    import java.util.List;
    
    /**
     * @Author kay三石
     * @date:2019/8/3
     * 当有多个缓存管理器时，必须指定一个默认的缓存管理器
     *     @Primary 指定默认的缓存管理器，这里我们话应该使用spring中自带的(不加会出现以下错误)
     *     java.lang.IllegalStateException: No CacheResolver specified, and no unique bean of type CacheManager found.
     *     Mark one as primary or declare a specific CacheManager to use.
     *需要使用低版本的对于2.0.0以上的版本使用方法不是这样的
     */
    @Configuration
    public class MyRedisConfig {
        /**
         * 用自己的CacheManager 为 employee序列化缓存
         *@Primary 将某个缓存管理器设为默认的
         * @param
         * @return
         */
        @Bean
        public RedisCacheManager employeeCacheManager(RedisTemplate<Object,Employee> empRedisTemplate) {
            RedisCacheManager cacheManager = new RedisCacheManager(empRedisTemplate);
            //key 使用前缀，将CacheName最为前缀
            cacheManager.setUsePrefix(true);
            return cacheManager;
        }
        /**
         * 用自己的CacheManager 为 depart序列化缓存管理器
         * @param
         * @return
         */
        @Bean
        public RedisCacheManager departmentCacheManager(RedisTemplate<Object,Department> depRedisTemplate) {
            RedisCacheManager cacheManager = new RedisCacheManager(depRedisTemplate);
            //key 使用前缀，将CacheName最为前缀
            cacheManager.setUsePrefix(true);
            return cacheManager;
        }
    
        /**
         * 使用自己的将object对象转化为json
         * @param redisConnectionFactory
         * @return
         * @throws UnknownHostException
         */
        @Bean
        public RedisTemplate<Object,Employee> empRedisTemplate(
                RedisConnectionFactory redisConnectionFactory)throws UnknownHostException {
            RedisTemplate<Object, Employee> template = new RedisTemplate <>();
            template.setConnectionFactory(redisConnectionFactory);
            template.setDefaultSerializer(new Jackson2JsonRedisSerializer <Employee>(Employee.class));
            return template;
        }
    
        @Bean
        public RedisTemplate<Object, Department> depRedisTemplate(
                RedisConnectionFactory redisConnectionFactory)throws UnknownHostException {
            RedisTemplate<Object, Department> template = new RedisTemplate <>();
            template.setConnectionFactory(redisConnectionFactory);
            template.setDefaultSerializer(new Jackson2JsonRedisSerializer <Department>(Department.class));
            return template;
        }
    
        /**
         * 选择redis作为默认缓存工具
         * @param redisTemplate
         * @return
         */
        @Primary
        @Bean
        public RedisCacheManager cacheManager(RedisTemplate redisTemplate) {
            RedisCacheManager caheManager = new RedisCacheManager(redisTemplate);
            return caheManager;
        }
    }
    
    /**
     * 默认使用时ConcurrentMapCacheManager == ConcurrentMapCache 将数据保存到ConcurrentMap<Object,Object>
     *  实际开发中使用的是 中间件， redis,ehcache
     *
     *  redis 测试缓存，原理 CacheManager === Cache 将组件来实际给缓存存取数据
     *  引入redis的start后容器所用的是RedisCacheManager
     *  RedisCacheManager 帮我们创建 RedisCache来作为缓存组件
     *  默认保存数据 k,v 都是object 利用序列化转化为json
     *      1.引入redis的start后CacheManager变为 RedisCacheManager
     *      2.默认创建的 RedisCacheManager 操作redis的时候使用的是 RedisTemplate<Object,Object>
     *      3.RedisTemplate<Object, Object>是默认使用jdk的序列化机制
     *   自定义CacheManager:
     *
     */
    
    @SpringBootApplication
    @MapperScan("com.kayleoi.springbootcache.mapper")
    @EnableCaching
    public class SpringBootCacheApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(SpringBootCacheApplication.class, args);
        }
    
    }
    

service层：

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
    

通过以上的配置的代码可以实现redis操作缓存数据。
