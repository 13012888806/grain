package com.xinlin.app.util.platform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xinlin.app.base.BaseController;
import com.xinlin.app.entity.vo.LoginUser;
import com.xinlin.app.util.StaticVar;
import com.xinlin.app.util.platform.jedis.JedisOpExecuter;

/**
 * 登录及权限控制过滤器
 * 
 * @author JiangShui
 * @date	2013-10-28
 */
public class LoginAndAuthFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
	    HttpServletResponse response = (HttpServletResponse)rsp; 
		HttpSession session = request.getSession();
		Object user = session.getAttribute(StaticVar.LOGIN_USER_KEY);
		String url = request.getRequestURL().toString();
		if (url.endsWith("login.jsp") || url.endsWith("login.do") || url.endsWith("index.jsp")) {
			chain.doFilter(request, response);
		//首先如果登录成功放行,登录请求处理默认放行
		} else if ((null != session && null != user)) {
			LoginUser loginUser = (LoginUser)user;
			//检查当前访问用户的令牌是否与服务器redis中缓存的令牌一致，如果不一致则代表有用户重新登录了，剔除当前用户
			if (!loginUser.getToken().equals(JedisOpExecuter.getSingleObject(StaticVar.REDIS_SESSION_KEY_PREFIX+loginUser.getUserId()))) {
				String requestType = request.getHeader("X-Requested-With");
				//根据当前请求的类型给予不同的响应回复(ajax或者普通请求)
				if(requestType != null && requestType.equals("XMLHttpRequest")) {
					BaseController.sendJsonDataToClient("{\"noLogin\":true,\"rows\":[],\"msg\":'您的账号在另一地点登录，您被迫下线，如非本人操作，可能是密码泄露，建议您及时修改密码！'}", response);
	            } else {
	            	request.getSession().removeAttribute(StaticVar.LOGIN_USER_KEY);
	            	request.getSession().invalidate();
	            	request.setAttribute("msg", "您的账号在另一地点登录，您被迫下线，如非本人操作，可能是密码泄露，建议您及时修改密码！");
	            	request.getRequestDispatcher("/error.jsp").forward(request, response);
	            }
			} else {
				chain.doFilter(request, response);
			}
		} else {
			String requestType = request.getHeader("X-Requested-With");
			//根据当前请求的类型给予不同的响应回复(ajax或者普通请求)
			if(requestType != null && requestType.equals("XMLHttpRequest")) {
//				response.sendError();
				BaseController.sendJsonDataToClient("{\"noLogin\":true,\"rows\":[],\"msg\":'你没有登录，无权进行该操作！'}", response);
            } else {
            	request.getSession().invalidate();
            	request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}