/**  
 * @Title: CusAction.java
 * @Package com.xinlin.baseInfo.action
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinlin.app.base.BaseController;
import com.xinlin.app.entity.vo.LoginUser;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.util.JCDFDateUtil;
import com.xinlin.app.util.JCDFJsonUtil;
import com.xinlin.app.util.StaticVar;
import com.xinlin.baseInfo.entity.Customer;
import com.xinlin.baseInfo.entity.CustomerVo;
import com.xinlin.baseInfo.service.CusService;

/**
 * ClassName: CusAction 
 * @Description: 客户信息维护的控制层
 * @author Chenxl
 * @date 2015-6-3
 */
@Controller
@RequestMapping(value="/base/cus.do")
public class CusAction extends BaseController {
	
	/**
	 * 客户信息管理业务类
	 */
	@Resource(name="cusService")
	private CusService cusService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(HttpServletResponse response){
		return "base/cus_list";
	}
	
	/**
	 * @Description: 分页查找客户信息
	 * @param @param response
	 * @param @param request
	 * @param @param map   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-3
	 */
	@RequestMapping(params="method=pageQuery")
	public void pageQuery(HttpServletResponse response, HttpServletRequest request, Customer customer) {
		sendJsonDataToClient(cusService.pageQuery(customer), response);
	}
	
	/**
	 * @Description: 客户信息弹出层-保存输入的合法数据
	 * @param response
	 * @param request
	 * @param customer   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=insertCus")
	public void insertCus(HttpServletResponse response, HttpServletRequest request,CustomerVo customer)
	{
		LoginUser loginUser = (LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Message msg = null;
		try {
			Map<String, Object> mapParam = JCDFJsonUtil.convertBean2Map(customer);
			mapParam.put("INPUT_DT", JCDFDateUtil.formatDate(new Date()));	//录入时间
			mapParam.put("INPUT_CD", loginUser.getUserId());				//录入人
			mapParam.put("DEL_FLAG", "1");									//删除标识0已删除，1未删除
			msg = (Message) cusService.insert(mapParam);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "新增出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 修改客户信息
	 * @param response
	 * @param request
	 * @param customer   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	@RequestMapping(params="method=updCus")
	public void updCus(HttpServletResponse response, HttpServletRequest request,CustomerVo customer)
	{
		LoginUser loginUser = (LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Message msg = null;
		try {
			Map<String, Object> mapParam = JCDFJsonUtil.convertBean2Map(customer);
			mapParam.put("MODIFY_DT", JCDFDateUtil.formatDate(new Date()));	//修改时间
			mapParam.put("MODIFY_CD", loginUser.getUserId());				//修改人编码
			msg = (Message) cusService.update(mapParam);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "修改出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 通过客户信息的ids删除对应的客户信息
	 * @param response
	 * @param request   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-17
	 */
	@RequestMapping(params="method=deleteByIds")
	public void deleteByIds(HttpServletResponse response, HttpServletRequest request)
	{
		Message msg = null;
		String ids = request.getParameter("ids");
		try {
			msg = (Message) cusService.deleteById(ids);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "修改出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 查询客户的编码及名称
	 * @param response
	 * @param request   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-9-5
	 */
	@RequestMapping(params="method=queryCus")
	public void queryCus(HttpServletResponse response, HttpServletRequest request)
	{
		List<Map<String,Object>> list = cusService.list();
		sendJsonDataToClient(list, response);
	}
	
}
