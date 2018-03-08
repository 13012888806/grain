package com.xinlin.test.jedis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.xinlin.app.util.platform.jedis.JedisOpExecuter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:jcdf-springmvc-config.xml","classpath*:jcdf-context-test.xml"})
//@ContextConfiguration({"/jcdf-springmvc-config.xml","/jcdf-context-test.xml"})
public class JedisMultiTest {
	
		@Before
		public void testBefore(){
			 
		}
		
		@Test
		public void testInsertData(){
			Map<String ,String> map = new HashMap<String ,String>();
			map.put("key2", "value2");
			
			JedisOpExecuter.putSingleObject("jedis_test2", map);
			
			//jedisInstance.
			//System.out.println("test add");
		}
		
		@Test
		public void testDeleteData(){
			JedisOpExecuter.removeSingleObject("jedis_test");
		}
		
		@Test
		public void testgetData(){
			Map map = (Map)JedisOpExecuter.getSingleObject("jedis_test2");
			System.out.println("get value from redis: "+map.get("key2"));
		}
		
		@Test
		public void testMulti(){
			for(int i = 0; i < 100000;i++){
				Map<String ,String> map = new HashMap<String ,String>();
				map.put("key2", "value2 &&&&&&&&&&&&&");
				
				JedisOpExecuter.putSingleObject("jedis_test2", map);
				
				Map map2 = (Map)JedisOpExecuter.getSingleObject("jedis_test2");
				System.out.println("get value from redis: "+map2.get("key2"));
		
			}
		}
		

}