package com.xinlin.app.util.platform.adapter.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;

import com.xinlin.app.util.JCDFDateUtil;
import com.xinlin.app.util.StaticVar;
import com.xinlin.app.util.platform.adapter.common.local.WSManager;

/**
 * 拦截器
 * 
 * @author JXQ
 * @date 2013-10-12
 * 
 *   1、引入目标类
 *   2、引入全局WS管理
 *   3、通过构造函数给目标类和WS管理类赋值
 *
 */
public final class WSInterceptor implements InvocationHandler{
	
	private Object target;//目标类
	private WSManager manager;//引入全局管理类
	
	public WSInterceptor(Object target,WSManager manager){
		this.target = target;
		this.manager = manager;
	}
	

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//this.transaction.beginTransaction();
		//校验参数个数，是否超过限制范围
		if(args != null && args.length >= StaticVar.WS_MAX_NUM_ONETIME){
			return StaticVar.WS_QUERY_PARAM_OVERCOUNT;
		}
		
		System.out.print("method :"+method+" invoked ....");
		//打印日期时间戳
		JCDFDateUtil.printlnSysTime();
		
		//校验连接数，是否超过限制
		boolean iscongestion = manager.isCongestion();
		
		
		
		//调用目标类的方法
		Object obj = null;
		if(!iscongestion){
			//当前访问数+1
			manager.IncrementVisited();
			obj = method.invoke(this.target, args);
			//访问完毕，当前访问数-1
			manager.DecrementVisited();
		}else{
			obj = StaticVar.WS_ONLINE_TOTAL_OVERCOUNT;
		}
		//this.transaction.commit();
		return obj;
	}

}
