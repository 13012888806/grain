package com.xinlin.app.util.platform.adapter.common.remote;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 调用远程WS服务接口定义
 * 
 * @author JXQ
 * @date 2013-10-16
 * 
 */
public final class RemoteWSOp {
	
	private static final String SOA_ENVELOPE_PART_A = 
			" <soapenv:Envelope "
					+ "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
					// + "xmlns:q0=\"http://www.xinlin.com\" "
					+ "xmlns:q0=\"" ;
	private static final String SOA_ENVELOPE_PART_B = 
			 "\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
					+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> "
					+ "	<soapenv:Body>  ";
	
	private static final String SOA_ENVELOPE_PART_C = 
			" </soapenv:Body></soapenv:Envelope> ";
	
	

	/**
	 * 调用第三方WS服务的通用接口
	 * 
	 * 
	 * @author JXQ
	 * @2013-10-15
	 * 
	 * 
	 * @param wsUrl
	 *            远程ws服务：wsdl地址
	 * 
	 * @param xmlData
	 *            输入远程WS服务的查询XML文
	 *      示例： 
	 *      <q0:getCountryCityByIp>
  	 *			<q0:theIpAddress>61.129.65.58</q0:theIpAddress> 
  	 *		</q0:getCountryCityByIp>
	 *					
	 * @return 返回SOAP报文结构，由用户根据需求，自处理
	 *      示例：
	 *       <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	 *			<soap:Body>
	 *			<getCountryCityByIpResponse xmlns="http://WebXml.com.cn/">
	 *			<getCountryCityByIpResult>
	 *		  	<string>61.129.65.58</string> 
	 *		  	<string>上海市 电信IDC机房(武胜路长信机房)</string> 
	 *		  	</getCountryCityByIpResult>
	 *		  	</getCountryCityByIpResponse>
	 *		  	</soap:Body>
	 *		  </soap:Envelope>
	 * 
	 * 
	 */
	public static String InvokeRemoteWSWithXml(final String wsUrl,
			final String xmlData) {

		String resultXML = null;

		if (xmlData == null || xmlData.trim().length() == 0) {
			return "the queryXML is null, please check it ...";
		}

		System.out.println("==============调用外围平台接口-参数-  start ====================");
    	System.out.println("xmlData："+xmlData);
    	System.out.println("==============调用外围平台接口-参数-  end ====================");

		
		URL url = null;
		URLConnection conn = null;
		HttpURLConnection httpconn = null;
		InputStream is = null;
		OutputStream out = null;

		try {
			url = new URL(wsUrl);
			conn = url.openConnection();
			httpconn = (HttpURLConnection) conn;
			// 设置请求方式
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);
			httpconn.setRequestMethod("POST");
			httpconn.setRequestProperty("content-type", "text/xml;charset=UTF-8");

			/**
			 * <xsd:schema> <xsd:import namespace="http://www.xinlin.com"
			 * schemaLocation="http://10.20.14.91:5678/hello?xsd=1">
			 * </xsd:import> </xsd:schema>
			 */
			// 获取命名空间名称
			String namespace = getNameSpaceFromURL(wsUrl);
			
			if (namespace != null && (namespace = namespace.trim()).length() != 0) {
				// 手动构造请求体
				
				String requestBody = SOA_ENVELOPE_PART_A
						+ namespace
						+ SOA_ENVELOPE_PART_B
						//+ paramXML.toString()
						+ xmlData
						+ SOA_ENVELOPE_PART_C;
				/*String requestBody = 	" <soapenv:Envelope "
						+ "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
						// + "xmlns:q0=\"http://www.xinlin.com\" "
						+ "xmlns:q0=\""
						+ namespace
						+ "\" "
						+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
						+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> "
						+ "	 <soapenv:Body>          " 
						//+ "	 <q0:"
						//+ methodName
						//+ ">              " //
						//+ "	   <arg0>fdfdfdf</arg0>     " //
						//+ "	   <arg1>10</arg1>         " //
						//+ "	   </q0:" 
						//+ methodName
						//+ ">           " //
						+ paramXML.toString()
						+ "	   </soapenv:Body>       "
						+ "	   </soapenv:Envelope>   ";*/

				//System.out.println("SAOP Envelope Text:  "+requestBody);
				// 获得输出流
				out = httpconn.getOutputStream();
				out.write(requestBody.getBytes());

				int code = httpconn.getResponseCode();
				if (code == 200) {// 服务端返回正常
					is = httpconn.getInputStream();
					byte[] b = new byte[1024];
					StringBuffer sb = new StringBuffer();
					int len = 0;
					while ((len = is.read(b)) != -1) {
						String str = new String(b, 0, len, "UTF-8");
						sb.append(str);
					}
					//System.out.println(sb.toString());
					resultXML = sb.toString();
					//resultMap = getResultMapByResultXML(resStr);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			conn = null;
			url = null;
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpconn != null) {
				httpconn.disconnect();
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return resultXML;
	}

	
	/**
	 * 根据WS定义的地址，获取命名空间定义
	 * 
	 * 
	 * @param _wsUrl
	 * @return
	 * @throws Exception
	 */
	private static String getNameSpaceFromURL(String _wsUrl) throws Exception {
		/*if (!_wsUrl.endsWith("wsdl")) {
			_wsUrl += "?wsdl";
		}*/

		SAXReader reader = null;
		Reader wsreader = null;
		Document document = null;
		String returnVal = null;

		try {
			// 得到Document对象
			reader = new SAXReader();
			wsreader = getUrlXmlReader(_wsUrl);
			if (wsreader != null) {
				document = reader.read(wsreader);
				// 得到根元素
				 Element root = document.getRootElement();
				 String namespace  = root.valueOf("@targetNamespace");
				 //System.out.println("targetNamespace  :" + namespace+"\n");
				 returnVal = namespace;

			}
			// return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			reader = null;
			wsreader.close();
			wsreader = null;
			document = null;
		}

		return returnVal;
	}

	/**
	 * 读取URL指定的网页内容 返回Reader对象
	 * 
	 * @author JXQ
	 * @date 2013-10-16
	 * 
	 */
	private static Reader getUrlXmlReader(String urlLoc) throws IOException {
		if (urlLoc == null || (urlLoc = urlLoc.trim()).length() == 0) {
			return null;
		}
		/*if (!urlLoc.endsWith("?wsdl")) {
			urlLoc += "?wsdl";
		}*/
		// URL("http://fy.webxml.com.cn/webservices/EnglishChinese.asmx?wsdl");
		URL url = null;
		// 打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。
		Reader reader = null;
		try {
			url = new URL(urlLoc);
			reader = new InputStreamReader(new BufferedInputStream(
					url.openStream()));
		} catch (Exception e) {
			e.printStackTrace();
			/*try{
				urlLoc = urlLoc.replaceAll("?wsdl", "");
				url = new URL(urlLoc);
				reader = new InputStreamReader(new BufferedInputStream(
						url.openStream()));
			}catch (Exception e1) {
				e1.printStackTrace();
			}*/
		}
		return reader;
		/*
		 * BufferedReader br = new BufferedReader(reader); String line = null;
		 * StringBuffer sbf = new StringBuffer(500); while((line =br.readLine())
		 * != null) { sbf.append(line); //System.out.println(line); }
		 */

	}

	/**
	 * 递归遍历XML结构
	 * 
	 * 
	 * @param root
	 */
	private static void workElement(Element root) {
		if (root.isTextOnly() || !root.isRootElement()) {
			String asXML = root.asXML();
			System.out.println(asXML);
		} else {
			List<Element> elements = root.elements();
			for (Element e : elements) {
				workElement(e);
			}

		}
	}
	 
	public static void main(String []args) throws Exception{
		String wsUrl = "http://192.168.1.220:1080/sap/bc/srt/wsdl/bndg_ABACE55116A5B068E10000000A0A0406/wsdl11/allinone/ws_policy/document?sap-client=200&sap-user=ZSAP_OP&sap-password=Abcd1234";
		String target = getNameSpaceFromURL(wsUrl);
		System.out.println("target === >" + target);
		
	}
	
}
