package com.xinlin.app.dao;

import java.util.List;

import com.xinlin.app.base.BaseDao;
import com.xinlin.app.entity.pojo.Role;
import com.xinlin.app.entity.pojo.RoleUserMap;
import com.xinlin.app.entity.vo.Page;

/**
 * 角色数据库访问接口，负责定义接口操作协议
 * 
 * @author JiangShui
 * @date	2013-10-13
 * 
 */
public interface RoleDao extends BaseDao<Role>{

	/**
	 * 分页查询角色数据
	 * 
	 * @param role	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public Page pageQuery(Role role) throws Exception;

	/**
	 * 新增用户与角色的对应关系信息
	 * 
	 * @param roleUserMap
	 * @return
	 */
	public void insertRoleUserMap(RoleUserMap roleUserMap);
	
	/**
	 * 查询角色所拥有的用户账号，或者查询用户所拥有的角色编码
	 * 
	 * @param List<String>
	 * @return
	 */
	public List<String> getRoleUserMap(RoleUserMap roleUserMap);
	
	/**
	 * 根据指定的条件删除用户与角色的对应关系信息
	 * 
	 * @param roleUserMap
	 * @return
	 */
	public void deleteRoleUserMap(RoleUserMap roleUserMap);
	
	/**
	 * 检查指定的角色是否存在，通过角色名来查找，如果指定了roleId，的检查除此roleId外是否存在指定名称的角色(用户新增修改的角色名唯一校验)
	 * 
	 * @param role	查询条件(仅处理了roleName、roleId两个字段)
	 * @return	如果查询到了，则返回对应的roel对象，否则返回null
	 */
	public Role checkRoleIsExist(Role role);
}