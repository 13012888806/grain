package com.xinlin.app.util.platform.listener;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Endpoint;

import com.xinlin.app.util.StaticVar;
import com.xinlin.app.util.platform.adapter.PoiWS;
import com.xinlin.app.util.platform.adapter.common.WSService;

/**
 * 监听器
 * 动态发布WS服务
 * 
 * 
 * @author JXQ
 * @date 2013-10-12
 *
 */
public class WSListener implements ServletContextListener {
	private static String DEFAULT_URL = "http://10.20.14.91:11083/branch2poi";
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * 服务启动，自启动poi webservice服务
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		
		InputStream ins = this.getClass().getResourceAsStream(StaticVar.WS_PROP_LOCATION_DEFITION);  
		
		//获取本机节点IP
		List<String> res = new ArrayList<String>();
		Enumeration netInterfaces;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces
						.nextElement();
				// System.out.println("---Name---:" + ni.getName());
				Enumeration nii = ni.getInetAddresses();
				while (nii.hasMoreElements()) {
					ip = (InetAddress) nii.nextElement();
					if (ip.getHostAddress().indexOf(":") == -1) {
						res.add(ip.getHostAddress());
						System.out.println("Local Machine IP=" + ip.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		//本集群节点非WS定义节点，退出
		if(! res.contains(StaticVar.WSLOCATION)){
			return;
		}
		res = null;
		netInterfaces = null;
		//return (String[]) res.toArray(new String[0]);
		
		
		Properties prop = new Properties();
		try {
			prop.load(ins);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Set<String> urlNames = prop.stringPropertyNames();
		String publishURl = null;
		for(String urlName : urlNames){
			if(urlName == null || (urlName = urlName.trim()).length()==0) continue;
			publishURl = prop.getProperty(urlName);
			System.out.println("WS DEFINED : "+urlName+" : "+publishURl);
			//Endpoint.publish("http://10.20.14.91:5567/branch2poi", poiService);
			Endpoint.publish(publishURl, WSService.getClassByName(urlName));
		}
		
		
		//String publishURl =  prop.get("wsurl").toString(); 
		if(publishURl == null){ 
			publishURl = DEFAULT_URL;
		}
		
		//创建服务
		//PoiWS poiService =  new PoiWS();
		//发布WS服务
		//Endpoint.publish(publishURl, poiService);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//创建服务
		//PoiServiceImpl poiService =  new PoiServiceImpl();
		//ReflectTest.setWSDao("");
		PoiWS poiService =  new PoiWS();
		//发布WS服务
		//Endpoint.publish("http://10.20.14.91:5001/poiservice", ReflectUtil.getWSDaoByService(""));
		//System.out.println("&&&");
	}

}
