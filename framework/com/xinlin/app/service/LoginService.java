package com.xinlin.app.service;

/**
 * 
 * ClassName: LoginService 
 * @Description: 用户登录业务接口
 * @author Chenxl
 * @date 2015-5-15
 */
public interface LoginService {

	/**
	 * 用户登录
	 * 
	 * @param userId	用户账号
	 * @param userPass	用户密码
	 * @return
	 */
	public abstract Object login(String userId, String userPass);

}