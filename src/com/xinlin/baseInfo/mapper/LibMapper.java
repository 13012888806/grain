/**  
 * @Title: LibMapper.java
 * @Package com.xinlin.baseInfo.mapper
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xinlin.baseInfo.entity.Library;

/**
 * ClassName: LibMapper 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface LibMapper {
	
	/**
	 * @Description: 分页查询出库信息表
	 * @param @param library
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Library>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-4
	 */
	public List<Library> pageQuery(Library library) throws Exception;

	/**
	 * @Description: 增加出库信息
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	public int insert(Map<String, Object> t);

	/**
	 * @Description: 修改出库信息
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	public void update(Map<String, Object> t);

	/**
	 * @Description: 通过出库信息的ids删除对应的出库信息
	 * @param id   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-17
	 */
	public void deleteById(@Param("ids") String id);
}
