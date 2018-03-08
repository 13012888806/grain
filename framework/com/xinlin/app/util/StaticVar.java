package com.xinlin.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * 全局的静态常量
 * 
 * 
 * @author Chenxl
 * @date 2013-10-11
 * 
 */
public class StaticVar  implements Serializable {
	
	/**----------分页功能、分页插件常量---------**/
	/**
	 * 默认分页查询的起始页码(如果不指定，则从第一页开始查询)
	 * @author Chenxl
	 */
	public static int DEFAULT_PAGE_NO = 1;
	/**
	 * 默认分页内容大小(从配置文件中读取)
	 * @author Chenxl
	 */
	public static int DEFAULT_PAGE_SIZE = 15;
	/**
	 * 数据库类型：mysql、oracle
	 * @author Chenxl
	 */
	public static String DB_NAME = "mysql";
	/**
	 * 要拦截并进行分页处理的mybatis sql的id(用于分页插件)
	 * @author Chenxl
	 */
	public static String PAGE_SQL_ID_REGEX = ".*(P|p)ageQuery$";
	
	/**----------登录权限常量---------**/
	/**
	 * 新增用户的用户默认密码
	 * @author Chenxl
	 */
	public static String DEFAULT_PASSWORD="123456";
	/**
	 * 登录用户信息实体存放在session中的key
	 * @author Chenxl
	 */
	public static String LOGIN_USER_KEY="loginUser";
	/**
	 * redis中保存用户令牌的key前缀
	 * 用于设置单用户登录
	 */
	public static String REDIS_SESSION_KEY_PREFIX = "token_";
	/**
	 * redis保存用户资源权限编码的key前缀
	 * 用于缓存登录用户权限
	 */
	public static String REDIS_MENU_CODE_KEY_PREFIX = "menu_code_";
	/**
	 * 超级管理员(类型标示)
	 */
	public static int USER_TYPE_OF_SUPER_USER = 1;
	
	/* jedis 配置应用用key*/
	public static final String JEDIS_XINLIN_GIS_MYBATIS_GLOBAL = "jedis_xinlin_gis_mybatis_global";
	public static final String JEDIS_POOL_SPRING_KEY = "jedisPool";
	
	/** ws部署的节点定义*/
	public static final String WSLOCATION;
	public static final String WSLOCATION_KEY = "wslocation";
	/**ws错误提示信息*/
	public static final String WS_EMPTY_ERROR = "{\"error\":\"query params is empty\"}";
	
	//WS接口，定义单次访问最大参数量 50
	public static final int WS_MAX_NUM_ONETIME;
	public static final String WS_MAX_NUM_ONETIME_KEY = "ws_max_num_onetime";
	//在线连接数限制
	public static final int WS_MAX_NUM_VISITED;
	public static final String WS_MAX_NUM_VISITED_KEY = "ws_max_num_visited";
	
	//WS 通用配置文件名
	public static final String WS_PROP_LOCATION_DEFITION = "/webservice.properties";
	
	//外部WS定义xml文件名
	public static final String REMOTE_WS_XML_LOCATION;
	public static final String REMOTE_WS_XML_LOCATION_KEY = "remote_ws_xml_location";
	
	
	//文件上传，单个文件大小限制
	public static final int MAXIMUM_UPLOAD_SIZE;
	public static final String MAXIMUM_UPLOAD_SIZE_KEY = "maximum_upload_size";
	
	
	public static final String SYS_LOG_KEY = "user_log_global_key";
	public static final String SYS_LOG_MODULE_KEY = "user_log_global_module_key";
	public static final String SYS_LOG_ENABLED_KEY = "user_log_enabled_key";
	public static final String SYS_LOG_ENABLED = "user_log_enabled";
	/**
	 * 读配置文件内容
	 */
	static{
		InputStream in = null;
		try {
			/*
			String classpath = Configuration.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			classpath = classpath.substring(0, classpath.indexOf("WEB-INF/classes")+16)+"default.properties";
			System.out.println("Configuration.enclosing_method() classpath:  "+classpath);
			in = new FileInputStream(new File(classpath));*/
			
			// 加载default.properties配置文件
			Properties props = new Properties();
			
			in = StaticVar.class.getClassLoader().getResourceAsStream("jcdf-default.properties");
			props.load(in);
			WSLOCATION = props.getProperty(WSLOCATION_KEY);
			WS_MAX_NUM_ONETIME = Integer.parseInt(props.getProperty(WS_MAX_NUM_ONETIME_KEY));
			WS_MAX_NUM_VISITED = Integer.parseInt(props.getProperty(WS_MAX_NUM_VISITED_KEY));
			MAXIMUM_UPLOAD_SIZE = Integer.parseInt(props.getProperty(MAXIMUM_UPLOAD_SIZE_KEY));
			REMOTE_WS_XML_LOCATION = props.getProperty(REMOTE_WS_XML_LOCATION_KEY);
			
			//读取默认页大小
			DEFAULT_PAGE_SIZE = Integer.parseInt(props.getProperty("default_page_size"));
			//读取默认数据库类型
			DB_NAME = props.getProperty("dbname");
			//读取mybatis分页sql的id，用于mybatis底层分页插件拦截
			PAGE_SQL_ID_REGEX = props.getProperty("page_sql_id_regex");
			
			
			System.out.println("WSLOCATION:  "+WSLOCATION);
			System.out.println("MAXIMUM_UPLOAD_SIZE:  "+MAXIMUM_UPLOAD_SIZE);
			System.out.println("WS_MAX_NUM_VISITED:  "+WS_MAX_NUM_VISITED);
			System.out.println("REMOTE_WS_XML_LOCATION:  "+REMOTE_WS_XML_LOCATION);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
				try {
					if(in != null){
						in.close();
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}
	}
	
	//WS错误信息定义
	public static final String WS_QUERY_PARAM_OVERCOUNT = "{\"error\":\"the query params can not over "+(WS_MAX_NUM_ONETIME)+" one time..\"}";
	public static final String WS_ONLINE_TOTAL_OVERCOUNT = "{\"error\":\"current connector is over maxinum  "+(WS_MAX_NUM_VISITED)+" ,please wait..\"}";
	public static final String WS_SERVICE_INVALID = "{\"error\":\"the serive you visited is not exists ..\"}";

	
	public static final String TYPE_JSON = "json";
	public static final String TYPE_XML = "xml";
	
	//分隔符
	public static final String SEPARATOR = "_";
}
