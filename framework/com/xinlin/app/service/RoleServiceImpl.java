package com.xinlin.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinlin.app.dao.MenuDao;
import com.xinlin.app.dao.RoleDao;
import com.xinlin.app.entity.pojo.Auth;
import com.xinlin.app.entity.pojo.Role;
import com.xinlin.app.entity.pojo.RoleUserMap;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.entity.vo.TreeNode;
import com.xinlin.app.util.JCDFStringUtil;
import com.xinlin.app.util.JCDFWebUtil;

/**
 * 角色管理业务类实现
 * 
 * @author JiangShui
 * @date	2013-10-13
 *
 */
@Service(value="roleService")
@Transactional
public class RoleServiceImpl implements RoleService{

	/**角色数据库操作接口*/
	@Resource(name="roleDao")
	private RoleDao roleDao;
	/**功能菜单数据库访问接口*/
	@Resource(name="menuDao")
	private MenuDao menuDao;
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.service.IRoleService#pageQuery(com.xinlin.app.entity.pojo.Role)
	 */
	@Override
	public Page pageQuery(Role role) {
		Page page = null;
		try {
			return roleDao.pageQuery(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService
	 */
	@Override
	public Role get(String id) {
		return roleDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService
	 */
	@Override
	public Object insert(Role t) {
		try {
			Role checkRole = new Role();
			checkRole.setRoleName(t.getRoleName());
			if (null != roleDao.checkRoleIsExist(checkRole)) {
				return new Message(false, "对应名称的角色已经存在！");
			} else {
				t.setRoleId(UUID.randomUUID().toString());
				roleDao.insert(t);
				return new Message(true, "新增成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Message(false, "新增失败！");
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService
	 */
	@Override
	public List<Role> list() {
		return roleDao.list();
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService
	 */
	@Override
	public Object update(Role t) {
		try {
			Role checkRole = new Role();
			checkRole.setRoleName(t.getRoleName());
			checkRole.setRoleId(t.getRoleId());
			if (null != roleDao.checkRoleIsExist(checkRole)) {
				return new Message(false, "对应名称的角色已经存在！");
			} else {
				roleDao.update(t);
				//记录日志
				JCDFWebUtil.Log("角色管理", "修改角色:"+t.getRoleName());
				return new Message(true, "修改成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Message(false, "修改失败！");
	}
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService
	 */
	@Override
	public Object deleteById(String roleIds) {
		String message = null;
		int successNum = 0;
		int totleNum = 0;
		Auth auth = new Auth();
		//AuthType=1:表示角色权限，2：表示用户权限
		auth.setAuthType(1);
		RoleUserMap roleUserMap = new RoleUserMap();
		
		if (null != roleIds) {
			String[] roleIdsArr = roleIds.split(",");
			totleNum = roleIdsArr.length;
			for (String roleId : roleIdsArr) {
				try {
					//删除角色
					roleDao.deleteById(roleId);
					//删除角色与权限的映射关系
					auth.setUserRoleId(roleId);
					menuDao.deleteUserOrRoleAuthByAuth(auth);
					//删除用户与角色的映射关系
					roleUserMap.setRoleId(roleId);
					roleDao.deleteRoleUserMap(roleUserMap);
					successNum = successNum + 1;
					JCDFWebUtil.Log("角色管理", "删除角色roleID:"+roleId);
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
	 * @see com.xinlin.app.service.IRoleService#loadRoleTree(java.lang.String)
	 */
	@Override
	public List<TreeNode> loadRoleTree(String userId) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		List<TreeNode> childNodes = new ArrayList<TreeNode>();
		TreeNode node = null;
		RoleUserMap roleUserMap = new RoleUserMap();
		List<String> userOrRoles = null;
		List<Role> roles = roleDao.list();
		if(null != roles) {
			for (Role role : roles) {
				node = new TreeNode();
				node.setId(role.getRoleId());
				node.setText(role.getRoleName());
				node.setIconCls("icon-group");
				//根据当前指定用户是否拥有指定的角色而决定是否需要勾选当前角色节点
				if(null == userOrRoles) {
					roleUserMap.setUserId(userId);
					userOrRoles = roleDao.getRoleUserMap(roleUserMap);
				}
				if (null != userOrRoles && userOrRoles.contains(role.getRoleId())) {
					node.setChecked(true);
				}
				childNodes.add(node);
			}
		}
		if (childNodes.size() > 0) {
			node = new TreeNode();
			node.setId("");
			node.setText("系统角色");
			node.setChildren(childNodes);
			treeNodes.add(node);
		}
		//释放集合中的缓存数据
		if(null != userOrRoles) {
			userOrRoles.clear();
			userOrRoles = null;
		}
		return treeNodes;
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.service.IRoleService#pageQuery(java.lang.String, java.lang.String)
	 */
	@Override
	public Message insertRoleUserMap(String roleIds, String userId) {
		RoleUserMap roleUserMap = null;
		try {
			//先删除所有的老资源权限
			roleUserMap = new RoleUserMap();
			roleUserMap.setUserId(userId);
			roleDao.deleteRoleUserMap(roleUserMap);
			//遍历保存本次新的资源权限
			if (JCDFStringUtil.isNotNullAndEmpty(roleIds)) {
				String[] roleIdsArr = roleIds.split(",");
				for (String roleId : roleIdsArr) {
					roleUserMap = new RoleUserMap();
					roleUserMap.setRoleId(roleId);
					roleUserMap.setUserId(userId);
					roleDao.insertRoleUserMap(roleUserMap);
				}
			}
			return new Message(true, "权限数据保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Message(false, "权限数据保存失败！");
	}
}