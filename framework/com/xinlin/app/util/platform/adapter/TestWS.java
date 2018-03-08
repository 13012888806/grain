package com.xinlin.app.util.platform.adapter;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

import com.xinlin.app.util.StaticVar;
import com.xinlin.app.util.platform.adapter.common.WSService;
import com.xinlin.app.util.platform.adapter.common.WSPoolExecutor;
import com.xinlin.app.util.platform.adapter.common.local.IWSDao;

/**
 * 对外开放WS接口实现
 * 
 * @author jxq
 * @date 2013-10-09
 *
 */
@WebService
//避免jdk低版本环境报错, jdk.1.6.0_22版本以下使用 
//@SOAPBinding(style = SOAPBinding.Style.RPC) 
public class TestWS {
	//private static final long serialVersionUID = -1L;
	
	@WebMethod(operationName="getJson4Test")
	@WebResult(name="resJson")
	public String getResJson(String orgCodeStr,String type){
		if(orgCodeStr == null || (orgCodeStr = orgCodeStr.trim()).length() == 0){
			return StaticVar.WS_EMPTY_ERROR;
		}
		String []orgCode = orgCodeStr.split(",");
		
		IWSDao  iWSDao =  WSService.getWSDaoByService(this.getClass().getName());
		if(iWSDao == null){
			return StaticVar.WS_SERVICE_INVALID;
		}
		if(type==null || (type = type.trim()).length()==0){
			type="json";
		}
		if(orgCode.length > 1){
			WSPoolExecutor pool = new WSPoolExecutor(iWSDao, orgCode, type);
			return pool.getStrByInputParam();
		}
		
		//PoiPoolExecutor pool = new PoiPoolExecutor(orgCode);
		//return pool.getJsonStrByInputParam();
		//IWSDao  iWSDao = WSFactory.getiWSDao();
		return iWSDao.getJsonStrByInputParam(orgCode,type);
	}
	
	
}
