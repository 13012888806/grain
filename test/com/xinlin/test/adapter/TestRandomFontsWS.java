package com.xinlin.test.adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.xinlin.app.util.platform.adapter.common.remote.RemoteWSOp;


/**
 * 随机中文字符  WS测试
 * @author jxq
 *
 */
public class TestRandomFontsWS {
	
	@Test
	public void testWSOpInputXml() throws Exception {
		// http://webservice.webxml.com.cn/WebServices/RandomFontsWebService.asmx?wsdl
		String wsUrl = " http://webservice.webxml.com.cn/WebServices/RandomFontsWebService.asmx?wsdl";
		
		String xmlData = "<q0:getChineseFonts>"
				+"<q0:byFontsLength>5</q0:byFontsLength> "
				+"</q0:getChineseFonts>";
		
		
		//String userId = "6fa98ef3485c427a822cf0c5987b4062";
		
		//调用接口
		String resultXML = RemoteWSOp.InvokeRemoteWSWithXml(wsUrl, xmlData);
		
		System.out.println("resultXML  : ====="+resultXML.replaceAll("<", "\n<"));
	
		
	}
}
