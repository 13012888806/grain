package com.xinlin.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinlin.app.base.BaseController;
import com.xinlin.app.entity.pojo.JcdfLog;
import com.xinlin.app.service.JcdfLogService;

/**
 * 日志管理
 * 
 * @author JXQ
 * @date	2013-11-12
 * 
 */
@Controller
@RequestMapping(value = "/sys/syslog.do")
public class SysLogController extends BaseController{

	/**系统日志管理业务类*/
	@Resource(name="jcdfLogService")
	private JcdfLogService jcdfLogService;
	
	/**
	 * 转发到日志列表页面
	 * 
	 * @param response
	 * 
	 * @return	用户管理页面视图
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String forwardListJsp(HttpServletResponse response){
		return "public/syslog_list";
	}
	
	
	/**
	 * 分页查询用户数据
	 * 
	 * @param response
	 * @param request
	 * @param sysUser
	 */
	@RequestMapping(params="method=pageQuery")
	public void pageQuery(HttpServletResponse response, HttpServletRequest request, JcdfLog log) {
		sendJsonDataToClient(jcdfLogService.pageQuery(log), response);
	}
	
	
}