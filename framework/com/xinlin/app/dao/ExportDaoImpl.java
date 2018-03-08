package com.xinlin.app.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.xinlin.app.util.platform.filter.JcdfDaoSupport;

/**
 * 导出数据库访问接口实现
 * 
 * @author Jiangshui
 * @date	2013-10-13
 *
 */
@Repository(value="exportDao")
public class ExportDaoImpl extends JcdfDaoSupport implements ApplicationContextAware, ExportDao{
	
	/**应用程序上下文，用于动态获取Mybatis指定class的mapper对象（spring自动注入）*/
	private ApplicationContext acx = null;
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.dao.ExportDao#exportData(java.lang.String, java.lang.String, java.lang.Object)
	 */
	public List<Map<String, Object>> exportData(String className, String methodName, Object params) throws BeansException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//从spring容器中获取其托管的mybatismappe对象
		Object mapper = acx.getBean(Class.forName(className));
		//动态调用mapper中的，用于导出的数据查询方法
		Method method = (mapper.getClass()).getMethod(methodName, params.getClass());
		Object result = method.invoke(mapper, params);
		if(null != result && result instanceof List) {
			return (List<Map<String, Object>>)result;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.dao.ExportDao#exportData(java.lang.String, java.lang.Object)
	 */
	public List<Map<String, Object>> exportData(String mapperId, Object params) {
		SqlSession session = this.getSqlSession();
		Object result = session.selectList(mapperId, params);
		if(null != result && result instanceof List) {
			return (List<Map<String, Object>>)result;
		}
		return null;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext acx)
			throws BeansException {
		// TODO Auto-generated method stub
		this.acx = acx;
	}
}
