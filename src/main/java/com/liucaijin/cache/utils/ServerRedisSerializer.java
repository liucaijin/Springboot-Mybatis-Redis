package com.liucaijin.cache.utils;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

 

public class ServerRedisSerializer implements RedisSerializer{

	@Override
	public byte[] serialize(Object t) throws SerializationException {
		return SerializeUtils.serialize(t);
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		return SerializeUtils.deserialize(bytes);
	}

}
