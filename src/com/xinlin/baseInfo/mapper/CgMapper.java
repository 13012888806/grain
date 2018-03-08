/**  
 * @Title: CgMapper.java
 * @Package com.xinlin.baseInfo.mapper
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xinlin.baseInfo.entity.Cg;

/**
 * ClassName: CgMapper 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface CgMapper {
	
	/**
	 * @Description: 分页查询种类管理表
	 * @param @param cg
	 * @param @return
	 * @param @throws Exception   
	 * @return List<CgVo>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-4
	 */
	public List<Cg> pageQuery(Cg cg) throws Exception;

	/**
	 * @Description: 增加种类管理
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	public int insert(Map<String, Object> t);

	/**
	 * @Description: 修改种类管理
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	public void update(Map<String, Object> t);

	/**
	 * @Description: 通过种类管理的ids删除对应的种类管理
	 * @param id   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-17
	 */
	public void deleteById(@Param("ids") String id);

	/**
	 * @Description: 查询种类的键值对集合,在下拉框中使用
	 * @return   
	 * @return List<Map<String,Object>>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-28
	 */
	public List<Map<String, Object>> queryCg();
}
