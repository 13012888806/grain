package com.xinlin.app.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.exceptions.JedisException;

import com.xinlin.app.dao.SysUserDao;
import com.xinlin.app.entity.pojo.SysUser;
import com.xinlin.app.entity.vo.LoginUser;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.util.StaticVar;
import com.xinlin.app.util.platform.jedis.JedisOpExecuter;

/**
 * 
 * ClassName: LoginServiceImpl 
 * @Description: 用户管理业务类实现
 * @author Chenxl
 * @date 2015-5-15
 */
@Service(value="loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

	/**用户数据库操作接口*/
	@Resource(name="sysUserDao")
	private SysUserDao sysUserDao;
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.service.ILoginService#login(java.lang.String, java.lang.String)
	 */
	public Object login(String userId, String userPass) {
		LoginUser loginUser = null;
		//验证账号密码非空
		if(null == userId || null == userPass || "".equals(userId.trim()) || "".equals(userPass.trim())) {
			return new Message(false, "账号或者密码不能为空！");
		}
		try {
			userId = userId.trim();
			userPass = URLDecoder.decode(userPass.trim(), "utf-8");
			SysUser sysUser = sysUserDao.get(userId);
			//验证账号密码是否正确
			if(null == sysUser || !sysUser.getUserPass().equals(userPass)) {
				return new Message(false, "账号或者密码不正确！");
				//验证通过
			} else {
				//获取用户本次登录的唯一令牌
				String token = UUID.randomUUID().toString();
				//产生放到session中的登录用户信息
				loginUser = new LoginUser();
				loginUser.setUserId(sysUser.getUserId());
				loginUser.setUserName(sysUser.getUserName());
				loginUser.setToken(token);
				loginUser.setUserType(sysUser.getUserType());
				//将令牌存于redis缓存(为防止redis中key的覆盖，所有用户令牌key=token_前缀+账号)
				JedisOpExecuter.putSingleObject(StaticVar.REDIS_SESSION_KEY_PREFIX+sysUser.getUserId(), token);
				//获取用户权限编码并缓存到redis中
				List<String> menuCodes = sysUserDao.queryUserMenuCode(userId);
				JedisOpExecuter.putSingleObject(StaticVar.REDIS_MENU_CODE_KEY_PREFIX+sysUser.getUserId(), menuCodes);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JedisException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginUser;
	}
}