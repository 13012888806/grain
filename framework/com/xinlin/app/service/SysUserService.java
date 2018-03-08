package com.xinlin.app.service;

import com.xinlin.app.base.BaseService;
import com.xinlin.app.entity.pojo.SysUser;
import com.xinlin.app.entity.vo.LoginUser;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.entity.vo.Page;

/**
 * 用户管理业务类接口，负责定义业务类的所有接口协议
 * 
 * @author JiangShui
 * @date	2013-10-13
 * 
 */
public interface SysUserService extends BaseService<SysUser>{

	/**
	 * 分页查询用户数据
	 * 
	 * @param pageQueryParams	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public Page pageQuery(SysUser sysUser);

	/**
	 * 密码修改
	 * 
	 * @param loginUser	当前登录用户
	 * @param oldPass	旧密码
	 * @param newPass	新密码
	 * @return
	 */
	public Message changePass(LoginUser loginUser, String oldPass, String newPass);
	
	/**
	 * 删除指定的用户
	 * 
	 * @param userIds	将要删除的所有用户账号，多个用逗号分隔
	 * @param loginUser	当前登录用户(自己不能删除自己)
	 * @return
	 */
	public Message deleteById(String userIds, LoginUser loginUser);
}