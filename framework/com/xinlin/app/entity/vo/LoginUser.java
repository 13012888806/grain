package com.xinlin.app.entity.vo;

/**
 * 登录用户信息
 * 
 * @author Jiangshui
 * @date 2013-10-21
 */
public class LoginUser {

	/**用户账号*/
    private String userId;
	/** 用户名称 */
	private String userName;
	/** 本次登录的令牌 */
	private String token;
	/**用户类型：1：超级管理员，2：普通用户*/
    private Integer userType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}