package com.xinlin.app.util.platform.adapter.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import com.alibaba.fastjson.JSON;
import com.xinlin.app.util.JCDFXmlUtil;
import com.xinlin.app.util.StaticVar;
import com.xinlin.app.util.platform.adapter.common.local.IWSDao;
import com.xinlin.app.util.platform.adapter.common.local.WSObjectFactory;
import com.xinlin.app.util.platform.vo.WSVOTest;
  

/**
 * 线程池业务类，并发处理WS多参数请求
 * 单个http连接，多参数，多返回值时选用
 * 
 * @author JXQ
 * @date 2013-10-12
 * 
 */
public class WSPoolExecutor {
	private static final long serialVersionUID = 6214790243416807050L;
	
	private IWSDao iWSDao;
	
	private String [] inputParam;
	
	private String returnPattern;
 
	public void setInputParam(String[] inputParam) {
		this.inputParam = inputParam;
	}
	
	public void setiWSDao(IWSDao iWSDao) {
		this.iWSDao = iWSDao;
	}

	public WSPoolExecutor(IWSDao iWSDao, String[] inputParam) {
		this.iWSDao = iWSDao;
		this.inputParam = inputParam;
	}

	public WSPoolExecutor(IWSDao iWSDao, String[] inputParam,
			String returnPattern) {
		super();
		this.iWSDao = iWSDao;
		this.inputParam = inputParam;
		this.returnPattern = returnPattern;
	}

	public WSPoolExecutor(String[] inputParam) {
		this.inputParam = inputParam;
	}
	
	private WSPoolExecutor() {
	}
	
	
	/**
	 * @return
	 * @throws InterruptedException
	 */
	public String getJsonStrByInputParam() 
			{
		//去除重复数据
		Set inputParams = new HashSet(Arrays.asList(inputParam));
		
		//查询数据长度
		final int totalCount = inputParams.size();
		
		//final List<String> results = new ArrayList<String>();
		//final StringBuffer sbf = new StringBuffer(totalCount*200);
		
		//返回值Hashtable
		final Hashtable<String, Object> result = new Hashtable<String, Object>();
		
		// 开始的倒数锁
		final CountDownLatch begin = new CountDownLatch(1);
		// 结束的倒数锁
		//final CountDownLatch end = new CountDownLatch(10);
		final CountDownLatch end = new CountDownLatch(totalCount);
		
		for (java.util.Iterator ite  = inputParams.iterator(); ite.hasNext();) {
			//final int NO = index + 1;
			//输入参数
			final String inputStr = (String)ite.next();
			Runnable run = new Runnable() {
				public void run() {
					try {
						begin.await();
						//Thread.sleep((long) (Math.random() * 10000));
						//处理业务
						Map jsonMap = iWSDao.getJsonMapByInputParam(inputStr);
						//sbf.append(orgCode+"No." + NO + " arrived\t" + SYS_SEPARATOR);
						//保存中间值
						result.put(inputStr, jsonMap);
					} catch (InterruptedException e) {
					} finally {
						end.countDown();
					}
				}
			};
			//提交
			WSObjectFactory.getPool().submit(run);
		}
		//System.out.println(Thread.currentThread().getName()+" Start");
		begin.countDown();
		try {
			end.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(Thread.currentThread().getName()+"  Over");
		String jsonStr = JSON.toJSONString(result);
			
		//System.out.println(jsonStr);
		return jsonStr;
	}

	public String getStrByInputParam() {
		if(!StaticVar.TYPE_XML.equalsIgnoreCase(returnPattern))
			return this.getJsonStrByInputParam();
		else
			return this.getXmlStrByInputParam();
	}

	
	
	/**
	 * @return
	 * @throws InterruptedException
	 */
	public String getXmlStrByInputParam() 
			{
		//去除重复数据
		Set inputParams = new HashSet(Arrays.asList(inputParam));
		
		//查询数据长度
		final int totalCount = inputParams.size();
		
		//final List<String> results = new ArrayList<String>();
		//final StringBuffer sbf = new StringBuffer(totalCount*200);
		
		//返回值Hashtable
		//final Hashtable<String, Object> result = new Hashtable<String, Object>();
		final Vector <WSVOTest>WSVOresult = new Vector <WSVOTest>();
		
		// 开始的倒数锁
		final CountDownLatch begin = new CountDownLatch(1);
		// 结束的倒数锁
		//final CountDownLatch end = new CountDownLatch(10);
		final CountDownLatch end = new CountDownLatch(totalCount);
		
		for (java.util.Iterator ite  = inputParams.iterator(); ite.hasNext();) {
			//final int NO = index + 1;
			//输入参数
			final String inputStr = (String)ite.next();
			Runnable run = new Runnable() {
				public void run() {
					try {
						begin.await();
						//Thread.sleep((long) (Math.random() * 10000));
						//处理业务
						List<WSVOTest> obj = (List<WSVOTest>)iWSDao.getObjectByInputParam(inputStr);
						
						//sbf.append(orgCode+"No." + NO + " arrived\t" + SYS_SEPARATOR);
						//保存中间值
						WSVOresult.addAll(obj);
					} catch (InterruptedException e) {
					} finally {
						end.countDown();
					}
				}
			};
			//提交
			WSObjectFactory.getPool().submit(run);
		}
		//System.out.println(Thread.currentThread().getName()+" Start");
		begin.countDown();
		try {
			end.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(Thread.currentThread().getName()+"  Over");
		String xmlStr = null;
		try {
			xmlStr = JCDFXmlUtil.toXml(WSVOresult);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		//System.out.println(jsonStr);
		return xmlStr;
	}
	
}
