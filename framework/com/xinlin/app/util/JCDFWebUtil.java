package com.xinlin.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class JCDFWebUtil {
	
	 

	public static String getClientAddress(HttpServletRequest request) {
		String address = request.getHeader("X-Forwarded-For");
		if (address != null && isIpAddress(address)) {
			return address;
		}
		return request.getRemoteAddr();
	}
	
	
	public static String getIP(HttpServletRequest request) {
		// if(request.getHeader("x-forwarded-for")==null){
		// return request.getRemoteAddr();
		// }
		// return request.getHeader("x-forwarded-for");

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static Boolean isIpAddress(String s) {
		String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	
	
	public static String getMACAddress(String ip) {

		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(
								str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		System.out.println(macAddress);
		return macAddress;
	}

	/**
	 * 全局Key定义
	 * @return
	 */
	public static String getGlobalKey(){
		return UUID.randomUUID().toString();
	}
	

	/**
	 * 记录日志信息
	 * @param logText
	 * @param request
	 */
	public static void Log(HttpServletRequest request, String logText){
		request.setAttribute(StaticVar.SYS_LOG_ENABLED_KEY, StaticVar.SYS_LOG_ENABLED);
		request.setAttribute(StaticVar.SYS_LOG_KEY, logText);
	}
	
	/**
	 * 记录日志信息
	 * @param logText
	 * @param request
	 */
	public static void Log(String moduleName, String logText){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
		request.setAttribute(StaticVar.SYS_LOG_ENABLED_KEY, StaticVar.SYS_LOG_ENABLED);
		request.setAttribute(StaticVar.SYS_LOG_KEY, logText);
		request.setAttribute(StaticVar.SYS_LOG_MODULE_KEY, moduleName);
	}
	
}
