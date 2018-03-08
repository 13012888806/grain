/**  
 * @Title: ClDao.java
 * @Package com.xinlin.baseInfo.dao
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.dao;

import java.util.Map;

import com.xinlin.app.base.BaseDao;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.baseInfo.entity.CusLevel;

/**
 * ClassName: ClDao 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface ClDao extends BaseDao<Map<String, Object>> {

	/**
	 * @Description: 分页查找客户级别
	 * @param @param map
	 * @param @return   
	 * @return Page  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-3
	 */
	Page pageQuery(CusLevel cusLevel) throws Exception;

}
