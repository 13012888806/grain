package com.xinlin.app.util;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.betwixt.io.BeanWriter;
import org.xml.sax.SAXException;


/**
 * xml文件操作工具类
 * 
 * 
 * @author jxq
 * @date 2013-10-10
 * 
 */
public final class JCDFXmlUtil {

	/**
	 * 由对象，生成xml
	 * @param obj
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws IntrospectionException
	 */
	public static String toXml(Object obj)throws IOException,SAXException,IntrospectionException{
		
		StringWriter outputWriter = new StringWriter();
		BeanWriter bw = new BeanWriter(outputWriter);
		bw.getXMLIntrospector().getConfiguration() .setAttributesForPrimitives(false);
		bw.getBindingConfiguration() .setMapIDs(false);
		bw.enablePrettyPrint();
		bw.write(obj.getClass().getSimpleName() ,obj );
		
		return outputWriter.toString();
	}
}
