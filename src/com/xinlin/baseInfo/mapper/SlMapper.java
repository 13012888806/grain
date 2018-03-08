/**  
 * @Title: SlMapper.java
 * @Package com.xinlin.baseInfo.mapper
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xinlin.baseInfo.entity.SupLevel;

/**
 * ClassName: SlMapper 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface SlMapper {
	
	/**
	 * @Description: 分页查询供应商级别表
	 * @param @param supLevel
	 * @param @return
	 * @param @throws Exception   
	 * @return List<SupLevel>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-4
	 */
	public List<SupLevel> pageQuery(SupLevel supLevel) throws Exception;

	/**
	 * @Description: 增加供应商级别
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	public int insert(Map<String, Object> t);

	/**
	 * @Description: 修改供应商级别
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	public void update(Map<String, Object> t);

	/**
	 * @Description: 通过供应商级别的ids删除对应的供应商级别
	 * @param id   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-17
	 */
	public void deleteById(@Param("ids") String id);

	/**
	 * @Description: 查询供应商级别,下拉框用
	 * @return   
	 * @return List<Map<String,Object>>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-28
	 */
	public List<Map<String, Object>> querySl();
}
