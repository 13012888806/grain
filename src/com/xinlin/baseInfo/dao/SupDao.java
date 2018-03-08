/**  
 * @Title: SupDao.java
 * @Package com.xinlin.baseInfo.dao
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.dao;

import java.util.Map;

import com.xinlin.app.base.BaseDao;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.baseInfo.entity.Supplier;

/**
 * ClassName: SupDao 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface SupDao extends BaseDao<Map<String, Object>> {

	/**
	 * @Description: 分页查找供应商信息
	 * @param @param map
	 * @param @return   
	 * @return Page  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-3
	 */
	Page pageQuery(Supplier supplier) throws Exception;

}
