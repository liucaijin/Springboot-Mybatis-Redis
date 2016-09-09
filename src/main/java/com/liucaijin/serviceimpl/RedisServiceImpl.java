package com.liucaijin.serviceimpl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.liucaijin.domain.User;
import com.liucaijin.service.IredisService;

@Service
public class RedisServiceImpl implements IredisService {

	@Override
	@Cacheable(value = "subcache",keyGenerator = "wiselyKeyGenerator")
	public String sub(int a, int b) {
		System.out.println("没有读取缓存");
		return (a-b)+"";
	}
	
	@Override
	@Cacheable(value = "subcache2",keyGenerator = "wiselyKeyGenerator")
	public User sub2(int a, int b) {
		System.out.println("没有读取缓存");
		
		return new User(a-b);
	}

}
