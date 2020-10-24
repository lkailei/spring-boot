package com.kaysanshi.springbootdataredis.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import sun.security.krb5.internal.crypto.Des;

/**
 * @Author kay三石
 * @date:2019/6/30
 * 定义序列化的操作，所有的类和对象
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {
    //为了方便对象与数组的转换使用这两个对象转换器
    private Converter<Object ,byte[]> serializingConverter=new SerializingConverter();
    private Converter<byte[],Object> deserializingConverter=new DeserializingConverter();

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (object==null){
            return new byte[0];
        }
        //序列化对象
        return this.serializingConverter.convert(object);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes==null || bytes.length == 0){
            return null;
        }
        return this.deserializingConverter.convert(bytes);
    }
}
