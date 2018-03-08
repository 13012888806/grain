/**  
 * @Title: ClMapper.java
 * @Package com.xinlin.baseInfo.mapper
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xinlin.baseInfo.entity.CusLevel;

/**
 * ClassName: ClMapper 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface ClMapper {
	
	/**
	 * @Description: 分页查询客户级别表
	 * @param @param cusLevel
	 * @param @return
	 * @param @throws Exception   
	 * @return List<CusLevel>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-4
	 */
	public List<CusLevel> pageQuery(CusLevel cusLevel) throws Exception;

	/**
	 * @Description: 增加客户级别
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	public int insert(Map<String, Object> t);

	/**
	 * @Description: 修改客户级别
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	public void update(Map<String, Object> t);

	/**
	 * @Description: 通过客户级别的ids删除对应的客户级别
	 * @param id   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-17
	 */
	public void deleteById(@Param("ids") String id);

	/**
	 * @Description: 查询客级别，下拉框用
	 * @return   
	 * @return List<Map<String,Object>>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-29
	 */
	public List<Map<String, Object>> queryCl();
}
