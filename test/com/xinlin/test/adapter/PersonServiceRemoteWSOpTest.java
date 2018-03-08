package com.xinlin.test.adapter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.xinlin.app.util.platform.adapter.common.remote.RemoteWSOp;


/**
 * 测试com.xinlin.test.adapter.PersonService 发布的服务
 * 
 * @author jxq
 * @date 2013-10-16
 *
 */
public class PersonServiceRemoteWSOpTest {

	
	
	@Test
	public void testWSOpInputXml() throws Exception {
			//参数一
			String wsUrl = "http://127.0.0.1:5679/PersonWS?wsdl"; 
			
			String xmlData = "<q0:helloOp>"
							  + "<name>admin</name> "
							  + "<age>10</age> "
							  + "</q0:helloOp>";
		
		//String userId = "6fa98ef3485c427a822cf0c5987b4062";
		
		//调用接口
		String resultXML = RemoteWSOp.InvokeRemoteWSWithXml(wsUrl, xmlData);
		
		System.out.println("resultXML  : ====="+resultXML.replaceAll("<", "\n<"));

	}
	
}
