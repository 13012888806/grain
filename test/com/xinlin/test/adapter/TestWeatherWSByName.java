package com.xinlin.test.adapter;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.xinlin.app.util.platform.adapter.common.WSService;
import com.xinlin.app.util.platform.adapter.common.remote.RemoteWSOp;

/**
 * 天气预报WS 远程调用测试
 * @author jxq
 *
 */
public class TestWeatherWSByName {
	
	@Test
	public void testWSOpInputXml() throws Exception {
		//
		String wsUrl = 
				 WSService.getWSDefByServiceName("weatherReport");
		
		String xmlData = "<q0:getWeather> "
					  + "<q0:theCityCode>shanghai</q0:theCityCode> "
					  + "</q0:getWeather>";
		
		//String userId = "6fa98ef3485c427a822cf0c5987b4062";
		
		//调用接口
		String resultXML = RemoteWSOp.InvokeRemoteWSWithXml(wsUrl, xmlData);
		
		System.out.println("resultXML  : ====="+resultXML.replaceAll("<", "\n<"));
		
	}
}
