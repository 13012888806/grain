package com.xinlin.app.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;

/**
 * 导出数据库访问接口
 * 
 * @author Jiangshui
 * @date	2013-10-25
 */
public interface ExportDao {

	/**
	 * 动态调用指定的mapper类的指定方法，查询数据，用于数据导出
	 * 
	 * @param className	mapper类名称，要指定全名，包括包名
	 * @param methodName	mapper中用于查询数据的方法名
	 * @param params	数据查询参数
	 * 
	 * @return
	 * 
	 * @throws ClassNotFoundException 
	 * @throws BeansException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public abstract List<Map<String, Object>> exportData(String className,
			String methodName, Object params) throws BeansException,
			ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException;

	/**
	 * 根据指定的mapper中sql语句的id，动态调用并获取查询结果，用于数据导出
	 * 
	 * @param mapperId	mapper中用于查询导出数据的sql语句的id
	 * @param params	查询参数
	 * @return
	 */
	public abstract List<Map<String, Object>> exportData(String mapperId, Object params);

}