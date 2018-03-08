package com.xinlin.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinlin.app.dao.JcdfLogDao;
import com.xinlin.app.entity.pojo.JcdfLog;
import com.xinlin.app.entity.pojo.Menu;
import com.xinlin.app.entity.vo.Page;

/**
 * 日志管理业务类实现
 * 
 * @author JXQ
 * @date	2013-10-21
 *
 */
@Service(value="jcdfLogService")
public class JcdfLogServiceImpl implements JcdfLogService {

	/**用户数据库操作接口*/
	@Resource(name="jcdfLogDao")
	private JcdfLogDao jcdfLogDao;
	
	
	@Override
	public Page pageQuery(JcdfLog log) {
		Page page = null;
		try {
			page = jcdfLogDao.pageQuery(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}
	
	
	public List<JcdfLog> list() {
		return jcdfLogDao.list();
	}

	
	public JcdfLog get(String id) {
		return jcdfLogDao.get(id);
	}

	
	@Transactional
	public Object insert(JcdfLog t) {
		t.setOperatorTime(new java.util.Date());
		jcdfLogDao.insert(t);
		return null;
	}

	
	@Transactional
	public Object update(JcdfLog t) {
		jcdfLogDao.update(t);
		return null;
	}

	
	@Transactional
	public Object deleteById(String id) {
		jcdfLogDao.deleteById(id);
		return null;
	}
	
	/*public Page pageQuery(Object queryParams, PageQueryParams pageQueryParams) {
		return jcdfLogDao.pageQuery(queryParams, pageQueryParams);
	}
*/
}
