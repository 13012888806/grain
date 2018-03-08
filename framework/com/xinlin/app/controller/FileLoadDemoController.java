package com.xinlin.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinlin.app.base.BaseController;
import com.xinlin.app.service.FileLoadService;


/**
 * 文件上传/下载控制器
 * Demo
 * 
 * @author jxq
 * @date	2013-10-22
 * 
 */
@Controller
@RequestMapping(value = "/fileload/xinlinfileDemo.do")
public class FileLoadDemoController extends BaseController{
	
	@Resource(name="fileLoadService")
	private FileLoadService fileLoadService;
	
	
	/**
	 */
	@RequestMapping(method = RequestMethod.GET)
	//@RequestMapping(params="method=page")
	public String forwardToPage(HttpServletResponse response){
		HttpSession ss = null;
		return "fileload/file_list";
	}
	

	
	

}
