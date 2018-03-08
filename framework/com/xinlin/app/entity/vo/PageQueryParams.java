package com.xinlin.app.entity.vo;

import com.xinlin.app.util.StaticVar;

/**
 * 分页查询的通用参数，主要用于规范所传参数中必需带上参数
 * 
 * @author Jiangshui
 * @date 2013-08-08
 */
public class PageQueryParams {

	/** 分页查询的页号(前台视图页面传入) */
	protected int pageNo;
	/** 分页查询数据时，每页显示的记录数(前台视图页面传入) */
	protected int pageSize;
	/** 总记录数（由系统查询数据库后设置，无需过问） */
	protected long total;
	/** 本次查询记录的开始位置（由系统查询数据库计数后设置，无需过问） */
	protected long start;
	
	/**
	 * 获取实例
	 * 
	 * @param pageNo
	 * @param PageSize
	 * @return
	 */
	public static PageQueryParams getInstance(int pageNo, int pageSize) {
		PageQueryParams p = new PageQueryParams();
		p.setPageNo(pageNo);
		p.setPageSize(pageSize);
		return p;
	}
	
	public int getPageNo() {
		//如果页号小于等于0，则修正为1
		if (pageNo <= 0) pageNo = 1;
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}
	
	/**
	 * 设置页号
	 * 提供该方法的目的是便于使用spring的自动参数注入功能
	 * 
	 * @param page
	 */
	public void setPage(int page) {
		this.pageNo = page <=0 ? StaticVar.DEFAULT_PAGE_NO : page;
		
	}

	/**
	 * 设置每页显示的记录数
	 * 提供该方法的目的是便于使用spring的自动参数注入功能
	 * 
	 * @param rows
	 */
	public void setRows(int rows) {
		this.pageSize = rows <=0 ? StaticVar.DEFAULT_PAGE_SIZE : rows;
	}
}