package com.xinlin.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinlin.app.util.platform.adapter.TestWS;


/**
 * 接口适配，测试页面专用
 * 
 * @author jxq
 * @date 2013-11-01
 *
 */

@Controller
@RequestMapping(value = "/xinlinadap")
public class WSController {



	@RequestMapping(value = "/adapter.do")
	public String showAdapter(ModelMap model) {
		return "adapter";
	}
	
	@RequestMapping(value = "/adapterOp.do")
	public String adapterOp(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("requestXML", request.getParameter("requestXML"));
		request.setAttribute("inputURL", request.getParameter("inputURL"));
		
		return "adapterOp";
	}
	

	@RequestMapping(value = "/adapterPost.do")
	public void adapterPost(HttpServletRequest request,
			HttpServletResponse response) {
		String orgCodeStr = request.getParameter("orgCodeStr") == null ? "":(String)request.getParameter("orgCodeStr");
		String type = request.getParameter("type") == null ? "": (String)request.getParameter("type");
		TestWS ws = new TestWS();
		String resJson = ws.getResJson(orgCodeStr, type);
		try {
			response.getWriter().write(resJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
