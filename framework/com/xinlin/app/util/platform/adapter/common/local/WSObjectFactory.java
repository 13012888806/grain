package com.xinlin.app.util.platform.adapter.common.local;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.xinlin.app.util.StaticVar;

/**
 * WS接口适配的工厂类
 * 供发布本地服务使用
 * 
 * 
 * @author JXQ
 * @date 2013-10-09
 *
 */
public final class WSObjectFactory implements Serializable{

	private static final long serialVersionUID = -1L;
	
	//public static PoiWSDao poiWSDao = new PoiWSDao();
	
	//public static PoiService poiService = new PoiServiceImpl();
	
	//private IWSDao iWSDao;
	/**
	 * 业务类对象池
	 * 保存业务类，保存在跟线程绑定的缓存内，用后清空
	 */
	//private static ThreadLocal localDaoKeep = new ThreadLocal();
	
	//线程数量
	private final static int POOL_SIZE = 200;
	
	//定义线程池
	private  static ExecutorService exec = Executors.newFixedThreadPool(POOL_SIZE);
	/**
	 * 返回处理池实例
	 * @return
	 */
	public static ExecutorService getPool(){
		if(exec == null || exec.isShutdown())
		synchronized(exec){	
			exec= Executors.newFixedThreadPool(POOL_SIZE);
		}
		return exec;
	}
	
	/**
	 * 获取业务处理实例类
	 * @return
	 *//*
	public static IWSDao getiWSDao() {
		IWSDao iWSDao = null;
		synchronized (localDaoKeep) {
			if (localDaoKeep == null) {
				localDaoKeep = new ThreadLocal();
			}
		}
		Object obj = localDaoKeep.get();
		if(obj != null && obj instanceof IWSDao){
			iWSDao = (IWSDao)obj;
		}
		return iWSDao;
	}*/
	
	/**
	 * 设置业务处理实例类
	 * @param iWSDao
	 */
	/*public static void setiWSDao(IWSDao iWSDao) {
		synchronized (localDaoKeep) {
			if (localDaoKeep == null) {
				localDaoKeep = new ThreadLocal();
			}
		}
		localDaoKeep.set(iWSDao);
	}*/
	

}
