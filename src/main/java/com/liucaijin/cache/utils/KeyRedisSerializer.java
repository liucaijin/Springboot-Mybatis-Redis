package com.liucaijin.cache.utils;

 
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

 

public class KeyRedisSerializer implements RedisSerializer{

	@Override
	public byte[] serialize(Object t) throws SerializationException {
		if(t instanceof String){
			return StringUtils.getBytesUtf8((String)t);
		}
		return SerializeUtils.serialize(t);
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		return new String (bytes);
//		return SerializeUtils.deserialize(bytes);
	}

}
