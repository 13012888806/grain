package com.xinlin.app.util.platform.adapter.common.local;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 处理WS业务类，须遵循的接口定义
 * 在部署新WS功能点时，添加的功能类，须实现以下接口
 * 
 * @author JXQ
 * @date 2013-10-09
 *
 */
public interface IWSDao {
	/**
	 * 测试版本1.0
	 * 
	 * 返回Json格式String
	 * 
	 * @param inputParam
	 * @return
	 * @version 1.0
	 */
	public String getJsonStrByInputParam(Serializable... inputParam);

	/**
	 * 测试版本1.0 返回Map数据体
	 * 
	 * @param inputParam
	 * @return Map
	 * @version 1.0
	 */
	public Map getJsonMapByInputParam(Serializable... inputParam);
	
	/**
	 * 测试版本1.0
	 * 
	 * 返回Object
	 * 
	 * @param inputParam
	 * @return
	 * @version 1.0
	 */
	public Object getObjectByInputParam(Serializable... inputParam);
	
}
