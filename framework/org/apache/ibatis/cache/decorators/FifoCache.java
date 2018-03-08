/*
 *    Copyright 2009-2012 The MyBatis Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.cache.decorators;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import com.xinlin.app.util.platform.jedis.JedisObjectFactory;
import com.xinlin.app.util.platform.jedis.JedisOpExecuter;


/*
 * FIFO (first in, first out) cache decorator
 * Mybatis二级缓存 ， 覆盖FIFO的默认缓存
 * 结合redis平台的 自定义实现
 * @author jxq
 */
public class FifoCache implements Cache {

  private final Cache delegate;
  //private final LinkedList<Object> keyList;
  private int size;
  
  
  	private static Logger logger = LoggerFactory.getLogger(FifoCache.class);
	
	private ApplicationContext context = null;
	
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(); 
	  
	
  public FifoCache(Cache delegate) {
    this.delegate = delegate;
    //this.keyList = new LinkedList<Object>();
    this.size = 1024;
  }

  @Deprecated
  public FifoCache(){
	  delegate=null;
  }
  
  public String getId() {
    return delegate.getId();
  }

  public int getSize() {
    return delegate.getSize();
  }

  public void setSize(int size) {
    this.size = size;
  }

/*  public void putObject(Object key, Object value) {
	  System.out.println(" fifo &&&&&&&&&&&&&&");
    cycleKeyList(key);
    delegate.putObject(key, value);
  }

  public Object getObject(Object key) {
	  System.out.println(" fifo &&&&&&&&&&&&&&");
    return delegate.getObject(key);
  }

  public Object removeObject(Object key) {
	  System.out.println(" fifo &&&&&&&&&&&&&&");
    return delegate.removeObject(key);
  }*/

/*  public void clear() {
    delegate.clear();
    keyList.clear();
  }*/
  
  	public void clear() {
			JedisOpExecuter.clear();
	}

  
	/**
	 * Mybatis,往redis缓存放数据
	 */
	public void putObject(Object key, Object value) {
		  System.out.println("jedis fifo inited ... ");
		  JedisOpExecuter.putSingleObject(key, value);
		//redisClient.set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));
	}

	
	/**
	 * Mybatis ,从redis缓存取数据
	 * @param redisClient
	 * @param key
	 * @param value
	 */
	public Object getObject(Object key) {
		  System.out.println("jedis getObject fifo ...");
		return JedisOpExecuter.getSingleObject(key);
	}

	/**
	 * Mybatis，在redis缓存中，删除数据
	 * @param key
	 */
	public Object removeObject(Object key) {
		System.out.println("removeObject fifo ");
		return JedisOpExecuter.removeSingleObject(key);
	}

  
  
  public ReadWriteLock getReadWriteLock() {
    return delegate.getReadWriteLock();
  }

 /* private void cycleKeyList(Object key) {
    keyList.addLast(key);
    if (keyList.size() > size) {
      Object oldestKey = keyList.removeFirst();
      delegate.removeObject(oldestKey);
    }
  }*/
  
  
  
	
	/**
	 * 获取jedis代理
	 * @return
	 * @throws JedisException
	 */
  protected  static Jedis createReids() throws JedisException{
  	//JedisPool pool = new JedisPool(new JedisPoolConfig(), "10.20.14.117");
  	//return pool.getResource();
  	return JedisObjectFactory.getJedisInstance();
  }
  
  /**
   * 获取jedis连接池
   * @return
   * @throws JedisException
   */
  protected  static JedisPool getRedisPool() throws JedisException{
  	//JedisPool pool = new JedisPool(new JedisPoolConfig(), "10.20.14.117");
  	//return pool.getResource();
  	return JedisObjectFactory.getJedisPoolInstance();
  }
  

}
