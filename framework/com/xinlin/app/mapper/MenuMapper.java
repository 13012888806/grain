package com.xinlin.app.mapper;

import java.util.List;

import com.xinlin.app.entity.pojo.Auth;
import com.xinlin.app.entity.pojo.Menu;

/**
 * 功能菜单的Mybatis映射工具类
 * 
 * @author Jiangshui
 * @date	2013-10-13
 *
 */
public interface MenuMapper {

	/**
	 * 分页查询菜单数据
	 * 
	 * @param menu	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public List<Menu> pageQuery(Menu menu);
	
	/**
	 * 根据父节点编码，查询所有的功能菜单信息(不包含按钮)
	 * 
	 * @param parentMenuCode	父节点编码
	 * 
	 * @return	对应父节点编码的所有子节点(菜单)信息
	 */
	public List<Menu> findMenuByParentMenuCode(String parentMenuCode);
	
	/**
	 * 根据父节点编码，查询所有的功能按钮信息(不包含菜单)
	 * 
	 * @param parentMenuCode	父节点编码
	 * 
	 * @return	对应父节点编码的所有子节点(按钮)信息
	 */
	public List<Menu> findButtonByParentMenuCode(String parentMenuCode);
	
	/**
	 * 根据父节点编码，查询所有的功能菜单信息(包含菜单和按钮)
	 * 
	 * @param parentMenuCode	父节点编码
	 * 
	 * @return	对应父节点编码的所有子节点信息
	 */
	public List<Menu> findAllByParentMenuCode(String parentMenuCode);
	
	/**
	 * 查询所有的菜单和编码信息(用于内存计算产生树)
	 * 
	 * @param menuType
	 * @return
	 */
	public  List<Menu> findAllMenuAndButtonCode(Integer menuType);
	
	/**
	 * 新增功能菜单
	 * 
	 * @param role
	 * @return
	 */
	public int insert(Menu menu);

	/**
	 * 通过功能菜单编号(menuCode)查询功能菜单信息
	 * 
	 * @param id
	 * @return
	 */
	public Menu get(String menuCode);
	
	/**
	 * 根据功能菜单编号(menuCode)进行功能菜单信息修改
	 * 
	 * @param menu
	 */
	public void update(Menu menu);

	/**
	 * 根据功能菜单编号(menuCode)删除功能菜单
	 * 
	 * @param id
	 */
	public void deleteById(String menuCode);
	
	/**
	 * 新增用户或者角色权限信息
	 * 
	 * @param role
	 * @return
	 */
	public void insertUserOrRoleAuth(Auth auth);
	
	/**
	 * 根据指定的条件查询用户或者角色的资源权限编码
	 * 
	 * @param String
	 * @return
	 */
	public List<String> getUserOrRoleAuth(Auth auth);
	
	/**
	 * 根据指定的条件删除用户或者角色的权限信息
	 * 
	 * @param auth
	 * @return
	 */
	public void deleteUserOrRoleAuthByAuth(Auth auth);
}
