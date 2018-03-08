package com.xinlin.test.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class ExportSeviceTest {

	@Test
	public void getDataFromMap() {
		Map map = new HashMap();
		map.put("11", "daf");
		map.put("22", "的萨芬");
		StringBuffer buffer = new StringBuffer();
		 for (Object key : map.keySet()) {
			 System.out.println("key= "+ key.toString() + " and value= " + map.get(key));
		 }  
	}
	
}
