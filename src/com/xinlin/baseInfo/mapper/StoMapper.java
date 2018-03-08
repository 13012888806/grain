/**  
 * @Title: StoMapper.java
 * @Package com.xinlin.baseInfo.mapper
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xinlin.baseInfo.entity.Storage;

/**
 * ClassName: StoMapper 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface StoMapper {
	
	/**
	 * @Description: 分页查询入库信息表
	 * @param @param storage
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Storage>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-4
	 */
	public List<Storage> pageQuery(Storage storage) throws Exception;

	/**
	 * @Description: 增加入库信息
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	public int insert(Map<String, Object> t);

	/**
	 * @Description: 修改入库信息
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	public void update(Map<String, Object> t);

	/**
	 * @Description: 通过入库信息的ids删除对应的入库信息
	 * @param id   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-17
	 */
	public void deleteById(@Param("ids") String id);
}
