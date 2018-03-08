package com.xinlin.app.dao;

import java.util.List;

import com.xinlin.app.base.BaseDao;
import com.xinlin.app.entity.pojo.Auth;
import com.xinlin.app.entity.pojo.Menu;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.entity.vo.PageQueryParams;

/**
 * 菜单功能数据库访问接口
 * 
 * @author Jiangshui
 * @date	2013-10-13
 * 
 */
public interface MenuDao extends BaseDao<Menu>{

	/**
	 * 分页查询菜单数据
	 * 
	 * @param menu	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public Page pageQuery(Menu menu) throws Exception;
	
	/**
	 * 根据父节点编码，查询所有的功能菜单信息(不包含按钮)
	 * 
	 * @param parentMenuCode	父节点编码，如果为空则查询所有的根节点
	 * 
	 * @return	对应父节点编码的所有子节点(菜单)信息
	 */
	public abstract List<Menu> findMenuByParentMenuCode(String parentMenuCode);

	/**
	 * 根据父节点编码，查询所有的功能按钮信息(不包含菜单)
	 * 
	 * @param parentMenuCode	父节点编码
	 * 
	 * @return	对应父节点编码的所有子节点(按钮)信息
	 */
	public abstract List<Menu> findButtonByParentMenuCode(String parentMenuCode);

	/**
	 * 根据父节点编码，查询所有的功能菜单信息(包含菜单和按钮)
	 * 
	 * @param parentMenuCode	父节点编码
	 * 
	 * @return	对应父节点编码的所有子节点信息
	 */
	public abstract List<Menu> findAllByParentMenuCode(String parentMenuCode);
	
	/**
	 * 查询所有的菜单和编码信息(用于内存计算产生树)
	 * 
	 * @param menuType	0:查询所有菜单和按钮资源,1:功能菜单，2：功能按钮
	 * @return
	 */
	public  List<Menu> findAllMenuAndButtonCode(Integer menuType);
	
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
