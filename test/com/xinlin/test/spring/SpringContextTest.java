package com.xinlin.test.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextTest {
	public static ApplicationContext context;
	static{
		//ClassPathXmlApplicationContext
		context = new ClassPathXmlApplicationContext("jcdf-springmvc-config.xml","jcdf-context.xml");
		//for junit test
		//context = new ClassPathXmlApplicationContext("jcdf-springmvc-config.xml","jcdf-context-test.xml");
	}
	
	@Test
	public void testSpring(){
		Assert.assertNotNull(context);
	}
	
}
