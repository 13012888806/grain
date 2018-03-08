package com.xinlin.app.util.platform.jedis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import com.xinlin.app.util.StaticVar;

/**
 * redis对象工厂
 * redis有关对象调用，由此类对象生成
 * 
 * 
 * @author: jxq
 * @date: 2013-10-11
 *
 */
public class JedisObjectFactory implements ApplicationContextAware {
	
	private static final long serialVersionUID = -1L;
	
	private static ApplicationContext context ;
	
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

	 /**
	  * Jedis连接池
	  */
	 @Autowired
	 private static JedisPool jedisPool;
	 
	 
	 public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	
	 /**
	  * 获取Jedis连接代理
	  * @return
	  * @throws JedisException
	  */
	 public static Jedis getJedisInstance() throws JedisException{
		 jedisPool = getJedisPoolInstance();
		 Jedis redis = (Jedis)jedisPool.getResource();
		 if(redis == null){
			 throw new JedisException("Jedis can not be load from JedisPool ...");
		 }
		 return redis;
	 }
	 
	 /**
	  * 获取Jedis连接池
	  * @return
	  * @throws JedisException
	  */
	 public static JedisPool getJedisPoolInstance() throws JedisException{
		 if(jedisPool == null){
			 jedisPool = (JedisPool)context.getBean(StaticVar.JEDIS_POOL_SPRING_KEY);
		 }
		 if(jedisPool == null){
			 throw new JedisException("JedisPool can not be initialized ...");
		 }
		 
		 return jedisPool;
		 
	 }
	 
}
