package com.xinlin.app.entity.pojo;

/**
 * 用户角色映射信息实体
 * 
 * @author Jiangshui
 * @date	2013-10-18
 */
public class RoleUserMap {

	/** 用户id */
	private String userId;
	/** 角色id */
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
