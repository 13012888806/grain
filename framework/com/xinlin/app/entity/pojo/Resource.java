package com.xinlin.app.entity.pojo;

import java.io.Serializable;

/**
 * 资源信息实体
 * 
 * @author Jiangshui
 * @date 2013-10-16
 */
public class Resource implements Serializable{

	private static final long serialVersionUID = 12325668976751L;
	/** 功能菜单编码 */
	private String menuCode;
	/** 资源，多半为功能发起请求的url后缀 */
	private String res;
	/** 资源备注 */
	private String resMark;

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getResMark() {
		return resMark;
	}

	public void setResMark(String resMark) {
		this.resMark = resMark;
	}
}