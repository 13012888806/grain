package com.xinlin.app.dao;

import com.xinlin.app.base.BaseDao;
import com.xinlin.app.entity.pojo.JcdfLog;
import com.xinlin.app.entity.vo.Page;

/**
 * 系统日志Dao
 * 
 * @author jxq
 * @date 2013-10-24
 *
 */
public interface JcdfLogDao extends BaseDao<JcdfLog> {
	public Page pageQuery(JcdfLog log) throws Exception;

}
