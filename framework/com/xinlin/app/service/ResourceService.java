package com.xinlin.app.service;

import java.util.List;

import com.xinlin.app.base.BaseService;
import com.xinlin.app.entity.pojo.Menu;
import com.xinlin.app.entity.pojo.Resource;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.entity.vo.TreeNode;


/**
 * 资源管理业务类接口，负责定义业务类的所有接口协议
 * 
 * @author JiangShui
 * @date	2013-10-13
 * 
 */
public interface ResourceService extends BaseService<Resource>{

	/**
	 * 分页查询菜单数据
	 * 
	 * @param menu	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public Page pageQuery(Menu menu);
	
	/**
	 * 加载资源树
	 * 
	 * @param menuType	类型，0：查找所有子节点，1：仅查找指定父节点的所有菜单子节点(不包含按钮)
	 * @param userId	用户的id(用于查询指定用户是否有指定功能菜单的权限，有则勾选上)
	 * @param roleId	角色的id(用于查询指定角色是否有指定功能菜单的权限，有则勾选上)
	 * 
	 * @return	资源树所有节点数据
	 */
	public List<TreeNode> loadTree(int menuType,String userId, String roleId);
	
	/**
	 * 根据功能菜单编号(menuCode)删除功能菜单
	 * 
	 * @param menuCodes	将要删除的功能菜单编码，多个用逗号分隔
	 * 
	 * @return	删除操作结果消息
	 */
	public Message deleteMenuById(String menuCodes);

	/**
	 * 根据功能菜单编码获取功能菜单信息
	 * 
	 * @param menuCode
	 * @return
	 */
	public Menu getMenu(String menuCode);

	/**
	 * 新增一个功能菜单
	 * 
	 * @param t	将要新增的功能菜单信息实体
	 * @return	删除操作结果消息
	 */
	public Message insertMenu(Menu t);

	/**
	 * 修改功能菜单信息
	 * 
	 * @param t	将要修改的功能菜单信息实体
	 * @return	删除操作结果消息
	 */
	public Message updateMenu(Menu t);
	
	/**
	 * 新增用户或者角色的权限信息
	 * 
	 * @param menuCodes	功能菜单的编码，多个用逗号分隔
	 * @param userRoleId	用户或者角色id
	 * @param authType	权限类型：1：角色权限，2：用户权限
	 * @return
	 */
	public Message insertUserOrRoleAuth(String menuCodes, String userRoleId, Integer authType);
}
