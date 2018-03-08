package com.xinlin.app.service;

import java.util.List;

import com.xinlin.app.base.BaseService;
import com.xinlin.app.entity.pojo.Role;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.entity.vo.TreeNode;

/**
 * 角色管理业务类接口，负责定义业务类的所有接口协议
 * 
 * @author JiangShui
 * @date	2013-10-13
 * 
 */
public interface RoleService extends BaseService<Role>{

	/**
	 * 分页查询角色数据
	 * 
	 * @param role	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public Page pageQuery(Role role);

	/**
	 * 加载角色树(将所有的角色作为根节点，加载为一棵树)
	 * 
	 * @param userId
	 * @return
	 */
	public List<TreeNode> loadRoleTree(String userId);
	
	/**
	 * 新增用户与角色的对应关系信息
	 * 
	 * @param roleIds	分配给当前指定用户的角色id，多个用逗号分隔
	 * @param userId	当前被分配角色的用户
	 */
	public Message insertRoleUserMap(String roleIds, String userId);
}