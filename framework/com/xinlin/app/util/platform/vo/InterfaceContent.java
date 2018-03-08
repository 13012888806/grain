package com.xinlin.app.util.platform.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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

@XmlRootElement(name="InterFaceContent")
public class InterfaceContent {

    @XmlElement(name="InterfaceConfigVO")
	  private List<InterfaceConfigVO> interfaceContent;
    
    public InterfaceContent(){
    	interfaceContent = new ArrayList<InterfaceConfigVO>();
    }

    public void add(InterfaceConfigVO interfaseConfigVO){
    	interfaceContent.add(interfaseConfigVO);
    }

    public Iterator<InterfaceConfigVO> iterator(){
        return interfaceContent.iterator();
    }
}