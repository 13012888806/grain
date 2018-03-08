package com.xinlin.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.xinlin.app.base.BaseController;
import com.xinlin.app.util.platform.plugin.DataImportPlugin;

/**
 * Excel文件导出
 * 
 * @author JiangShui
 * @date	2013-10-24
 * 
 */
@Controller
@RequestMapping(value = "/import.do")
public class ImportController extends BaseController{

	/**
	 * 转发到数据导入页面
	 * 
	 * @param response
	 * 
	 * @return	数据导入jsp页面
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(HttpServletResponse response, HttpServletRequest request){
		return "test/import";
	}
	
	/**
	 * 数据导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void importData(HttpServletResponse response, HttpServletRequest request, MultipartFile importFile, String importId){
		sendJsonDataToClient((new DataImportPlugin()).parseFile(importFile, request), response);
	}
}
