package com.xinlin.app.entity.pojo;

import java.io.Serializable;

/**
 * 用户角色权限信息实体
 * 
 * @author Jiangshui
 * @date	2013-10-17
 */
public class Auth implements Serializable {

	private static final long serialVersionUID = 3423434291877L;
	/** 用户或角色id */
	private String userRoleId;
	/** 权限类型 */
	private Integer authType;
	/** 资源编码 */
	private String menuCode;

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
}