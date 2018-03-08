/**  
 * @Title: CusMapper.java
 * @Package com.xinlin.baseInfo.mapper
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xinlin.baseInfo.entity.Customer;

/**
 * ClassName: CusMapper 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
public interface CusMapper {
	
	/**
	 * @Description: 分页查询客户信息表
	 * @param @param customer
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Customer>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-4
	 */
	public List<Customer> pageQuery(Customer customer) throws Exception;

	/**
	 * @Description: 增加客户信息
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	public int insert(Map<String, Object> t);

	/**
	 * @Description: 修改客户信息
	 * @param t   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	public void update(Map<String, Object> t);

	/**
	 * @Description: 通过客户信息的ids删除对应的客户信息
	 * @param id   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-17
	 */
	public void deleteById(@Param("ids") String id);

	/**
	 * @Description:  查询客户键值对,在下拉框使用
	 * @return   
	 * @return List<Map<String,Object>>  
	 * @throws
	 * @author Chenxl
	 * @date 2015-9-5
	 */
	public List<Map<String, Object>> queryCus();
}
