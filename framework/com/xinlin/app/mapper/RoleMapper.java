package com.xinlin.app.mapper;

import java.util.List;

import com.xinlin.app.entity.pojo.Role;
import com.xinlin.app.entity.pojo.RoleUserMap;

/**
 * 角色的Mybatis映射工具类
 * 
 * @author Jiangshui
 * @date	2013-10-13
 *
 */
public interface RoleMapper {

	/**
	 * 分页查询角色
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> pageQuery(Role role);
	
	/**
	 * 新增角色
	 * 
	 * @param role
	 * @return
	 */
	public int insert(Role role);

	/**
	 * 查询所有的角色信息
	 * 
	 * @return
	 */
	public List<Role> list();
	
	/**
	 * 通过角色编号(roleId)查询角色信息
	 * 
	 * @param id
	 * @return
	 */
	public Role get(String roleId);
	
	/**
	 * 根据角色编号(roleId)进行角色信息修改
	 * 
	 * @param role
	 */
	public void update(Role role);

	/**
	 * 根据角色编号(roleId)删除角色
	 * 
	 * @param id
	 */
	public void deleteById(String roleId);
	
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
