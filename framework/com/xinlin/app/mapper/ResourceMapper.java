package com.xinlin.app.mapper;

import java.util.List;

import com.xinlin.app.entity.pojo.Resource;

/**
 * 资源的Mybatis映射工具类
 * 
 * @author Jiangshui
 * @date	2013-10-13
 *
 */
public interface ResourceMapper {

	/**
	 * 根据指定的菜单编码查询对应的所有资源信息
	 * 
	 * @param parentMenuCode	父节点编码
	 * 
	 * @return	对应父节点编码的所有子节点信息
	 */
	public List<Resource> findByMenuCode(String menuCode);
	
}
