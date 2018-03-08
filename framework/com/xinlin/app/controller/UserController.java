package com.xinlin.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xinlin.app.entity.pojo.User;
import com.xinlin.app.service.UserService;




@Controller
@RequestMapping(value = "/xinlin")
public class UserController {

	@Resource(name="userService")
	private UserService iUserService;

	/*@Autowired
	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}*/



	@RequestMapping(method = RequestMethod.GET)
	public String showForm(ModelMap model) {
		List<User> users = iUserService.list();
		model.addAttribute("users", users);
		return "hello";
	}

	@RequestMapping(value = "/list.do")
	public String showMainForm(ModelMap model) {
		List<User> users = iUserService.list();
		model.addAttribute("users", users);
		return "hello";
	}
	
	@RequestMapping(value = "/add.do")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("hello");
		String userName = "";
		String password = "";
		try {
			userName = request.getParameter("userName") ;
			password =  request.getParameter("password") ;
		} catch (Exception e) {
		}
		 
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUserName(userName);
		user.setPassword(password);
		iUserService.insert(user);
		List<User> list = iUserService.list();
		mv.addObject("users", list);
		return mv;
	}

	@RequestMapping(value = "/detail.do")
	public String detail(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		User user = iUserService.get(id);
		model.addAttribute("user", user);
		return "detail";
	}

	@RequestMapping(value = "/delete.do")
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		iUserService.deleteById(id);
		ModelAndView mv = new ModelAndView("hello");
		List<User> list = iUserService.list();
		mv.addObject("users", list);
		return mv;
	}

	@RequestMapping(value = "/toupdate.do")
	public String toUpdate(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		User user = iUserService.get(id);
		model.addAttribute("user", user);
		return "update";
	}

	@RequestMapping(value = "/update.do")
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("sid");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setPassword(password);
		iUserService.update(user);
		ModelAndView mv = new ModelAndView("hello");
		List<User> list = iUserService.list();
		mv.addObject("users", list);
		return mv;
	}
}
