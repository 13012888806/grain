/**  
 * @Title: VarMapper.java
 * @Package com.xinlin.baseInfo.mapper
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xinlin.baseInfo.entity.Variety;

/**
 * ClassName: VarMapper 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface VarMapper {
	
	/**
	 * @Description: 分页查询品种管理表
	 * @param @param variety
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Variety>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-4
	 */
	public List<Variety> pageQuery(Variety variety) throws Exception;

	/**
	 * @Description: 增加品种管理
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	public int insert(Map<String, Object> t);

	/**
	 * @Description: 修改品种管理
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	public void update(Map<String, Object> t);

	/**
	 * @Description: 通过品种管理的ids删除对应的品种管理
	 * @param id   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-17
	 */
	public void deleteById(@Param("ids") String id);

	/**
	 * @Description: 查询品种的键值对集合,在下拉框中使用
	 * @return   
	 * @return List<Map<String,Object>>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-28
	 */
	public List<Map<String, Object>> queryVar();
}
