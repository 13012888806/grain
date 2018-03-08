package com.xinlin.app.mapper;

import java.util.HashMap;
import java.util.List;

import com.xinlin.app.entity.pojo.SysUser;

/**
 * 
 * ClassName: SysUserMapper 
 * @Description: 用户的Mybatis映射工具类
 * @author Chenxl
 * @date 2015-5-15
 */
public interface SysUserMapper {

	/**
	 * 分页查询用户数据
	 * 
	 * @param sysUser
	 * @return
	 */
	public List<SysUser> pageQuery(SysUser sysUser);
	
	/**
	 * 新增用户
	 * 
	 * @param sysUser
	 * @return
	 */
	public int insert(SysUser sysUser);

	/**
	 * 查询所有的用户信息
	 * 
	 * @return
	 */
	public List<SysUser> list();
	
	/**
	 * 通过用户账号(user_id)查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public SysUser get(String userId);
	
	/**
	 * 根据用户账号(user_id)进行用户信息修改
	 * 
	 * @param sysUser
	 */
	public void update(SysUser sysUser);

	/**
	 * 根据用户账号(user_id)删除用户
	 * 
	 * @param userId
	 */
	public void deleteById(String userId);
	
	/**
	 * 根据用户账号(userId)获取用户所有有权限的资源编码
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> queryUserMenuCode(String userId);
	
	/**
	 * 查询所有的用户信息，用于导出
	 * 
	 * @return
	 */
	public List<Object> export(HashMap map);
}
