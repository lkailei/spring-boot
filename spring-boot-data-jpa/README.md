1）、SpringData简介

Spring Data的使命是为数据访问提供一个熟悉且一致的，基于Spring的编程模型，同时仍保留基础数据存储的特殊特征。

它使使用数据访问技术，关系和非关系数据库，map-reduce框架以及基于云的数据服务变得容易。 这是一个总括项目，其中包含许多特定于给定数据库的子项目。 这些项目是通过与这些令人兴奋的技术背后的许多公司和开发人员共同开发的。

主要模块

Spring Data Commons-每个Spring Data模块的核心Spring概念。

Spring Data JDBC-Spring Data存储库对JDBC的支持。

Spring Data JDBC Ext-支持标准JDBC的数据库特定扩展，包括对Oracle RAC快速连接故障转移的支持，对AQ JMS的支持以及对使用高级数据类型的支持。

Spring Data JPA-对JPA的Spring Data存储库支持。

Spring Data KeyValue-基于映射的存储库和SPI，可以轻松地为键值存储构建Spring Data模块。

Spring Data LDAP-Spring数据存储库对Spring LDAP的支持。

Spring Data MongoDB-MongoDB的基于Spring的对象文档支持和存储库。

Spring Data Redis-轻松配置和从Spring应用程序访问Redis。

Spring Data REST-将Spring Data存储库导出为超媒体驱动的RESTful资源。

适用于Apache Cassandra的Spring数据-易于配置和访问Apache Cassandra或大规模，高可用性，面向数据的Spring应用程序。

用于Apache Geode的Spring Data-易于配置和访问Apache Geode，以实现高度一致，低延迟的面向数据的Spring应用程序。

适用于Apache Solr的Spring数据-易于配置，并可访问面向搜索的Spring应用程序访问Apache Solr。

用于Pivotal GemFire的Spring数据-可以轻松配置并访问Pivotal GemFire，以实现高度一致，低延迟/高吞吐量的面向数据的Spring应用程序。

2）、整合SpringData JPA

JPA:ORM（Object Relational Mapping）；

1）、编写一个实体类（bean）和数据表进行映射，并且配置好映射关系；

    //使用JPA注解配置映射关系
    @Entity //告诉JPA这是一个实体类（和数据表映射的类）
    @Table(name = "tbl_user") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
    public class User {
    
        @Id //这是一个主键
        @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
        private Integer id;
    
        @Column(name = "last_name",length = 50) //这是和数据表对应的一个列
        private String lastName;
        @Column //省略默认列名就是属性名
        private String email;

2）、编写一个Dao接口来操作实体类对应的数据表（Repository）

    //继承JpaRepository来完成对数据库的操作
    public interface UserRepository extends JpaRepository<User,Integer> {
    }
    

3）、基本的配置JpaProperties

    spring:  
     jpa:
        hibernate:
    #     更新或者创建数据表结构
          ddl-auto: update
    #    控制台显示SQL
        show-sql: true


