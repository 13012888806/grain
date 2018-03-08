package com.xinlin.app.util.platform.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 系统配置文件用(interFaceConfig.xml)
 * (定义系统有关的外部接口，第三方业务接口的wsdl描述集)
 * 
 * 用于构建和读取interFaceConfig.xml
 * 
 * 
 * @author jxq
 * @date 2013-10-23
 *
 */
@XmlType(propOrder={"serviceName","requestUrl"})
@XmlRootElement(name="InterfaceConfigVO")
public class InterfaceConfigVO {
	 private  String serviceName;
		
	 private  String requestUrl;

    @XmlElement
	public final String getServiceName() {
		return serviceName;
	}

	public final void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}

	@XmlElement
	public final String getRequestUrl() {
		return requestUrl;
	}

	public final void setRequestUrl(final String requestUrl) {
		this.requestUrl = requestUrl;
	}
    
    
}