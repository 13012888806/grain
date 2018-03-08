package com.xinlin.app.util.platform.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.util.JCDFFileUtil;
import com.xinlin.app.util.JCDFStringUtil;
import com.xinlin.app.util.StaticVar;
import com.xinlin.app.util.platform.plugin.util.DataReader;
import com.xinlin.app.util.platform.plugin.util.ExcelReader;

/**
 * 数据导入插件
 * 
 * @author Jiangshui
 * @date	2013-10-29
 */
public class DataImportPlugin {
	/**系统导出功能的所有参数配置*/
	public static Map<String, String> importConfig;
	
	/**
	 * 默认构造函数，负责初始化导入配置参数
	 */
	public DataImportPlugin() {
		if(null == importConfig) {
			initImportConfig();
		}
	}
	
	/**
	 * 解析出上传的导入文件并保存
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	public Message parseFile(MultipartFile file, HttpServletRequest request) {
		Message msg = null;
		if (null != file && file.getSize() > 0) {
	        //保存  
	        try { 
	        	String tempfilePath = importConfig.get("import.tempfilePath");
				File importFile = new File(tempfilePath, UUID.randomUUID().toString()+".xls");  
		        if(!importFile.exists()){  
		        	importFile.mkdirs();  
		        }
	        	file.transferTo(importFile);
	        	msg = this.parseFile(importFile, request);
	        } catch (Exception e) {  
	           e.printStackTrace();
	           msg = new Message(false, "导入文件解析失败！");
	        }
		} else {
			msg = new Message(false, "上传文件为空，解析失败！");
		}
		return msg;
	}
	
	/**
	 * 
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	public Message parseFile(File file, HttpServletRequest request) {
		Message msg = null;
		DataReader dr = null;
		HashMap<String, String> params = this.parseParamsFromRequest(request);
		String importId = params.get("importId");
		String className = importConfig.get(importId+".className");
		String methodName = importConfig.get(importId+".methodName");
		String beanName = importConfig.get(importId+".beanName");
		try {
			dr = new ExcelReader();
			dr.setFile(file);
			ArrayList<Object[]> list = dr.getRows(0, dr.countTotalRow()-1);
			WebApplicationContext wtx = RequestContextUtils.getWebApplicationContext(request);
			Object processor = null;
			if (JCDFStringUtil.isNotNullAndEmpty(beanName) && JCDFStringUtil.isNotNullAndEmpty(methodName)) {
				//从spring容器中获取其托管的bean
				processor = wtx.getBean(beanName);
			} else if(JCDFStringUtil.isNotNullAndEmpty(className) && JCDFStringUtil.isNotNullAndEmpty(methodName)) {
				//从spring容器中获取其托管的bean
				processor = wtx.getBean(Class.forName(className));
			} else {
				System.out.println("导入配置错误：beanName+methodName 或 className+methodName，请二选一配置完整");
			}
			//如果按照配置找到了对应的数据导入处理器，则处理
			if(null != processor) {
				Method method = (processor.getClass()).getMethod(methodName, params.getClass(), list.getClass());
				Object result = method.invoke(processor, params, list);
				if(null != result && result instanceof Message) {
					msg = (Message)result;
				}
			}
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(null == msg)msg = new Message(false, "文件导入解析失败！");
		return msg;
	}
	
	/**
	 * 考虑到底层反射调用的需要，这里需将获得参数map转化成java.util.HashMap.
	 *（request.getParameterMap()获得参数集合的本质是：org.apache.catalina.util.ParameterMap<String, String[]>）
	 * 
	 * @param request
	 * @return
	 */
	public HashMap<String, String> parseParamsFromRequest(HttpServletRequest request) {
		Map parameterMap = request.getParameterMap();
		HashMap<String, String> params = new HashMap<String, String>();
		for (Object key : parameterMap.keySet()) {
			if(null != parameterMap.get(key)) {
				String[] values = (String[])parameterMap.get(key);
				if(values.length > 0) {
					params.put(key.toString(), values[0]);
				}
			}
		}
		return params;
	}
	
	/**
	 * 读配置文件内容
	 * 
	 */
	public void initImportConfig() {
		InputStream in = null;
		String rootPath = JCDFFileUtil.getWebRealPath("/");
		if(null == importConfig)importConfig = new HashMap<String,String>();
		try {
			// 加载导出功能配置文件(config/inport/import.properties)
			Properties props = new Properties();
			in = StaticVar.class.getClassLoader().getResourceAsStream("inport/import.properties");
			props.load(in);
			Set<Object> keys = props.keySet();
			for (Object key : keys) {
				String value = (new String(props.getProperty(key.toString()).getBytes("ISO-8859-1"),"utf-8")).trim();
				//文件导出临时文件存放路径需要特殊处理
				if("import.templateFilePath".equals(key.toString()) || "import.tempfilePath".equals(key.toString())) {
					//如果是相对项目根目录的路径，则加上项目根目录
					if(value.startsWith("root:")) {
						value = value.replace("root:\\", "").replace("root:/", "").replace("root:", "");
						value = (value.endsWith("/")||value.endsWith("\\")) ? value : value+"/";
						value = rootPath + ((rootPath.endsWith("/")||rootPath.endsWith("\\")) ? "" : "/") + value;
					}
					//为兼容linux系统，路径分隔符统一采用/
					value = value.replaceAll("\\\\", "/");
				}
				importConfig.put(key.toString(), value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(in != null){
					in.close();
					in = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
