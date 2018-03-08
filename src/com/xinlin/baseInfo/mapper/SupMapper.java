/**  
 * @Title: SupMapper.java
 * @Package com.xinlin.baseInfo.mapper
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xinlin.baseInfo.entity.Supplier;

/**
 * ClassName: SupMapper 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface SupMapper {
	
	/**
	 * @Description: 分页查询仓库信息表
	 * @param @param supplier
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Supplier>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-4
	 */
	public List<Supplier> pageQuery(Supplier supplier) throws Exception;

	/**
	 * @Description: 增加仓库信息
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	public int insert(Map<String, Object> t);

	/**
	 * @Description: 修改仓库信息
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	public void update(Map<String, Object> t);

	/**
	 * @Description: 通过仓库信息的ids删除对应的仓库信息
	 * @param id   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-17
	 */
	public void deleteById(@Param("ids") String id);
}
