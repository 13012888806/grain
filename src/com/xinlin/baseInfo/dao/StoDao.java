/**  
 * @Title: StoDao.java
 * @Package com.xinlin.baseInfo.dao
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.dao;

import java.util.Map;

import com.xinlin.app.base.BaseDao;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.baseInfo.entity.Storage;

/**
 * ClassName: StoDao 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface StoDao extends BaseDao<Map<String, Object>> {

	/**
	 * @Description: 分页查找入库信息
	 * @param @param map
	 * @param @return   
	 * @return Page  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-3
	 */
	Page pageQuery(Storage storage) throws Exception;

}
