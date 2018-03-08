package com.xinlin.app.util.platform.adapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xinlin.app.util.platform.adapter.common.local.IWSDao;
 
/**
 * 处理具体WS功能点的业务类
 * Demo
 * 
 * @author jxq
 * @date 2013-10-09
 *
 */
public class PoiWSDao implements IWSDao{
	
	public static final String POI_XY = "##";
	
	private static final String COMMUNITY="小区";
	private static final String MANSION="大厦";
	private static final String ADDRESS="地址";
	private static final String ROAD="道路";

	public String getJsonStrByInputParam(Serializable... inputParam) {
		Map<String, String> map=new HashMap<String, String>();
		 
		map.put("Name","test");						//名称
		map.put("status", "test");				//是否已开通
		return JSON.toJSONString(map);
	}


	public Map getJsonMapByInputParam(Serializable... inputParam) {
		Map<String, String> map=new HashMap<String, String>();
		 
		map.put("Name","test");						//名称
		map.put("status", "test");				//是否已开通
		return map;
	}
	
	
	
	/**
	 * 测试版本1.0
	 * 
	 * 返回xml格式String
	 * 
	 * @param inputParam
	 * @return
	 * @version 1.0
	 */
	public String getXmlStrByInputParam(Serializable... inputParam){
		// TODO generate xml output
		return null;
	}

	public static void main(String args[]) throws InterruptedException {
		PoiWSDao dao = new PoiWSDao();
		String jsonStrByOrgCode = dao.getJsonStrByInputParam("518081");
		System.out.println(jsonStrByOrgCode);
	}


	@Override
	public Object getObjectByInputParam(Serializable... inputParam) {
		// TODO Auto-generated method stub
		return null;
	}


	
	


}
