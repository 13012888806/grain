package com.xinlin.app.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.service.LoginService;
import com.xinlin.app.util.StaticVar;

/**
 * 
 * ClassName: LoginController 
 * @Description: 用户登录管理控制器
 * @author Chenxl
 * @date 2015-5-15
 */
@Controller
@RequestMapping(value = "/login.do")
public class LoginController {

	/**用户管理业务类*/
	@Resource(name="loginService")
	private LoginService loginService;
	
	/**
	 * 如果以get请求的方式直接进行登录请求，则直接跳转到登录页面
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String forwardToLogin(HttpServletResponse response, HttpServletRequest request){
		return "public/login";
	}
	
	/**
	 * 用户登录
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String login(HttpServletResponse response, HttpServletRequest request, String userId, String userPass) throws IOException{
		Object result = loginService.login(userId, userPass);
		if(null == result) {
			request.setAttribute("userId", userId);
			request.setAttribute("msg", "登录失败！");
			return "public/login";
		} else {
			if (result instanceof Message) {
				Message msg = (Message)result;
				request.setAttribute("userId", userId);
				request.setAttribute("msg", msg.getMsg());
				return "public/login";
			} else {
				request.getSession().setAttribute(StaticVar.LOGIN_USER_KEY, result);
				response.sendRedirect("index.do");
				return null;
			}
		}
	}
	
	/**
	 * 用户登出
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(params="method=loginOut")
	public String loginOut(HttpServletResponse response, HttpServletRequest request){
		request.getSession().removeAttribute(StaticVar.LOGIN_USER_KEY);
    	request.getSession().invalidate();
    	return "public/login";
	}
}
