package com.liucaijin.cache;

 

public interface ICacheKey<T>{
	public String getCacheKey(T t);
}
