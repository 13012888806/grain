package com.xinlin.app.entity.pojo;

import java.io.Serializable;
import java.util.Date;

import com.xinlin.app.entity.vo.PageQueryParams;

/**
 * 系统菜单信息实体
 * 
 * @author Jiangshui
 * @date 2013-10-16
 */
public class Menu extends PageQueryParams implements Serializable{

	private static final long serialVersionUID = 121232132133L;
	/** 功能菜单编码 */
	private String menuCode;
	/** 父功能菜单编码 */
	private String parentMenuCode;
	/** 功能菜单名称 */
	private String menuName;
	/** 功能菜单类型,1:菜单，2：按钮 */
	private Integer menuType;
	/** 功能菜单备注 */
	private String menuMark;
	/**菜单新增时间*/
	private Date createTime;

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getParentMenuCode() {
		return parentMenuCode;
	}

	public void setParentMenuCode(String parentMenuCode) {
		this.parentMenuCode = parentMenuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuMark() {
		return menuMark;
	}

	public void setMenuMark(String menuMark) {
		this.menuMark = menuMark;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}