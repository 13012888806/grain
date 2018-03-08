package com.xinlin.app.util.platform.springlog;

import java.util.Date;

import org.springframework.context.ApplicationEvent;



public class JcdfLogEvent extends ApplicationEvent{

    private String loginName;
    
    private String logId;

    private String logIp;

    private String logMac;

    private String userId;

    private String moduleName;
    
    private String operatoContent;
    
    
    private String logPara3;

    private String logPara4;
    
    private Throwable ex;

    public JcdfLogEvent(Object source){
         super(source);
    }

    public JcdfLogEvent(Object source, String loginName){
         super(source);
         this.loginName=loginName;
         System.out.println(super.getTimestamp());
         System.out.println(loginName);                
    }


    public String getLoginName(){
          return loginName;
    }


    public void setLoginName(String loginName){
          this.loginName=loginName;
    }

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public String getLogMac() {
		return logMac;
	}

	public void setLogMac(String logMac) {
		this.logMac = logMac;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperatoContent() {
		return operatoContent;
	}

	public void setOperatoContent(String operatoContent) {
		this.operatoContent = operatoContent;
	}

	public Throwable getEx() {
		return ex;
	}

	public void setEx(Throwable ex) {
		this.ex = ex;
	}

	public String getLogPara3() {
		return logPara3;
	}

	public void setLogPara3(String logPara3) {
		this.logPara3 = logPara3;
	}

	public String getLogPara4() {
		return logPara4;
	}

	public void setLogPara4(String logPara4) {
		this.logPara4 = logPara4;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

    
}
