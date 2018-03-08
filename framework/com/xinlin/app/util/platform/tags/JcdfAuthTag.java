package com.xinlin.app.util.platform.tags;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.xinlin.app.entity.vo.LoginUser;
import com.xinlin.app.util.StaticVar;
import com.xinlin.app.util.platform.jedis.JedisOpExecuter;

/**
 * 系统权限标签业务处理
 * 
 * @author Jiangshui
 * @date	2013-10-23
 */
public class JcdfAuthTag extends TagSupport {
	/**权限编码*/
	private String code;
	
	@Override
	public int doStartTag() throws JspException {
		Object user = pageContext.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		if (null != user) {
			LoginUser loginUser = (LoginUser)user;
			//如果是超级管理员，则默认放行
			if (StaticVar.USER_TYPE_OF_SUPER_USER == loginUser.getUserType()) {
				return this.EVAL_BODY_INCLUDE;
			//普通用户进行权限校验
			} else {
				List<String> menuCodes = (List<String>)JedisOpExecuter.getSingleObject(StaticVar.REDIS_MENU_CODE_KEY_PREFIX+loginUser.getUserId());
				if(null != code && menuCodes.contains(code)) {
					return this.EVAL_BODY_INCLUDE;
				}
			}
		}
		return this.SKIP_BODY;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}