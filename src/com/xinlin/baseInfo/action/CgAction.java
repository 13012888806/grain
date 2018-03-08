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
import com.xinlin.baseInfo.entity.Cg;
import com.xinlin.baseInfo.entity.CgVo;
import com.xinlin.baseInfo.service.CgService;

/**
 * ClassName: CgAction 
 * @Description: 种类管理维护的控制层
 * @author Chenxl
 * @date 2015-6-3
 */
@Controller
@RequestMapping(value="/base/cg.do")
public class CgAction extends BaseController {
	
	/**
	 * 种类管理业务类
	 */
	@Resource(name="cgService")
	private CgService cgService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(HttpServletResponse response){
		return "base/cg_list";
	}
	
	/**
	 * @Description: 分页查找种类管理
	 * @param @param response
	 * @param @param request
	 * @param @param map   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-3
	 */
	@RequestMapping(params="method=pageQuery")
	public void pageQuery(HttpServletResponse response, HttpServletRequest request, Cg cg) {
		sendJsonDataToClient(cgService.pageQuery(cg), response);
	}
	
	/**
	 * @Description: 种类管理弹出层-保存输入的合法数据
	 * @param response
	 * @param request
	 * @param variety   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-8
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=insertCg")
	public void insertCg(HttpServletResponse response, HttpServletRequest request,CgVo cg)
	{
		LoginUser loginUser = (LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Message msg = null;
		try {
			Map<String, Object> mapParam = JCDFJsonUtil.convertBean2Map(cg);
			mapParam.put("DEL_FLAG", "1");									//删除标识0已删除，1未删除
			msg = (Message) cgService.insert(mapParam);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "新增出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 修改种类管理
	 * @param response
	 * @param request
	 * @param cg   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-10
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=updCg")
	public void updCg(HttpServletResponse response, HttpServletRequest request,CgVo cg)
	{
		LoginUser loginUser = (LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Message msg = null;
		try {
			Map<String, Object> mapParam = JCDFJsonUtil.convertBean2Map(cg);
			mapParam.put("MODIFY_DT", JCDFDateUtil.formatDate(new Date()));	//修改时间
			mapParam.put("MODIFY_CD", loginUser.getUserId());				//修改人编码
			msg = (Message) cgService.update(mapParam);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "修改出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 通过种类管理的ids删除对应的种类管理
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
			msg = (Message) cgService.deleteById(ids);
			sendJsonDataToClient(msg, response);
		} catch (Exception e) {
			msg = new Message(false, "修改出现异常,请联系管理员！");
			sendJsonDataToClient(msg, response);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 查询种类的键值对集合,在下拉框中使用
	 * @param response
	 * @param request   
	 * @return void  
	 * @throws
	 * @author Chenxl
	 * @date 2015-6-28
	 */
	@RequestMapping(params="method=queryCg")
	public void queryCg(HttpServletResponse response, HttpServletRequest request)
	{
		List<Map<String,Object>> list = cgService.list();
		sendJsonDataToClient(list, response);
	}
	
}
