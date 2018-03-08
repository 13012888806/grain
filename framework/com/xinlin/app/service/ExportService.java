package com.xinlin.app.service;

import com.xinlin.app.entity.vo.Message;

/**
 * 角色管理业务类实现
 * 
 * @author Administrator
 *
 */
public interface ExportService {

	/**
	 * 数据导出
	 * 
	 * @param mapperId	配置文件中对应配置的id
	 * @param params	查询参数
	 * 
	 * @return	生成的导出文件名
	 */
	public abstract Message exportData(String mapperId, Object params);

}