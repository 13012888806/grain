package com.xinlin.app.util.platform.adapter.common.local;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import com.xinlin.app.util.StaticVar;

/**
 * 
 * 管理WS全局连接
 * 
 * @author JXQ
 * @date 2013-10-12
 *
 */
public final class WSManager implements Serializable {
	
	private static final long serialVersionUID = 6214790243416807050L;
	
	private static WSManager manager = new WSManager();
	private WSManager(){
	}
	
	public static WSManager getInstance(){
		if(manager == null){
			manager  = new WSManager();
		}
		return manager;
	}
	//WS连接用,记录在线连接数
	private static AtomicInteger visited_total = new AtomicInteger(0);
	
	/**
	 * 是否可访问，连接数是否超限
	 * 
	 * @return true 连接数超限制
	 * false 未超限制
	 */
	public synchronized static boolean isCongestion(){
		System.out.println("  check isCongestion ...");
		return visited_total.get()  > StaticVar.WS_MAX_NUM_VISITED;
	}

	/**
	 * 增加一个连接
	 * @return true 连接数超限制
	 * false 未超限制
	 */
	public synchronized static void IncrementVisited(){
		System.out.println(" Visited increment once ...");
		//测试用
		//部署时移交redis缓存管理
		visited_total.incrementAndGet() ;
	}
	
	/**
	 * 减少一个连接
	 */
	public synchronized static void DecrementVisited(){
		System.out.println(" Visited Decrement once ...");
		//测试用
		//部署时移交redis缓存管理
		visited_total.decrementAndGet();
	}
	
}
