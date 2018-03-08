package com.xinlin.app.service;

import java.util.List;

import com.xinlin.app.base.BaseService;
import com.xinlin.app.entity.pojo.JcdfLog;
import com.xinlin.app.entity.vo.Page;

public interface JcdfLogService extends BaseService<JcdfLog> {
	public Page pageQuery(JcdfLog log);
	 
}
