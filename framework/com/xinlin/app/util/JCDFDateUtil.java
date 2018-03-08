package com.xinlin.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


/**
 * ClassName: 日期工具类 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-8
 */
public final class JCDFDateUtil  implements JsonValueProcessor{

	/**日期格式化模式*/
	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	public JCDFDateUtil(){
		
	}
	
	public JCDFDateUtil(String dateFormat){
		this.dateFormat = dateFormat;
	}
	
	/**
	 * 获取明天的日期
	 * 
	 * @return
	 */
	public static Calendar tomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DATE,1);
		return calendar;
	}
	
	  /**  
     * 计算两个日期之间相差的天数  
     * 
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) {    
        long between_days = 0;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			smdate=sdf.parse(sdf.format(smdate));  
			bdate=sdf.parse(sdf.format(bdate));  
			Calendar cal = Calendar.getInstance();    
			cal.setTime(smdate);    
			long time1 = cal.getTimeInMillis();                 
			cal.setTime(bdate);    
			long time2 = cal.getTimeInMillis();         
			between_days = (time2-time1)/(1000*3600*24);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        return Integer.parseInt(String.valueOf(between_days));           
    }
    
    
    
    
   	///////////////////JsonValueProcessor 专用begin///////////////////////
   	 public Object processArrayValue(Object value, JsonConfig jsonConfig) {
   	        return null;
   	    }

   	    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
   	        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
   	        if (null != value) {
   	        	if (value instanceof java.util.Date) {
   	        		return sdf.format(value);
   	            }else {
   	            	return value.toString();
   	            }
   	        }else{
   	        	return "";
   	        }
   	    }

   	    public String getDateFormat() {
   	        return dateFormat;
   	    }

   	    public void setDateFormat(String dateFormat) {
   	        this.dateFormat = dateFormat;
   	    }
   	    
   	    /**
   	     * @Description: 采用默认格式
   	     * @param date
   	     * @return   
   	     * @return String  
   	     * @throws
   	     * @author Chenxl
   	     * @date 2015-6-8
   	     */
   	    public static String formatDate(java.util.Date date){
   	    	return new SimpleDateFormat(dateFormat).format(date);
   	    }
   	    
   	    /**
   	     * @Description: 自己制定格式
   	     * @param pattern
   	     * @param date
   	     * @return   
   	     * @return String  
   	     * @throws
   	     * @author Chenxl
   	     * @date 2015-6-8
   	     */
	   	public static String formatDate(String pattern, java.util.Date date){
	 	    return new SimpleDateFormat(pattern).format(date);
	 	}
   	    
   	    public static void printlnSysTime(){
   	    	System.out.println(formatDate(new java.util.Date())); 
   	    }
   	    
   	    /**
   	     * @Description: 获取随机数，用于生成唯一的主键值，生成规则：yyMMddHHmmssSSS+5位随机数=16到20位的数字字符
   	     * @return   
   	     * @return String  
   	     * @throws
   	     * @author Chenxl
   	     * @date 2015-6-8
   	     */
   	    public static String getRandomNum() {
	 		Random rand = new Random();
	 		int temp = rand.nextInt(99999);
	 		return formatDate("yyMMddHHmmssSSS", new Date())+temp;
	 	}
   	 ////////////////////JsonValueProcessor 专用end////////////////////////
}
