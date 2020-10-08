package com.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * redisKey设计
 */
public class RedisKeyUtil {

//    public static  void main(String args[]){
//        List<String> list=new ArrayList <>();
//        list.add("ABC");
//        list.add("BCD");
//        list.add("ADDD");
//        for (int i=0;i<list.size();i++){
//            if (list.get(i).equals("ABC")){
//                list.remove(list.get(i));
//            }
//        }
//        System.out.println(list);
//        List<String> list2=new ArrayList <>();
//        list2.add("ABC");
//        list2.add("BCD");
//        list2.add("ADDD");
//        Iterator iterator1=list2.iterator();
//        while (iterator1.hasNext()){
//            String current= (String) iterator1.next();
//            if (current.equals("ABC")){
//                //list2.remove(current); // 这个时候列表并未改变 原因？。
//                iterator1.remove();
//            }
//        }
//        System.out.println(list2);
//
//        List<String> list1=new ArrayList <>();
//        list1.add("ABC");
//        list1.add("BCD");
//        list1.add("ADDD");
//        for (String str : list1){
//            System.out.print(str);
//            if (str.equals("ABC")){
//                // 异常原因
//                list1.remove(str);
//            }
//        }
//        System.out.println(list1);
//    }

    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值:列名
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @param column 列名
     * @return
     */
    public static String getKeyWithColumn(String tableName,String majorKey,String majorKeyValue,String column){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return buffer.toString();
    }
    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @return
     */
    public static String getKey(String tableName,String majorKey,String majorKeyValue){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        return buffer.toString();
    }
}
