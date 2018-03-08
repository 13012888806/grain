/**  
 * @Title: SupService.java
 * @Package com.xinlin.baseInfo.service
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.service;

import java.util.Map;

import com.xinlin.app.base.BaseService;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.baseInfo.entity.Supplier;

/**
 * ClassName: SupService 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface SupService extends BaseService<Map<String, Object>> {

	/**
	 * @Description: TODO
	 * @param @param map
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-3
	 */
	Page pageQuery(Supplier supplier);

}
