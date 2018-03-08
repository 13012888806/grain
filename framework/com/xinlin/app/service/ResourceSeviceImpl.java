package com.xinlin.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinlin.app.dao.MenuDao;
import com.xinlin.app.dao.ResourceDao;
import com.xinlin.app.entity.pojo.Auth;
import com.xinlin.app.entity.pojo.Menu;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.entity.vo.TreeNode;
import com.xinlin.app.util.JCDFStringUtil;

/**
 * 资源管理业务类实现
 * 
 * @author JiangShui
 * @date	2013-10-13
 *
 */
@Service(value="resourceService")
@Transactional
public class ResourceSeviceImpl implements ResourceService {

	private static Log log = LogFactory.getLog(ResourceSeviceImpl.class);
	/**功能菜单数据库访问接口*/
	@Resource(name="menuDao")
	private MenuDao menuDao;
	/**资源数据库访问接口*/
	@Resource(name="resourceDao")
	private ResourceDao resourceDao;
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.service.IResourceService#pageQuery(com.xinlin.app.entity.Menu)
	 */
	@Override
	public Page pageQuery(Menu menu) {
		Page page = null;
		try {
			page = menuDao.pageQuery(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.service.IResourceService#loadTree()
	 */
	@Override
	public List<TreeNode> loadTree(int menuType, String userId, String roleId) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		TreeNode node = null;
		
		//指定用户或者角色所拥有的权限资源编码(一次性加载到集合中缓存在内存中，提高查询速度)
		List<String> auths = null;
		if (null != userId && !"".equals(userId)) {
			auths = loadUserOrRoleAuth(2, userId, 1);
		} else if (null != roleId && !"".equals(roleId)) {
			auths = loadUserOrRoleAuth(1, roleId, 1);
		}
		//所有的资源菜单按钮信息，用于内存来构造资源树(一次性加载到集合中缓存在内存中，提高查询速度)
		List<Menu> allMenuAndButtons = menuDao.findAllMenuAndButtonCode(menuType);
		List<Menu> rootMenus = findNodeByParentNode(null, menuType, allMenuAndButtons);
		if(null != rootMenus) {
			for (Menu menu : rootMenus) {
				node = new TreeNode();
				node.setId(menu.getMenuCode());
				//0表示查询所有资源(菜单和按钮)用于授权，此时不需要展示资源编码
				if(0 == menuType) {
					node.setText(menu.getMenuName());
				} else {
					node.setText(menu.getMenuName()+"("+menu.getMenuCode()+")");
				}
				
				List<Menu> childMenus = findNodeByParentNode(menu.getMenuCode(), menuType, allMenuAndButtons);
				if(null != childMenus && childMenus.size() > 0) {
					node.setIconCls("icon-tree-folder");
					//递归查询子节点
					node.setChildren(loadChildTree(childMenus, menuType, userId, roleId, allMenuAndButtons, auths));
				} else{
					node.setIconCls("icon-tree-file");
					//查询当前指定的用户或者角色是否拥有指定节点的权限
					if (null != auths && auths.contains(menu.getMenuCode())) {
						node.setChecked(true);
					}
				}
				treeNodes.add(node);
			}
		}
		//释放缓存资源
		if(null != allMenuAndButtons) {
			allMenuAndButtons.clear();
			allMenuAndButtons = null;
		}
		if(null != auths) {
			auths.clear();
			auths = null;
		}
		return treeNodes;
	}
	
	/**
	 * 递归查询指定父节点编码的所有子节点信息
	 * 
	 * @param parentMenuCode	父节点编码
	 * @param menuType	类型，0：查找所有子节点，1：仅查找指定父节点的所有菜单子节点(不包含按钮)
	 * @param userId	用户的id(用于查询指定用户是否有指定功能菜单的权限，有则勾选上)
	 * @param roleId	角色的id(用于查询指定角色是否有指定功能菜单的权限，有则勾选上)
	 * @param allMenuAndButtons	资源树数据(一次性查出，缓存在集合内存用户，提高查询速度)
	 * @param auths	指定用户或者角色所拥有的权限资源编码
	 * 
	 * @return	指定父节点编码的所有子节点信息
	 */
	public List<TreeNode> loadChildTree(List<Menu> childMenus, int menuType, String userId, String roleId, List<Menu> allMenuAndButtons, List<String> auths) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		TreeNode node = null;
		if(null != childMenus) {
			for (Menu menu : childMenus) {
				node = new TreeNode();
				node.setId(menu.getMenuCode());
				if(0 == menuType) {
					node.setText(menu.getMenuName());
				} else {
					node.setText(menu.getMenuName()+"("+menu.getMenuCode()+")");
				}
				List<Menu> supChildMenus = findNodeByParentNode(menu.getMenuCode(), menuType, allMenuAndButtons);
				if(null != supChildMenus && supChildMenus.size() > 0) {
					node.setIconCls("icon-tree-folder");
					//递归查询子节点
					node.setChildren(loadChildTree(supChildMenus, menuType, userId, roleId, allMenuAndButtons, auths));
				} else{
					node.setIconCls("icon-tree-file");
					//查询当前指定的用户或者角色是否拥有指定节点的权限
					if (null != auths && auths.contains(menu.getMenuCode())) {
						node.setChecked(true);
					}
				}
				treeNodes.add(node);
			}
		}
		return treeNodes;
	}
	
	/**
	 * 加载指定用户或者角色所拥有的权限资源编码
	 * 
	 * @param authType	1:角色，2：用户
	 * @param userOrRoleId
	 */
	public List<String> loadUserOrRoleAuth(int authType, String userOrRoleId, int a) {
		Auth auth = new Auth();
		auth.setAuthType(authType);
		auth.setUserRoleId(userOrRoleId);
		return menuDao.getUserOrRoleAuth(auth);
	}
	
	/**
	 * 查找指定父节点的子节点	
	 * 
	 * @param parentMenuCode	父节点编号；如果为null，则表示查找根节点
	 * @param menuType	类型，0：查找所有子节点，1：仅查找指定父节点的所有菜单子节点(不包含按钮)
	 * @param allMenuAndButtons	资源树数据(一次性查出，缓存在集合内存用户，提高查询速度)
	 * @return
	 */
	public List<Menu> findNodeByParentNode(String parentMenuCode, int menuType, List<Menu> allMenuAndButtons) {
		List<Menu> childMenus = new ArrayList<Menu>();
		if(null == parentMenuCode) {
			for (Menu menu : allMenuAndButtons) {
				if(null == menu.getParentMenuCode() || "".equals(menu.getParentMenuCode().trim())) {
					childMenus.add(menu);
				}
			}
		} else {
			for (Menu menu : allMenuAndButtons) {
				if(parentMenuCode.equals(menu.getParentMenuCode().trim())) {
					childMenus.add(menu);
				}
			}
		}
		return childMenus;
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.service.IResourceService#deleteMenuById(java.lang.String)
	 */
	public Message deleteMenuById(String menuCodes) {
		String message = null;
		int successNum = 0;
		int totleNum = 0;
		Auth auth = new Auth();
		
		if (null != menuCodes) {
			String[] menuCodesArr = menuCodes.split(",");
			totleNum = menuCodesArr.length;
			for (String menuCode : menuCodesArr) {
				try {
					//由于在删除“菜单”时会级联删除下面的按钮，所以先查询出来，后面将其对应的授权信息也删除
					List<Menu> deleteAuthMenu = menuDao.findButtonByParentMenuCode(menuCode);
					//删除资源
					menuDao.deleteById(menuCode);
					//将用户或者角色中对应该资源的权限也删除
					auth.setMenuCode(menuCode);
					menuDao.deleteUserOrRoleAuthByAuth(auth);
					if(null != deleteAuthMenu && deleteAuthMenu.size() > 0) {
						for (Menu m : deleteAuthMenu) {
							auth.setMenuCode(m.getMenuCode());
							menuDao.deleteUserOrRoleAuthByAuth(auth);
						}
					}
					successNum = successNum + 1;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//组装提示消息，响应到页面，给予操作提示
		if (0 == successNum) {
			message = "删除失败，可能是对应的角色正在被用户使用！";
		} else if (totleNum == successNum) {
			message = "成功删除"+successNum+"条记录！";
		} else if (successNum < totleNum) {
			message = "成功删除"+(successNum)+"条记录，失败删除"+(totleNum-successNum)+"条记录（可能是对应的角色正在被用户使用）！";
		}
		return new Message(successNum > 0, message);
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.service.IResourceService#getMenu(java.lang.String)
	 */
	public Menu getMenu(String menuCode) {
		return menuDao.get(menuCode);
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.service.IResourceService#insertMenu(com.xinlin.app.entity.Menu)
	 */
	public Message insertMenu(Menu t) {
		Message msg = null;
		try {
			if(null != menuDao.get(t.getMenuCode())) {
				msg = new Message(false, "对应编码的资源已经存在，新增失败！");
			} else {
				t.setCreateTime(new Date());
				//为了兼容oracle数据库(主键中不能存入空字符串，这里存入一个空格)
				if (!JCDFStringUtil.isNotNullAndEmpty(t.getParentMenuCode())) {
					t.setParentMenuCode(" ");
				}
				menuDao.insert(t);
				msg = new Message(true, "新增成功！", t.getMenuCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null==msg)msg = new Message(false, "新增失败！");
		return msg;
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.service.IResourceService#updateMenu(com.xinlin.app.entity.Menu)
	 */
	public Message updateMenu(Menu t) {
		try {
			menuDao.update(t);
			//为了兼容oracle数据库(主键中不能存入空字符串，这里存入一个空格)
			if (!JCDFStringUtil.isNotNullAndEmpty(t.getParentMenuCode())) {
				t.setParentMenuCode(" ");
			}
			return new Message(true, "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Message(false, "修改失败！");
	}
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.service.IResourceService#insertUserOrRoleAuth(com.xinlin.app.entity.Auth)
	 */
	@Override
	public Message insertUserOrRoleAuth(String menuCodes, String userRoleId, Integer authType) {
		Auth auth = null;
		try {
			//先删除所有的老资源权限
			auth = new Auth();
			auth.setAuthType(authType);
			auth.setUserRoleId(userRoleId);
			menuDao.deleteUserOrRoleAuthByAuth(auth);
			//遍历保存本次新的资源权限
			if (JCDFStringUtil.isNotNullAndEmpty(menuCodes)) {
				String[] menuCodesArr = menuCodes.split(",");
				for (String menuCode : menuCodesArr) {
					auth = new Auth();
					auth.setAuthType(authType);
					auth.setUserRoleId(userRoleId);
					auth.setMenuCode(menuCode);
					menuDao.insertUserOrRoleAuth(auth);
				}
			}
			return new Message(true, "权限数据保存成功！");
		} catch (Exception e) {
			if (e.getCause().getMessage().contains("ORA-00001")) {
				log.error("重复授权，忽略...");
				return new Message(true, "授权冲突，稍后重试(造成该问题的原因是：你和另一个用户在对同一个用户，同时进行重复授权)！");
			} else {
				e.printStackTrace();
			}
		}
		return new Message(false, "权限数据保存失败！");
	}
	
	public Object deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.xinlin.app.entity.pojo.Resource get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object insert(com.xinlin.app.entity.pojo.Resource t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<com.xinlin.app.entity.pojo.Resource> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object update(com.xinlin.app.entity.pojo.Resource t) {
		// TODO Auto-generated method stub
		return null;
	}
}
