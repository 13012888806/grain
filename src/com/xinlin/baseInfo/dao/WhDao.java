/**  
 * @Title: WhDao.java
 * @Package com.xinlin.baseInfo.dao
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.dao;

import java.util.Map;

import com.xinlin.app.base.BaseDao;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.baseInfo.entity.WareHouse;

/**
 * ClassName: WhDao 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface WhDao extends BaseDao<Map<String, Object>> {

	/**
	 * @Description: 分页查找仓库信息
	 * @param @param map
	 * @param @return   
	 * @return Page  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-3
	 */
	Page pageQuery(WareHouse wareHouse) throws Exception;

}
