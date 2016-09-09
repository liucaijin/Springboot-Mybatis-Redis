package com.liucaijin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liucaijin.cache.CacheUtil4Hash;
import com.liucaijin.cache.ICacheKey;
import com.liucaijin.domain.User;
import com.liucaijin.service.IredisService;

@Controller
public class TestRedisCache {
   @Autowired
   IredisService iredisService;
   @Autowired
   RedisTemplate redisTemplate;
   
   @SuppressWarnings("unchecked")
@RequestMapping("/test")
   public String getSubResult(){
//	   iredisService.sub2(1, 2);
//	   iredisService.sub2(2, 1);
	   Map<byte[],byte[]> map=new  HashMap<byte[],byte[]>();
	   
	   map.put("123".getBytes(), "123".getBytes());
	   
	   HashOperations opsForHash = redisTemplate.opsForHash();
	   Object object = opsForHash.get("liucaijin".getBytes(), "123".getBytes());
//	   redisTemplate.execute(new RedisCallback<Boolean>() {
//
//		@Override
//		public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
//			connection.hMSet("liucaijin".getBytes(), map); 
//			return true;
//		}
//		   
//	});
	   return "ok";
   }
   
   @RequestMapping("/test2")
   public String redisTest(){
	   List<User> users=new ArrayList<>();
	   users.add(new User(1,11));
	   users.add(new User(2,12));
	   users.add(new User(3,13));
	   users.add(new User(4,14));
	   CacheUtil4Hash.cache4Hash(users, "users", new ICacheKey<User>() {

		@Override
		public String getCacheKey(User t) {
			
			return t.getId()+"";
		}
	});
	   return "ok";
   }
   
   @RequestMapping("/test3")
   public String redisTest2(){
	   List<User> users=new ArrayList<>();
	   List<String> keys=new ArrayList<>();
	   keys.add("1");
	   List<User> get4HashFromRedis = CacheUtil4Hash.get4HashFromRedis("users", keys);
	   if(get4HashFromRedis!=null)
	   System.out.println("大小为："+get4HashFromRedis.size());
	   return "ok";
   }
}
