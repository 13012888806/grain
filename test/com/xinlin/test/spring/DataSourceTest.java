package com.xinlin.test.spring;

import java.sql.DriverManager;

import org.junit.Assert;
import org.junit.Test;


public class DataSourceTest {

	@Test
	public void testMysqlJdbcConn() throws Exception{
		 String dbUrl = "jdbc:mysql://127.0.0.1:3306/mvcdb"; 
		 Class.forName ("com.mysql.jdbc.Driver"); 
		 java.sql.Connection connection = DriverManager.getConnection (dbUrl, "root", "root"); 
		 Assert.assertNotNull(connection);
	}
	
}
