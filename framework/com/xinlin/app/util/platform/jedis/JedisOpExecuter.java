package com.xinlin.app.util.platform.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * redis操作类
 * redis有关操作，由此类对象封装，供其他部分调用
 * 此类不可继承重写，对外开放方法，可静态调用
 * 
 * @author: jxq
 * @date: 2013-10-11
 *
 */
public final class JedisOpExecuter {
	
	private static final long serialVersionUID = -1L;
	
	//private static Log logger = LogFactory.getLog(JedisMybatisSecCache.class);
	/**
	 * 构造函数，禁止实例化
	 */
	private JedisOpExecuter(){
		
	}
	
	/**
	 * 由jedis代理，向redis缓存放数据
	 * 
	 * @param key
	 * @param value
	 * @throws JedisException
	 */
	public static void putObjectArray(Object []key, Object []value)  throws JedisException{
		if(key == null || value==null) {return;}
		if(key.length != value.length){
			throw new JedisException("key's length should equal with value's length");
		}
		try{
			JedisPool jedisPool = JedisObjectFactory.getJedisPoolInstance();
			Jedis redisClient = jedisPool.getResource();
			for(int i = 0,il = key.length; i<il; i++){
				JedisOpExecuter.putObject(jedisPool, redisClient, key, value);
			}
		}catch(Exception e){
			//logger.debug("putObject:"+key+"="+value+" &failed Error msg:  "+e.getMessage());
			throw new JedisException(e.getMessage());
		}
	}
	
	/**
	 * 由jedis代理，从redis缓存取数据
	 * @param redisClient
	 * @param key
	 * @return
	 */
	public static Object[] getObjectArray(Jedis redisClient, Object []key) throws JedisException{
		Object []value = new Object[key.length];
		try{
			JedisPool jedisPool = JedisObjectFactory.getJedisPoolInstance();
			Jedis jedisInstance = jedisPool.getResource();
			for(int i = 0,il=key.length; i<il; i++){
				value[i]=JedisOpExecuter.getObject(jedisPool, redisClient, key);
			}
		}catch(Exception e){
			//logger.debug("getObject:key="+key+" &failed Error msg:  "+e.getMessage());
			throw new JedisException(e.getMessage());
		}
		return value;
	}
	
	
	/**
	 * 由jedis代理，从redis缓存删数据
	 * 
	 * @author JXQ
	 * 
	 * @param key
	 * @return
	 * @throws JedisException
	 */
	public static Object[] removeObjectArray(Object []key) throws JedisException{
		Object res[] = null;
		try{
			if(key != null && key.length > 0){
				JedisPool jedisPool = JedisObjectFactory.getJedisPoolInstance();
				Jedis redisClient = jedisPool.getResource();
				for(int i=0,il=key.length; i<il; i++){
					res[i]= JedisOpExecuter.removeObject(jedisPool, redisClient, key[i]);
				}
			}
		}catch(Exception e){
			//logger.debug("removeObject:key="+key+" &failed Error msg:  "+e.getMessage());
			throw new JedisException(e.getMessage());
		}
		return res;
	}
	
	/**
	 * 由jedis代理，向redis缓存放数据
	 * 
	 * @param key
	 * @param value
	 * @throws JedisException
	 */
	public static void putSingleObject(Object key, Object value)  throws JedisException{
		try{
			if(key != null){
				JedisPool jedisPool = JedisObjectFactory.getJedisPoolInstance();
				Jedis redisClient = jedisPool.getResource();
				JedisOpExecuter.putObject(jedisPool, redisClient, key, value);
			}
		}catch(Exception e){
			//logger.debug("putObject:"+key+"="+value+" &failed Error msg:  "+e.getMessage());
			throw new JedisException(e.getMessage());
		}
	}
	
	/**
	 * 由jedis代理，从redis缓存取数据
	 * @param redisClient
	 * @param key
	 * @return
	 */
	public static Object getSingleObject(Object key) throws JedisException{
		Object value = null;
		try{
			if(key != null){
				JedisPool jedisPool = JedisObjectFactory.getJedisPoolInstance();
				Jedis redisClient = jedisPool.getResource();
				value=JedisOpExecuter.getObject(jedisPool, redisClient, key);
			}
		}catch(Exception e){
			//logger.debug("getObject:key="+key+" &failed Error msg:  "+e.getMessage());
			throw new JedisException(e.getMessage());
		}
		return value;
	}
	
	
	/**
	 * 由jedis代理，从redis缓存删数据
	 * 
	 * @author JXQ
	 * 
	 * @param key
	 * @return
	 * @throws JedisException
	 */
	public static Object removeSingleObject(Object key) throws JedisException{
		Object res = null;
		try{
			if(key != null){
				JedisPool jedisPool = JedisObjectFactory.getJedisPoolInstance();
				Jedis redisClient = jedisPool.getResource();
				res= JedisOpExecuter.removeObject(jedisPool, redisClient, key);
			}
		}catch(Exception e){
			//logger.debug("removeObject:key="+key+" &failed Error msg:  "+e.getMessage());
			throw new JedisException(e.getMessage());
		}
		return res;
	}

	
	/**
	 * redis缓存操作
	 * 根据key值存放数据
	 * 
	 * @author JXQ
	 * 
	 * @param jedisPool
	 * @param redisClient
	 * @param key
	 * @param value
	 */
	private static void putObject(JedisPool jedisPool, Jedis redisClient, Object key, Object value) {
		try {
			redisClient.set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));
		} catch (Exception e) {
			JedisOpExecuter.returnBrokenJedisInstance(jedisPool, redisClient);
		} finally {
			JedisOpExecuter.returnJedisInstance(jedisPool, redisClient);
		}
		// redisClient.set(SerializeUtil.serialize(key.toString()),
		// SerializeUtil.serialize(value));
	}
	
	/**
	 * redis缓存操作
	 * 根据key值取数据
	 * 
	 * @author jxq
	 * 
	 * @param jedisPool
	 * @param redisClient
	 * @param key
	 * @return
	 * @throws JedisException
	 */
	private static Object getObject(JedisPool jedisPool,Jedis redisClient, Object key) throws JedisException{
		Object value = null;
		try {
			value= SerializeUtil.unserialize(redisClient.get(SerializeUtil.serialize(key.toString())));
		} catch (Exception e) {
			// 出现异常，清空redis连接
			JedisOpExecuter.returnBrokenJedisInstance(jedisPool, redisClient);
		} finally {
			// 将redis代理连接，返回给连接池
			JedisOpExecuter.returnJedisInstance(jedisPool, redisClient);
		}
		// SerializeUtil.unserialize(redisClient.get(SerializeUtil.serialize(key.toString())));
		return value;
	}
	
	
	/**
	 * 
	 * @param jedisPool
	 * @param redisClient
	 * @param key
	 * @return
	 * @throws JedisException
	 */
	public static Object removeObject(JedisPool jedisPool, Jedis redisClient,Object key) throws JedisException{
		Object obj = null;
		try {
			obj = redisClient.expire(SerializeUtil.serialize(key.toString()),0);
		} catch (Exception e) {
			JedisOpExecuter.returnBrokenJedisInstance(jedisPool, redisClient);
		} finally {
			JedisOpExecuter.returnJedisInstance(jedisPool, redisClient);
		}
		return obj;
	}
	
	
	
	
	/**
	 * 刷新redis缓存
	 * 清空当前打开的数据库
	 * 
	 * 
	 * @param jedisPool
	 * @param redisClient
	 */
	public static void clear(){
		JedisPool jedisPool = null;
		Jedis redisClient = null;
		try {
			jedisPool = JedisObjectFactory.getJedisPoolInstance();
			if(jedisPool == null){
				throw new JedisException("jedis pool can not be inited ...");
			}
			redisClient  = jedisPool.getResource();
			redisClient.flushDB();
		} catch (Exception e) {
			JedisOpExecuter.returnBrokenJedisInstance(jedisPool, redisClient);
		} finally {
			JedisOpExecuter.returnJedisInstance(jedisPool, redisClient);
		}
	}
	
	
	
	/**
	 * 刷新redis缓存
	 * 清空当前打开的数据库
	 * 
	 * 
	 * @param jedisPool
	 * @param redisClient
	 */
	private static void clear(JedisPool jedisPool, Jedis redisClient){
		try {
			redisClient.flushDB();
		} catch (Exception e) {
			JedisOpExecuter.returnBrokenJedisInstance(jedisPool, redisClient);
		} finally {
			JedisOpExecuter.returnJedisInstance(jedisPool, redisClient);
		}
	}
	
	
	
	 /**
	  * Jedis处理业务正常，业务处理完把Jedis代理返回给JedisPool
	  * @param pool
	  * @param resource
	  */
	 private static void returnJedisInstance(JedisPool pool, Jedis resource){
		 pool.returnResource(resource);
	 }
	 
	 /**
	  * Jedis出现异常时的处理
	  * @param pool
	  * @param resource
	  */
	 private static void returnBrokenJedisInstance(JedisPool pool, Jedis resource){
		 pool.returnBrokenResource(resource);
	 }
	 
}
