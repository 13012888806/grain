/**  
 * @Title: VarDao.java
 * @Package com.xinlin.baseInfo.dao
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.dao;

import java.util.Map;

import com.xinlin.app.base.BaseDao;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.baseInfo.entity.Variety;

/**
 * ClassName: VarDao 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface VarDao extends BaseDao<Map<String, Object>> {

	/**
	 * @Description: 分页查找品种管理
	 * @param @param map
	 * @param @return   
	 * @return Page  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-3
	 */
	Page pageQuery(Variety variety) throws Exception;

}
