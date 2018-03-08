package com.xinlin.app.util.platform.adapter.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.xinlin.app.util.StaticVar;
import com.xinlin.app.util.platform.adapter.common.local.IWSDao;
import com.xinlin.app.util.platform.adapter.common.local.WSManager;
import com.xinlin.app.util.platform.vo.InterfaceConfigVO;
import com.xinlin.app.util.platform.vo.InterfaceContent;


/**
 * WS服务类
 * 
 * 
 * @author JXQ
 * @date 2013-10-12
 * 
 * 对外部WS配置interFaceConfig.xml
 * 增加全局管理方法
 * @modified by jxq
 * @date 2013-10-23
 *
 */
public final class WSService {

	//记录interFaceConfig.xml内容，以key-value值记录
	//测试部署用，后续移交给redis管理
	private static Map<String, String> RemoteWS = new HashMap<String, String>();
	
	static {
		//读取interFaceConfig.xml
		//生成key-value格式内容
		JAXBContext context = null;
		FileReader fr  = null;
		Unmarshaller um =null;
		InterfaceContent wss =null;
		try {
			context = JAXBContext.newInstance(InterfaceContent.class);
			String classpath = WSService.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			int joinIndex;
			if((joinIndex = classpath.indexOf("com")) != -1)
			{
				classpath = classpath.substring(0, joinIndex)+ StaticVar.REMOTE_WS_XML_LOCATION;
			}else{
				classpath += StaticVar.REMOTE_WS_XML_LOCATION;
			}
			fr = new FileReader(new File(classpath));
			um = context.createUnmarshaller();
			wss = (InterfaceContent)um.unmarshal(fr);
			Iterator ite = wss.iterator();
			System.out.println("==============系统外部业务WS列表==== 开始===============");
			while(ite.hasNext()){
				InterfaceConfigVO wsVO = (InterfaceConfigVO)ite.next();
				RemoteWS.put(wsVO.getServiceName().trim(), wsVO.getRequestUrl().trim());
				System.out.println(wsVO.getRequestUrl());
			}
			System.out.println("==============系统外部业务WS列表==== 结束===============");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			context = null;
			um = null;
			wss = null;
			if(fr !=null){
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	

	/**
	 * 根据类路径，获取class类信息对象
	 * 
	 * @param classUrl
	 * @return
	 */
	public static Object getClassByName(String classUrl){
		Class<?> demo=null;
		Object returnVal = null;
		
		try{
			demo=Class.forName(classUrl);
			returnVal=  demo.newInstance();
		}catch (Exception e) {
			e.printStackTrace();
		}
		//保存所有的接口
		/*Class<?> intes[]=demo.getInterfaces();
		for (int i = 0; i < intes.length; i++) {
			System.out.println("实现的接口   " + intes[i].getName());
		}*/
		
		return returnVal;
		
	}
	
	/**
	 * WS服务用
	 * 根据WS接口url，获取对应Dao类代理对象
	 * 
	 * @param classUrl
	 * @return
	 */
	public static IWSDao getWSDaoByService(String classUrl){
		Class<?> demo=null;
		IWSDao iWSDao = null;
		Object target = null;
		
		try{
			demo=Class.forName(classUrl+"Dao");
			target=  demo.newInstance();
		}catch (Exception e) {
			e.printStackTrace();
		}
		//保存所有的接口
		/*Class<?> intes[]=demo.getInterfaces();
		for (int i = 0; i < intes.length; i++) {
			System.out.println("实现的接口   " + intes[i].getName());
		}*/
		if(target != null && target instanceof IWSDao ){
			//iWSDao = (IWSDao) val;
			WSInterceptor interceptor = new WSInterceptor(target, WSManager.getInstance());
			/**
			 * 1、目标类的类加载器
			 * 2、目标类实现的所有的接口
			 * 3、拦截器
			 */
			iWSDao = (IWSDao)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), interceptor);

		}
		return iWSDao;
	}
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(getWSDefByServiceName("crm"));
	}
	
	/**
	 * 根据服务名称，读取wsdl地址
	 * 供系统其他部分 调用
	 * 
	 * @return
	 */
	public static String getWSDefByServiceName(String serviceName){
		String resultUrl = null;
		if(RemoteWS != null){
			resultUrl = RemoteWS.get(serviceName);
		}
		return resultUrl;
		
	}
}
