package com.xinlin.app.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinlin.app.dao.MenuDao;
import com.xinlin.app.dao.RoleDao;
import com.xinlin.app.dao.SysUserDao;
import com.xinlin.app.entity.pojo.Auth;
import com.xinlin.app.entity.pojo.RoleUserMap;
import com.xinlin.app.entity.pojo.SysUser;
import com.xinlin.app.entity.vo.LoginUser;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.util.JCDFStringUtil;
import com.xinlin.app.util.StaticVar;

/**
 * 用户管理业务类实现
 * 
 * @author JiangShui
 * @date	2013-10-13
 *
 */
@Service(value="sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService{

	/**用户数据库操作接口*/
	@Resource(name="sysUserDao")
	private SysUserDao sysUserDao;
	/**功能菜单数据库访问接口*/
	@Resource(name="menuDao")
	private MenuDao menuDao;
	/**角色数据库操作接口*/
	@Resource(name="roleDao")
	private RoleDao roleDao;
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.service.ISysUserService#pageQuery(com.xinlin.app.entity.pojo.SysUser)
	 */
	@Override
	public Page pageQuery(SysUser sysUser) {
		Page page = null;
		try {
			page = sysUserDao.pageQuery(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.service.ISysUserService#deleteById(java.lang.String, com.xinlin.app.entity.pojo.SysUser)
	 */
	@Override
	public Message deleteById(String userIds, LoginUser loginUser) {
		String message = null;
		int successNum = 0;
		int totleNum = 0;
		boolean isHaveSuperUser = false;//本次删除操作是否存在超级管理员
		boolean isHavaSelf = false;//本次删除操作是否存在当前登录用户(当前登录用户不能删除自己)
		SysUser user = null;
		Auth auth = new Auth();
		auth.setAuthType(2);//AuthType=1:表示角色权限，2：表示用户权限
		RoleUserMap roleUserMap = new RoleUserMap();
		
		if (null != userIds) {
			String[] userIdsArr = userIds.split(",");
			totleNum = userIdsArr.length;
			for (String userId : userIdsArr) {
				try {
					//自己不允许删除自己
					if (userId.equals(loginUser.getUserId())) {
						isHavaSelf = true;
						continue;
					}
					//超级管理员不允许删除
					user = sysUserDao.get(userId);
					if (null != user && StaticVar.USER_TYPE_OF_SUPER_USER == user.getUserType()) {
						isHaveSuperUser = true;
						continue;
					}
					//删除用户
					sysUserDao.deleteById(userId);
					//删除用户与权限的映射关系
					auth.setUserRoleId(userId);
					menuDao.deleteUserOrRoleAuthByAuth(auth);
					//删除用户与角色的映射关系
					roleUserMap.setUserId(userId);
					roleDao.deleteRoleUserMap(roleUserMap);
					successNum = successNum + 1;
				} catch (Exception e) {
					e.printStackTrace();
				}
				user = null;
			}
		}
		//组装提示消息，响应到页面，给予操作提示
		if (0 == successNum) {
			message = "删除失败！" + ((isHaveSuperUser || isHavaSelf) ? "（注意：超级管理员和自己不允许删除）":"");
		} else if (totleNum == successNum) {
			message = "成功删除"+successNum+"条记录！";
		} else if (successNum < totleNum) {
			message = "成功删除"+(successNum)+"条记录，失败删除"+(totleNum-successNum)+"条记录！" + ((isHaveSuperUser || isHavaSelf) ? "（注意：超级管理员和自己不允许删除）":"");
		}
		return new Message(successNum > 0, message);
	}
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService
	 */
	@Override
	public Object deleteById(String userIds) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService
	 */
	@Override
	public SysUser get(String id) {
		return sysUserDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService
	 */
	@Override
	public Object insert(SysUser sysUser) {
		Message msg = new Message(false, "新增失败！");;
		try {
			if(null != sysUserDao.get(sysUser.getUserId())) {
				msg = new Message(false, "用户账号已存在，新增失败！");
			} else {
				sysUser.setCreateTime(new Date());
				//新添加的都默认设置为普通用户
				sysUser.setUserType(2);
				sysUser.setUserPass(JCDFStringUtil.encodePassword(StaticVar.DEFAULT_PASSWORD));
				sysUserDao.insert(sysUser);
				msg = new Message(true, "新增成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService
	 */
	@Override
	public List list() {
		return sysUserDao.list();
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService
	 */
	@Override
	public Object update(SysUser t) {
		try {
			sysUserDao.update(t);
			return new Message(true, "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Message(false, "修改失败！");
	}

	@Override
	public Message changePass(LoginUser loginUser, String oldPass, String newPass) {
		Message msg = null;
		try {
			if(!JCDFStringUtil.isNotNullAndEmpty(oldPass) || !JCDFStringUtil.isNotNullAndEmpty(newPass)) {
				msg = new Message(false, "原、新密码不能为空！");
			} else {
				String userId = loginUser.getUserId();
				oldPass = URLDecoder.decode(oldPass.trim(), "utf-8");
				newPass = URLDecoder.decode(newPass.trim(), "utf-8");
				SysUser sysUser = sysUserDao.get(userId);
				if(null == sysUser) {
					msg = new Message(false, "当前登录用户已不存在，可能是被销户！");
				} else if (!oldPass.equals(sysUser.getUserPass())) {
					msg = new Message(false, "原密码不正确！");
				} else {
					sysUser.setUserPass(newPass);
					sysUserDao.update(sysUser);
					msg = new Message(true, "密码修改成功，请重新登录！");
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null != msg ? msg : (new Message(false, "密码修改失败！"));
	}
}