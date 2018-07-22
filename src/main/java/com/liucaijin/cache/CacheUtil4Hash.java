package com.liucaijin.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class CacheUtil4Hash {
	static RedisTemplate redisTemplate;

	@Autowired
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@SuppressWarnings("unchecked")
	public static <T> void cache4Hash(final List<T> list, final String tableName, ICacheKey t1) {

		RedisSerializer keySerializer = redisTemplate.getKeySerializer();

		Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();

		for (T o : list) {
			map.put(keySerializer.serialize(t1.getCacheKey(o)), keySerializer.serialize(o));
		}

		redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hMSet(tableName.getBytes(), map);
				return true;
			}
		}, false, true);
		try {

		} catch (Exception e) {
			System.out.println("缓存到redis失败");
		}

	}

	public static <T> List<T> get4HashFromRedis(final String tableName, List keys) {
		List<T> result = new ArrayList<T>();
		RedisSerializer keySerializer = redisTemplate.getKeySerializer();
		HashOperations opsForHash = redisTemplate.opsForHash();
		List<T> keystemp = new ArrayList<T>();
		for (Object t : keys) {
			keystemp.add((T) keySerializer.serialize(t));
		}

		@SuppressWarnings("unchecked")
		List<T> multiGet = opsForHash.values(tableName);
		System.out.println("开始的大小：" + multiGet.size());
		if (multiGet.size() > 0) {
			for (T o : multiGet) {
				if (o != null) {
					result.add(o);
				}
			}
		}
		try {

		} catch (Exception e) {
			System.out.println("从缓存中取数据失败");
		}
		return result;
	}

}
