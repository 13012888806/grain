package com.xinlin.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinlin.app.base.BaseController;
import com.xinlin.app.entity.pojo.Role;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.service.RoleService;
import com.xinlin.app.util.JCDFWebUtil;

/**
 * 角色管理控制器
 * 
 * @author JiangShui
 * @date	2013-10-13
 * 
 */
@Controller
@RequestMapping(value = "/auth/role.do")
public class RoleController extends BaseController{

	/**角色管理业务类*/
	@Resource(name="roleService")
	private RoleService roleService;
	
	/**
	 * 转发到角色管理页面
	 * 
	 * @param response
	 * 
	 * @return	角色管理jsp页面视图
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(HttpServletResponse response){
		return "auth/role_list";
	}
	
	/**
	 * 转发到角色新增jsp页面
	 * 
	 * @param response
	 * 
	 * @return	角色新增jsp页面视图
	 */
	@RequestMapping(params="method=forwardAddJsp")
	public String forwardAddJsp(HttpServletResponse response){
		return "auth/role_add";
	} 
	
	/**
	 * 转发到角色修改jsp页面
	 * 
	 * @param response
	 * 
	 * @return	角色修改jsp页面视图
	 */
	@RequestMapping(params="method=forwardEditJsp")
	public String forwardEditJsp(HttpServletResponse response){
		return "auth/role_edit";
	} 
	
	/**
	 * 转发到角色授权jsp页面
	 * 
	 * @param response
	 * 
	 * @return	角色授权jsp页面视图
	 */
	@RequestMapping(params="method=forwardAuthJsp")
	public String forwardAuthJsp(HttpServletResponse response){
		return "auth/role_auth";
	} 
	
	/**
	 * 分页查询角色数据
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping(params="method=pageQuery")
	public void pageQuery(HttpServletResponse response, HttpServletRequest request, Role role) {
		sendJsonDataToClient(roleService.pageQuery(role), response);
	}
	
	/**
	 *	角色新增 
	 * 
	 * @param response
	 * @param role
	 */
	@RequestMapping(params="method=insert")
	public void insert(HttpServletResponse response, HttpServletRequest request, Role role) {
		Message msg = (Message)roleService.insert(role);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "新增角色：角色名称为："+role.getRoleName());
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 通过角色编号(roleId)查询角色信息
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping(params="method=get")
	public void get(HttpServletResponse response, String roleId) {
		sendJsonDataToClient(roleService.get(roleId), response);
	}
	
	/**
	 * 查询所有的角色信息
	 * 
	 * @param response
	 */
	@RequestMapping(params="method=list")
	public void list(HttpServletResponse response) {
		sendJsonDataToClient(roleService.list(), response);
	}
	
	/**
	 * 根据角色编号(roleId)进行角色信息修改
	 * 
	 * @param response
	 * @param role
	 */
	@RequestMapping(params="method=update")
	public void update(HttpServletResponse response, HttpServletRequest request, Role role) {
		Message msg = (Message)roleService.update(role);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "修改角色：角色id为："+role.getRoleId());
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 根据角色编号(roleId)删除角色
	 * 
	 * @param response
	 * @param ids
	 */
	@RequestMapping(params="method=deleteById")
	public void deleteById(HttpServletResponse response, HttpServletRequest request, String roleIds) {
		Message msg = (Message)roleService.deleteById(roleIds);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "删除角色：角色id为："+roleIds);
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 加载角色树
	 * 
	 * @param response
	 */
	@RequestMapping(params="method=loadRoleTree")
	public void loadRoleTree(HttpServletResponse response, String userId) {
		sendJsonDataToClient(roleService.loadRoleTree(userId), response);
	}
	
	/**
	 * 新增用户与角色的对应关系信息
	 * 
	 * @param response
	 * @param roleIds
	 * @param userId
	 */
	@RequestMapping(params="method=insertRoleUserMap")
	public void insertRoleUserMap(HttpServletResponse response, HttpServletRequest request, String roleIds, String userId){
		Message msg = roleService.insertRoleUserMap(roleIds, userId);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "为用户分配角色：用户id："+userId+"，角色id："+roleIds);
		}
		sendJsonDataToClient(msg, response);
	}
}
