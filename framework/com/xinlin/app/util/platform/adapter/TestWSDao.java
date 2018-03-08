package com.xinlin.app.util.platform.adapter;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.xinlin.app.util.JCDFXmlUtil;
import com.xinlin.app.util.platform.adapter.common.local.IWSDao;
import com.xinlin.app.util.platform.vo.WSVOTest;
 
/**
 * 处理具体WS功能点的业务类
 * Demo
 * 
 * @author JXQ
 * @date 2013-10-09
 *
 */
public class TestWSDao implements IWSDao{
	
	public String getJsonStrByInputParam(Serializable... inputParam) {
		Map<String, String> map=new HashMap<String, String>();
		 
		map.put("Name","name"+System.currentTimeMillis());						//名称
		map.put("status", "status"+System.currentTimeMillis());
		String resultStr = "";
		if("json".equalsIgnoreCase(String.valueOf(inputParam[inputParam.length-1])))
			resultStr = JSON.toJSONString(map);
		else
			try {
				resultStr =  JCDFXmlUtil.toXml(map);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
		System.out.println("resultStr  =======  "+resultStr);
		return resultStr;
	}


	public Map getJsonMapByInputParam(Serializable... inputParam) {
		Map<String, String> map=new HashMap<String, String>();
		 
		map.put("Name","name"+System.currentTimeMillis());							//名称
		map.put("status", "status"+System.currentTimeMillis());					//是否已开通
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
	
	/**
	 * 测试版本1.0
	 * 
	 * 返回Object
	 * 
	 * @param inputParam
	 * @return
	 * @version 1.0
	 */
	public Object getObjectByInputParam(Serializable... inputParam){
		List <WSVOTest> WSVOList = new ArrayList<WSVOTest>();
		if(inputParam != null){
			for(Serializable param : inputParam){
				WSVOTest vo = new WSVOTest();
				vo.setName((String)param);
				vo.setStatus("true");
				WSVOList.add(vo);
			}
		}
		return WSVOList;
	}

	public static void main(String args[]) throws InterruptedException {
		TestWSDao dao = new TestWSDao();
		String jsonStrByOrgCode = dao.getJsonStrByInputParam("518081");
		System.out.println(jsonStrByOrgCode);
	}


	
	


}
