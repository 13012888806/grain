package com.xinlin.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * ClassName: IndexController 
 * @Description: 系统首页管理控制器
 * @author Chenxl
 * @date 2015-5-15
 */
@Controller
@RequestMapping(value = "/index.do")
public class IndexController {

	/**
	 * 
	 * @Description: 如果以get请求的方式直接进行登录请求，则直接跳转到首页
	 * @param response
	 * @param request
	 * @param    
	 * @return String  
	 * @throws
	 * @author Chenxl
	 * @date 2015-5-15
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String forwardToLogin(HttpServletResponse response, HttpServletRequest request){
		return "public/index";
	}
}