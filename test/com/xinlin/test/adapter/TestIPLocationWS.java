package com.xinlin.test.adapter;


import org.junit.Test;

import com.xinlin.app.util.platform.adapter.common.WSService;
import com.xinlin.app.util.platform.adapter.common.remote.RemoteWSOp;


/**
 * 随机中文字符  WS测试
 * @author jxq
 *
 */
public class TestIPLocationWS {
	
	@Test
	public void testWSOpInputXml() throws Exception {
		// http://webservice.webxml.com.cn/WebServices/RandomFontsWebService.asmx?wsdl
		String wsUrl = // "http://webservice.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl";
				 WSService.getWSDefByServiceName("getIpAddress");
		
		String xmlData = "<q0:getCountryCityByIp> "
					 + "<q0:theIpAddress>"
					 + "61.129.65.58" 
					 + "</q0:theIpAddress>" 
					 + "</q0:getCountryCityByIp>";
					 //+" </soapenv:Body>";
		
		//String userId = "6fa98ef3485c427a822cf0c5987b4062";
		
		//调用接口
		String resultXML = RemoteWSOp.InvokeRemoteWSWithXml(wsUrl, xmlData);
		
		System.out.println("resultXML  : ====="+resultXML.replaceAll("<", "\n<"));
			//RemoteWSOp.pringXML(resultXML);
		
	}
}
