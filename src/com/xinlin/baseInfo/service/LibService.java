/**  
 * @Title: LibService.java
 * @Package com.xinlin.baseInfo.service
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.service;

import java.util.Map;

import com.xinlin.app.base.BaseService;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.baseInfo.entity.Library;

/**
 * ClassName: LibService 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface LibService extends BaseService<Map<String, Object>> {

	/**
	 * @Description: TODO
	 * @param @param map
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-3
	 */
	Page pageQuery(Library library);

}
