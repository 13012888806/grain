package com.xinlin.app.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 分页信息实体
 *
 * @author JS
 * @since  2013-04-09
 */
@SuppressWarnings("serial")
public class Page implements Serializable {

	/**总记录数*/
	private long total;
	/**当前页记录,类型为List*/
	private Object rows;
	/**列表底部显示的数据，例如统计数据，类型为List*/
	private Object footer;

	/**
	 * 构造方法，只构造空页.
	 */
	public Page() {
		this(0, new ArrayList());
	}

	/**
	 * 不分页列表数据对象默认构造方法
	 * 
	 * @param rows	列表数据
	 */
	public Page(Object rows) {
		this.rows = rows;
		if(null != rows && rows instanceof List) {
			this.total = ((List)rows).size();
		} else if (null != rows){
			this.total = 1;
		}
	}
	
	/**
	 * 分页列表数据对象默认构造方法（仅含含列表）
	 * 
	 * @param rows	列表数据
	 */
	public Page(long total, Object rows) {
		this.total = total;
		this.rows = rows;
	}
	
	/**
	 * 默认分页构造方法(含列表和表底)
	 *
	 * @param total	 总记录数
	 * @param rows 页数据
	 * @param footer	页脚数据
	 */
	public Page(long total, Object rows, Object footer) {
		this.total = total;
		this.rows = rows;
		this.footer = footer;
	}

	/**
	 * 取当前页中的记录.
	 */
	public Object getRows() {
		return rows;
	}
	
	/**
	 * 设置当前页中的记录.
	 */
	public void setRows(Object rows){
		this.rows = rows;
	}
	
	/**
	 * 获取总记录数
	 * 
	 * @return	
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param total
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	
	public Object getFooter() {
		return footer;
	}

	public void setFooter(Object footer) {
		this.footer = footer;
	}
	
	/**
	 * 获取任一页第一条数据在数据集的位置（不考虑总记录数）
	 *
	 * @param pageNo   从1开始的页号
	 * @param pageSize 每页记录条数
	 * @return 该页第一条数据
	 */
	public static long getStartOfPage(int pageNo, int pageSize) {
		long start = (pageNo - 1) * pageSize;
		return start < 0 ? 0 : start;
	}
	
	/**
	 * 获取任一页第一条数据在数据集的位置(考虑总记录数并做调整)
	 *
	 * @param total   总记录数
	 * @param pageNo   从1开始的页号
	 * @param pageSize 每页记录条数
	 * @return 该页第一条数据
	 */
	public static long getStartOfPage(long total, int pageNo, int pageSize) {
		long start = (pageNo - 1) * pageSize;
		//如果超过了最后一行，则自动退回到最后一页的第一行
		if (start >= total) {
			start = Page.getStartOfPage(pageNo-1, pageSize);
		}
		return start < 0 ? 0 : start;
	}
}