package com.xinlin.test.adapter;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.xinlin.app.util.platform.adapter.common.remote.RemoteWSOp;

/**
 * QQ在线状态WS 测试
 * @author jxq
 *
 */
public class TestQQOnlineWS {
	
	@Test
	public void testWSOpInputXml() throws Exception {
		//WSDL: http://webservice.webxml.com.cn/webservices/qqOnlineWebService.asmx?wsdl
		String wsUrl = 
				 "http://webservice.webxml.com.cn/webservices/qqOnlineWebService.asmx?wsdl";
		
		String xmlData = "<q0:qqCheckOnline>" +
				"<q0:qqCode>33561259</q0:qqCode> " +
				"</q0:qqCheckOnline>";
		
		
		
		//调用接口
		String resultXML = RemoteWSOp.InvokeRemoteWSWithXml(wsUrl, xmlData);
		
		System.out.println("resultXML  : ====="+resultXML.replaceAll("<", "\n<"));
	
		
	}
}
