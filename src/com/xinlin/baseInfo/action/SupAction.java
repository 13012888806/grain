/**  
 * @Title: SupAction.java
 * @Package com.xinlin.baseInfo.action
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.action;

import java.util.Date;
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
import com.xinlin.baseInfo.entity.Supplier;
import com.xinlin.baseInfo.entity.SupplierVo;
import com.xinlin.baseInfo.service.SupService;

/**
 * ClassName: SupAction 
 * @Description: 供应商信息维护的控制层
 * @author Chenxl
 * @date 2015-6-3
 */
@Controller
@RequestMapping(value="/base/sup.do")
public class SupAction extends BaseController {
	
	/**
	 * 供应商信息业务类
	 */
	@Resource(name="supService")
	private SupService supService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(HttpServletResponse response){
		return "base/sup_list";
	}
	
	/**
	 * @Description: 分页查找供应商信息
	 * @param @param response
	 * @param @param request
	 * @param @param map   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-3
	 */
	@RequestMapping(params="method=pageQuery")
	public void pageQuery(HttpServletResponse response, HttpServletRequest request, Supplier supplier) {
		sendJsonDataToClient(supService.pageQuery(supplier), response);
	}
	
	/**
	 * @Description: 供应商信息弹出层-保存输入的合法数据
	 * @param response
	 * @param request
	 * @param supplier   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=insertSup")
	public void insertSup(HttpServletResponse response, HttpServletRequest request,SupplierVo supplier)
	{
		LoginUser loginUser = (LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Message msg = null;
		try {
			Map<String, Object> mapParam = JCDFJsonUtil.convertBean2Map(supplier);
			mapParam.put("INPUT_DT", JCDFDateUtil.formatDate(new Date()));	//录入时间
			mapParam.put("INPUT_CD", loginUser.getUserId());				//录入人
			mapParam.put("DEL_FLAG", "1");									//删除标识0已删除，1未删除
			msg = (Message) supService.insert(mapParam);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "新增出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 修改供应商信息
	 * @param response
	 * @param request
	 * @param supplier   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	@RequestMapping(params="method=updSup")
	public void updSup(HttpServletResponse response, HttpServletRequest request,SupplierVo supplier)
	{
		LoginUser loginUser = (LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Message msg = null;
		try {
			Map<String, Object> mapParam = JCDFJsonUtil.convertBean2Map(supplier);
			mapParam.put("MODIFY_DT", JCDFDateUtil.formatDate(new Date()));	//修改时间
			mapParam.put("MODIFY_CD", loginUser.getUserId());				//修改人编码
			msg = (Message) supService.update(mapParam);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "修改出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 通过供应商信息的ids删除对应的供应商信息
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
			msg = (Message) supService.deleteById(ids);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "修改出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
}
