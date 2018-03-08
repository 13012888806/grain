package com.xinlin.app.util.platform.filter;

import java.lang.reflect.Method;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.entity.vo.PageQueryParams;

/**
 * 通用Dao，集成一些通用的功能
 * 
 * @author Jiangshui
 * @date	2013-10-23
 */
public abstract class JcdfDaoSupport extends SqlSessionDaoSupport{

	/**
	 * 物理分页查询(当前仅支持Mysql、Oracle),由框架底层自动拆分分析sql，进行总记录数和分页数据的查询
	 * 
	 * @param mapper	mybatis sql xml的映射接口对象
	 * @param method	映射接口中对应分页查询的方法名(也即xml中sql语句的id)
	 * @param parameters	查询参数，必需是PageQueryParams或者其子类的对象
	 * 
	 * @return
	 * @throws Exception
	 */
	public Page pageQuery(Object mapper, String methodName, PageQueryParams parameters) throws Exception{
		Page page = null;
		Method method = (mapper.getClass()).getMethod(methodName, parameters.getClass());
		Object result = method.invoke(mapper, parameters);
		if (null != result) {
			page = new Page(parameters.getTotal(), result);
		} else {
			page = new Page();
		}
		return page;
	}
	
	/**
	 * 物理分页查询，由开发人员提供查询分页数据、统计总计数数的sql，框架仅负责将查询结果进行组装
	 * 
	 * 注意：如果PageQueryParams中的，专门用于分页的参数不够自己分页sql中使用，请在继承PageQueryParams的
	 * 实体中添加新的参数，对于需要使用到start(本次查询记录的开始索引)、total(总记录数)进行计算才能得到的参数，可以
	 * 在对应参数的get方法中(mybatis的sql动态参数是依据get方法动态获得并注入到sql中的)添加算法，start、total
	 * 可以直接使用，框架可以在查询前提前查询计算出相应值并注入。
	 * 
	 * @param mapper
	 * @param methodNameOfPageData
	 * @param methodNameOfCountNote
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Page pageQuery(Object mapper, String methodNameOfPageData, String methodNameOfCountNote, PageQueryParams parameters) throws Exception{
		Page page = null;
		Object rows = null;
		//统计总记录数
		Object result = (mapper.getClass()).getMethod(methodNameOfCountNote, parameters.getClass()).invoke(mapper, parameters);
		long totle = null == result ? 0 : Long.valueOf(result.toString());
		if (totle < 1){
			rows = null;
		} else {
			//计算所查询页数据起始记录所在的行
			long start = Page.getStartOfPage(parameters.getPageNo(), parameters.getPageSize());
			//如果超过了最后一行，则自动退回到最后一页的第一行
			if (start >= totle) {
				start = Page.getStartOfPage(parameters.getPageNo()-1, parameters.getPageSize());
			}
			parameters.setStart(start);
			rows = (mapper.getClass()).getMethod(methodNameOfPageData, parameters.getClass()).invoke(mapper, parameters);
		}
		if(null != rows) {
			page = new Page(totle, rows);
		} else {
			page = new Page();
		}
		return page;
	}
}
