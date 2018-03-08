package com.xinlin.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinlin.app.base.BaseController;
import com.xinlin.app.entity.pojo.Menu;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.service.ResourceService;
import com.xinlin.app.util.JCDFWebUtil;

/**
 * 资源管理控制器
 * 
 * @author JiangShui
 * @date	2013-10-13
 * 
 */
@Controller
@RequestMapping(value = "/auth/resource.do")
public class ResourceController extends BaseController{

	/**资源管理业务类*/
	@Resource(name="resourceService")
	private ResourceService resourceService;
	
	/**
	 * 转发到资源管理页面
	 * 
	 * @param response
	 * 
	 * @return	资源管理jsp页面视图
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(HttpServletResponse response){
		return "auth/resource_list";
	}
	
	/**
	 * 转发到菜单资源新增jsp页面
	 * 
	 * @param response
	 * 
	 * @return	菜单资源新增jsp页面视图
	 */
	@RequestMapping(params="method=forwardMenuAddJsp")
	public String forwardMenuAddJsp(HttpServletResponse response){
		return "auth/resource_menu_add";
	} 
	
	/**
	 * 转发到菜单资源修改jsp页面
	 * 
	 * @param response
	 * 
	 * @return	菜单资源修改jsp页面视图
	 */
	@RequestMapping(params="method=forwardMenuEditJsp")
	public String forwardMenuEditJsp(HttpServletResponse response){
		return "auth/resource_menu_edit";
	} 
	
	/**
	 * 转发到按钮资源新增jsp页面
	 * 
	 * @param response
	 * 
	 * @return	按钮资源新增jsp页面视图
	 */
	@RequestMapping(params="method=forwardButtonAddJsp")
	public String forwardButtonAddJsp(HttpServletResponse response){
		return "auth/resource_button_add";
	} 
	
	/**
	 * 转发到按钮资源修改jsp页面
	 * 
	 * @param response
	 * 
	 * @return	按钮资源修改jsp页面视图
	 */
	@RequestMapping(params="method=forwardButtonEditJsp")
	public String forwardButtonEditJsp(HttpServletResponse response){
		return "auth/resource_button_edit";
	} 
	
	/**
	 * 分页查询菜单数据
	 * 
	 * @param response
	 * @param request
	 * @param menu
	 * @return
	 */
	@RequestMapping(params="method=pageQuery")
	public void pageQuery(HttpServletResponse response, HttpServletRequest request, Menu menu) {
		sendJsonDataToClient(resourceService.pageQuery(menu), response);
	}
	
	/**
	 * 仅加载菜单功能树数据
	 * 用于资源管理左侧的菜单资源树
	 * 
	 * @param response
	 */
	@RequestMapping(params="method=loadMenuTree")
	public void loadMenuTree(HttpServletResponse response) {
		sendJsonDataToClient(resourceService.loadTree(1, null, null), response);
	}
	
	/**
	 *  加载菜单按钮功能树数据
	 *  用于授权中的资源树展示
	 * 
	 * @param response
	 * @param userId
	 * @param roleId
	 */
	@RequestMapping(params="method=loadAllTree")
	public void loadAllTree(HttpServletResponse response, String userId, String roleId) {
		sendJsonDataToClient(resourceService.loadTree(0, userId, roleId), response);
	}
	
	/**
	 * 根据功能菜单编号(menuCode)删除功能菜单
	 * 
	 * @param menuCodes	将要删除的功能菜单编码，多个用逗号分隔
	 * 
	 * @return	删除操作结果消息
	 */
	@RequestMapping(params="method=deleteMenuById")
	public void deleteMenuById(HttpServletResponse response, HttpServletRequest request, String menuCodes){
		Message msg = resourceService.deleteMenuById(menuCodes);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "删除菜单、按钮功能，编码为：" + menuCodes);
		}
		sendJsonDataToClient(msg, response);
	}

	/**
	 * 根据功能菜单编码获取功能菜单信息
	 * 
	 * @param menuCode
	 * @return
	 */
	@RequestMapping(params="method=getMenu")
	public void getMenu(HttpServletResponse response, String menuCode){
		sendJsonDataToClient(resourceService.getMenu(menuCode), response);
	}

	/**
	 * 新增一个功能菜单
	 * 
	 * @param t	将要新增的功能菜单信息实体
	 * @return	删除操作结果消息
	 */
	@RequestMapping(params="method=insertMenu")
	public void insertMenu(HttpServletResponse response, HttpServletRequest request, Menu t){
		Message msg = resourceService.insertMenu(t);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "新增菜单、按钮功能，编码为：" + t.getMenuCode()+"，名称为："+t.getMenuName());
		}
		sendJsonDataToClient(msg, response);
	}

	/**
	 * 修改功能菜单信息
	 * 
	 * @param t	将要修改的功能菜单信息实体
	 * @return	删除操作结果消息
	 */
	@RequestMapping(params="method=updateMenu")
	public void updateMenu(HttpServletResponse response, HttpServletRequest request, Menu t){
		Message msg = resourceService.updateMenu(t);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "修改菜单、按钮功能，编码为：" + t.getMenuCode()+"，名称为："+t.getMenuName());
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 新增用户或者角色权限信息
	 * 
	 * @param menuCodes	菜单或者按钮的编码
	 * @param userRoleId	用户或者角色id
	 * @param authType	权限类型：1：角色权限，2：用户权限
	 * 
	 * @return	删除操作结果消息
	 */
	@RequestMapping(params="method=insertUserOrRoleAuth")
	public void insertUserOrRoleAuth(HttpServletResponse response, HttpServletRequest request, String menuCodes, String userRoleId, Integer authType){
		Message msg = resourceService.insertUserOrRoleAuth(menuCodes, userRoleId, authType);
		if (null != msg && msg.isResult()) {
			if (1 == authType) {
				JCDFWebUtil.Log(request, "新增角色权限：角色id" + userRoleId +"，菜单或者按钮编码：" + menuCodes);
			} else {
				JCDFWebUtil.Log(request, "新增用户权限：用户id" + userRoleId +"，菜单或者按钮编码：" + menuCodes);
			}
		}
		sendJsonDataToClient(msg, response);
	}
}