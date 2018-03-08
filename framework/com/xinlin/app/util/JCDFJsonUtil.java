package com.xinlin.app.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.beanutils.ConvertUtils;

/**
 * ClassName: JCDFJsonUtil 
 * @Description: Json转化
 * @author Chenxl
 * @date 2015-6-9
 */
public final class JCDFJsonUtil {
	 /**
		 * 将对象格式化成Json字符串并返回
		 * 
		 * @param obj	需要格式化的对象
		 * @param dateFmt	格式化日期字段时采用的“模式”
		 * 
		 * @return	如果obj为空，则返回空的json对象，即“{}”，如果是字符串则返回字符串本身，
		 * 			如果是数组或者集合类对象，则采用JSONArray对象格式化成json中的数组对象，如果不满足以上情况，则默认全部采用JSONObject格式化成json普通对象
		 */
		public static String fromObjctToJson(Object obj, String dateFmt) {
			if(null == dateFmt || "".equals(dateFmt.trim()))dateFmt = "yyyy-MM-dd";
			JsonConfig config = new JsonConfig();  
			JCDFDateUtil processor = new JCDFDateUtil(dateFmt);
	        config.registerJsonValueProcessor(java.util.Date.class, processor);
	        config.registerJsonValueProcessor(java.sql.Date.class, processor);
			String json = null;
			if (null == obj) {
				json = "{}";
			} else if (obj  instanceof String) {
				json = obj.toString();
			} else if (obj.getClass().isArray() || obj instanceof Collection) {
				json=JSONArray.fromObject(obj,config).toString();
			} else {
				json=JSONObject.fromObject(obj,config).toString();
			}
			return json.replace("null", "\"\"");
		}
		
		/**
		 * 将对象格式化成Json字符串并返回
		 * 格式化日期字段值时默认采用模式：yyyy-MM-dd，如果需要其他模式，
		 * 请采用重载方法：fromObjctToJson(Object obj, String dateFmt)以便自行指定模式
		 * 
		 * @param obj	需要格式化的对象
		 * 
		 * @return 
		 */
		public static String fromObjctToJson(Object obj) {
			return fromObjctToJson(obj, null);
		}
		
		/**
		 * @Descripotion 转化为map对象
		 * @param jsonStr
		 * @return
		 * @author Chenxl
		 */
		public static Map<String, Object> parseJSON2Map(String jsonStr){

	        Map<String, Object> map = new HashMap<String, Object>();

	        //最外层解析

	        JSONObject json = JSONObject.fromObject(jsonStr);

	        for(Object k : json.keySet()){

	            Object v = json.get(k); 

	            //如果内层还是数组的话，继续解析

	            if(v instanceof JSONArray){

	                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

	                Iterator<JSONObject> it = ((JSONArray)v).iterator();

	                while(it.hasNext()){

	                    JSONObject json2 = it.next();

	                    list.add(parseJSON2Map(json2.toString()));

	                }

	                map.put(k.toString(), list);

	            } else {

	                map.put(k.toString(), v);

	            }

	        }

	        return map;

	    }
		
		/** 
	     * @Description 将一个 Map 对象转化为一个 JavaBean 使用时最好保证value值不为null 不支持枚举类型和 用户自定义类 
	     * @param type 要转化的javabean 
	     * @param paramMap 包含属性值的map map的key值为javabean属性 key值大写 (toUpperCase大写、toLowerCase小写)
	     * @return 转化出来的 JavaBean 对象 
	     * @throws Exception  Exception 
	     * @author Chenxl
	     */ 
		public static Object convertMap(Class type, Map<String, Object> paramMap) throws Exception 
		{ 
			// 创建 JavaBean 对象 
			Object obj = null; 
		    // 获取类属性 
		    BeanInfo beanInfo = Introspector.getBeanInfo(type); 
		    obj = type.newInstance(); 
		    // 给 JavaBean 对象的属性赋值 
		    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors(); 
		    for (int i = 0; i < propertyDescriptors.length; i++) 
		    { 
		        PropertyDescriptor descriptor = propertyDescriptors[i]; 
		        String propertyName = descriptor.getName().toUpperCase(Locale.getDefault()); 
		
		        if (paramMap.containsKey(propertyName)) 
		        { 
		            String value = ConvertUtils.convert(paramMap.get(propertyName)); 
		            Object[] args = new Object[1]; 
		            args[0] = ConvertUtils.convert(value, descriptor.getPropertyType()); 
		
		            descriptor.getWriteMethod().invoke(obj, args); 
		
		        } 
		    } 
		    return obj; 
		}
		
		/** 
	     * 将一个 JavaBean 对象转化为一个  Map 
	     * @param bean 要转化的JavaBean 对象 
	     * @return 转化出来的  Map 对象 
	     * @throws IntrospectionException 如果分析类属性失败 
	     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
	     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
	     */ 
	    public static Map convertBean2Map(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException { 
	        Class type = bean.getClass(); 
	        Map returnMap = new HashMap(); 
	        BeanInfo beanInfo = Introspector.getBeanInfo(type); 

	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
	        for (int i = 0; i< propertyDescriptors.length; i++) {
	            PropertyDescriptor descriptor = propertyDescriptors[i]; 
	            String propertyName = descriptor.getName(); 
	            if (!propertyName.equals("class")) {
	                Method readMethod = descriptor.getReadMethod(); 
	                Object result = readMethod.invoke(bean, new Object[0]); 
	                if (result != null) { 
	                    returnMap.put(propertyName, result); 
	                } else { 
	                    returnMap.put(propertyName, ""); 
	                } 
	            } 
	        } 
	        return returnMap; 
	    } 

}
