/**  
 * @Title: WhAction.java
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
import com.xinlin.baseInfo.entity.WareHouse;
import com.xinlin.baseInfo.entity.WareHouseVo;
import com.xinlin.baseInfo.service.WhService;

/**
 * ClassName: WhAction 
 * @Description: 仓库信息维护的控制层
 * @author Chenxl
 * @date 2015-6-3
 */
@Controller
@RequestMapping(value="/base/wh.do")
public class WhAction extends BaseController {
	
	/**
	 * 仓库信息管理业务类
	 */
	@Resource(name="whService")
	private WhService whService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(HttpServletResponse response){
		return "base/wh_list";
	}
	
	/**
	 * @Description: 分页查找仓库信息
	 * @param @param response
	 * @param @param request
	 * @param @param map   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-3
	 */
	@RequestMapping(params="method=pageQuery")
	public void pageQuery(HttpServletResponse response, HttpServletRequest request, WareHouse wareHouse) {
		sendJsonDataToClient(whService.pageQuery(wareHouse), response);
	}
	
	/**
	 * @Description: 仓库信息弹出层-保存输入的合法数据
	 * @param response
	 * @param request
	 * @param wareHouse   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=insertWh")
	public void insertWh(HttpServletResponse response, HttpServletRequest request,WareHouseVo wareHouse)
	{
		LoginUser loginUser = (LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Message msg = null;
		try {
			Map<String, Object> mapParam = JCDFJsonUtil.convertBean2Map(wareHouse);
			mapParam.put("INPUT_DT", JCDFDateUtil.formatDate(new Date()));	//录入时间
			mapParam.put("INPUT_CD", loginUser.getUserId());				//录入人
			mapParam.put("DEL_FLAG", "1");									//删除标识0已删除，1未删除
			msg = (Message) whService.insert(mapParam);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "新增出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 修改仓库信息
	 * @param response
	 * @param request
	 * @param wareHouse   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	@RequestMapping(params="method=updWh")
	public void updWh(HttpServletResponse response, HttpServletRequest request,WareHouseVo wareHouse)
	{
		LoginUser loginUser = (LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Message msg = null;
		try {
			Map<String, Object> mapParam = JCDFJsonUtil.convertBean2Map(wareHouse);
			mapParam.put("MODIFY_DT", JCDFDateUtil.formatDate(new Date()));	//修改时间
			mapParam.put("MODIFY_CD", loginUser.getUserId());				//修改人编码
			msg = (Message) whService.update(mapParam);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "修改出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 通过仓库信息的ids删除对应的仓库信息
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
			msg = (Message) whService.deleteById(ids);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "修改出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 查询仓库信息的键值对在下拉框显示
	 * @param response
	 * @param request   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-9-5
	 */
	@RequestMapping(params="method=queryWh")
	public void queryWh(HttpServletResponse response, HttpServletRequest request)
	{
		List<Map<String,Object>> list = whService.list();
		sendJsonDataToClient(list, response);
	}
}
