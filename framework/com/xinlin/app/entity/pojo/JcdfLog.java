package com.xinlin.app.entity.pojo;

import java.io.Serializable;
import java.util.Date;

import com.xinlin.app.entity.vo.PageQueryParams;


/**
 * 系统日志有关实体
 * 
 * 
 * @author jxq
 * @date 2013-10-23
 *
 */
public class JcdfLog extends PageQueryParams  implements Serializable{

	private static final long serialVersionUID = 121232132133L;
	
    private String logId;

    private String logIp;

    private String logMac;

    private String userId;

    private String userName;

    private Date operatorTime;

    private String operatoContent;

    private String moduleName;

    private Integer logPara1;

    private Integer logPara2;

    private String logPara3;

    private String logPara4;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOperatoContent() {
        return operatoContent;
    }

    public void setOperatoContent(String operatoContent) {
        this.operatoContent = operatoContent;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getLogPara1() {
        return logPara1;
    }

    public void setLogPara1(Integer logPara1) {
        this.logPara1 = logPara1;
    }

    public Integer getLogPara2() {
        return logPara2;
    }

    public void setLogPara2(Integer logPara2) {
        this.logPara2 = logPara2;
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
}