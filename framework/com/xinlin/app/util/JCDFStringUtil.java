package com.xinlin.app.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 字符串工具类
 * 
 * 
 * @author jxq
 * @date 2013-10-10
 *
 */
public final class JCDFStringUtil {
	
	/**
	* 将一个字符串转化为输入流
	* 
	* @author JXQ
	* @date 2013-10-16
	*/
	public static InputStream getStringStream(String sInputString) {
		if (sInputString != null && !sInputString.trim().equals("")) {
			try {
				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(
						sInputString.getBytes());
				return tInputStringStream;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

 

	/**
	 * 将一个输入流转化为字符串
	 * 
	 * @author JXQ
	 * @date 2013-10-16
	 */
	public static String getStreamString(InputStream tInputStream) {
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(
						new InputStreamReader(tInputStream));
				StringBuffer tStringBuffer = new StringBuffer();
				String sTempOneLine = new String("");
				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					tStringBuffer.append(sTempOneLine);
				}
				return tStringBuffer.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	/**  
     * MD5 加密  
     */   
    public static String getMD5Str(String str) {   
        MessageDigest messageDigest = null;   
   
        try {   
            messageDigest = MessageDigest.getInstance("MD5");   
            messageDigest.reset();   
            messageDigest.update(str.getBytes("UTF-8"));   
        } catch (NoSuchAlgorithmException e) {   
            System.exit(-1);   
        } catch (UnsupportedEncodingException e) {   
            e.printStackTrace();   
        }   
   
        byte[] byteArray = messageDigest.digest();   
        StringBuffer md5StrBuff = new StringBuffer();   
        for (int i = 0; i < byteArray.length; i++) {               
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));   
            }else  { 
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));   
            }
        }   
   
        return md5StrBuff.toString();   
    }  
	
    
    /**
     * 采用MD5和Base64密码加密
     * 
     * @param password	密码明文
     * 
     * @return	加密后的密码密文
     * 
     * @author JiangShui
     * @date 2013-10-29
     */
    public static String encodePassword(String password) {
    	String encodePassword = null;
		try {
			byte [] bytPwd = password.getBytes();
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update(bytPwd);
			encodePassword = (new BASE64Encoder()).encode(alg.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return encodePassword;
    }
    
	
    /**
	 * 将object对象转成Sring对象
	 * 
	 * @param obj
	 * @return
	 */
	public static String objToString(Object obj) {
		if(null != obj && obj instanceof String) {
			return (String)obj;
		} else if (null != obj) {
			return obj.toString();
		} else {
			return "";
		}
	}
	
	/**
	 * 将Object对象转成BigDecimal对象
	 * 
	 * @param obj
	 * @return
	 */
	public static BigDecimal objToBigDecimal(Object obj) {
		BigDecimal result = null;
		try {
			if(null != obj && obj instanceof BigDecimal) {
				result = (BigDecimal)obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 将Object对象转成Integer对象
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer objToInteger(Object obj) {
		Integer result = null;
		try {
			if(null != obj && obj instanceof Integer) {
				result = (Integer)obj;
			} else if (null != obj) {
				result = Integer.parseInt(obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将String对象转成Integer对象
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer stringToInteger(String str) {
		Integer result = null;
		try {
			result = Integer.parseInt(str.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 将Object对象转成Double对象
	 * 
	 * @param obj
	 * @return
	 */
	public static Double objToDouble(Object obj) {
		Double result = null;
		try {
			if(null != obj && obj instanceof Double) {
				result = (Double)obj;
			} else if (null != obj) {
				result = Double.parseDouble(obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将String对象转成Double对象
	 * 
	 * @param obj
	 * @return
	 */
	public static Double stringToDouble(String str) {
		Double result = null;
		try {
			result = Double.parseDouble(str.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 判断指定的字符串是否为空(非null + 非“”)
	 * 
	 * @param str	要判断的字符串
	 * @return	如果为空则false，否则true
	 * 
	 * @author JiangShui
	 * @date 2013-10-29
	 */
	public static boolean isNotNullAndEmpty(String str) {
		return (null != str && !"".equals(str.trim()));
	}
	
	/**
	 * 将指定的字符串按照指定的编码进行编码转换
	 * 
	 * @param str
	 * @param newCharset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertCharset(String str, String newCharset) throws UnsupportedEncodingException {
		 if (null != str) {
			   //用默认字符编码解码字符串。
			   byte[] bs = str.getBytes();
			   //用新的字符编码生成字符串
			   return new String(bs, newCharset);
		  }
		  return null;
	}
}
