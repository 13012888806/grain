package com.xinlin.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinlin.app.base.BaseController;
import com.xinlin.app.entity.pojo.SysUser;
import com.xinlin.app.entity.vo.LoginUser;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.service.SysUserService;
import com.xinlin.app.util.JCDFWebUtil;
import com.xinlin.app.util.StaticVar;

/**
 * 用户管理控制器
 * 
 * @author JiangShui
 * @date	2013-10-13
 * 
 */
@Controller
@RequestMapping(value = "/auth/user.do")
public class SysUserController extends BaseController{

	/**用户管理业务类*/
	@Resource(name="sysUserService")
	private SysUserService sysUserService;
	
	/**
	 * 转发到用户列表页面
	 * 
	 * @param response
	 * 
	 * @return	用户管理页面视图
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String forwardList(HttpServletResponse response){
		return "auth/user_list";
	}
	
	/**
	 * 转发到用户新增页面
	 * 
	 * @param response
	 * 
	 * @return	用户新增页面视图
	 */
	@RequestMapping(params="method=forwardAddJsp")
	public String forwardAdd(HttpServletResponse response){
		return "auth/user_add";
	} 
	
	/**
	 * 转发到用户修改页面
	 * 
	 * @param response
	 * 
	 * @return	用户修改页面视图
	 */
	@RequestMapping(params="method=forwardEditJsp")
	public String forwardEdit(HttpServletResponse response){
		return "auth/user_edit";
	} 
	
	/**
	 * 转发到用户授权页面
	 * 
	 * @param response
	 * 
	 * @return	用户授权页面视图
	 */
	@RequestMapping(params="method=forwardAuthJsp")
	public String forwardAuth(HttpServletResponse response){
		return "auth/user_auth";
	} 
	
	/**
	 * 分页查询用户数据
	 * 
	 * @param response
	 * @param request
	 * @param sysUser
	 */
	@RequestMapping(params="method=pageQuery")
	public void pageQuery(HttpServletResponse response, HttpServletRequest request, SysUser sysUser) {
		sendJsonDataToClient(sysUserService.pageQuery(sysUser), response);
	}
	
	/**
	 *	用户新增 
	 * 
	 * @param response
	 * @param sysUser
	 */
	@RequestMapping(params="method=insert")
	public void insert(HttpServletResponse response, HttpServletRequest request, SysUser sysUser) {
		Message msg = (Message)sysUserService.insert(sysUser);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "新增用户：用户账号为："+sysUser.getUserId()+"，用户名称为："+sysUser.getUserName());
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 通过用户账号(sysUserId)查询用户信息
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping(params="method=get")
	public void get(HttpServletResponse response, String userId) {
		sendJsonDataToClient(sysUserService.get(userId), response);
	}
	
	/**
	 * 查询所有的用户信息
	 * 
	 * @param response
	 */
	@RequestMapping(params="method=list")
	public void list(HttpServletResponse response) {
		sendJsonDataToClient(sysUserService.list(), response);
	}
	
	/**
	 * 根据用户账号(sysUserId)进行用户信息修改
	 * 
	 * @param response
	 * @param sysUser
	 */
	@RequestMapping(params="method=update")
	public void update(HttpServletResponse response, HttpServletRequest request, SysUser sysUser) {
		Message msg = (Message)sysUserService.update(sysUser);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "修改用户：用户账号为："+sysUser.getUserId()+"，用户名称为："+sysUser.getUserName());
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 根据用户账号(sysUserId)删除用户
	 * 
	 * @param response
	 * @param ids
	 */
	@RequestMapping(params="method=deleteById")
	public void deleteById(HttpServletResponse response, HttpServletRequest request, String userIds) {
		LoginUser user = (LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Message msg = sysUserService.deleteById(userIds, user);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "删除用户：用户账号为："+userIds);
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 用户密码修改
	 * 
	 * @param response
	 * @param ids
	 */
	@RequestMapping(params="method=changePass")
	public void changePass(HttpServletResponse response, HttpServletRequest request, String oldPass, String newPass) {
		Message msg = null;
		Object loginUser = request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		if (null != loginUser) {
			msg = sysUserService.changePass((LoginUser)loginUser, oldPass, newPass);
		} else {
			msg = new Message(false, "登录状态时效，密码修改失败，请重新登录后再进行密码修改！");
		}
		sendJsonDataToClient(msg, response);
	}
}