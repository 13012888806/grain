package com.xinlin.app.service;

import com.xinlin.app.base.BaseService;
import com.xinlin.app.entity.pojo.FileLoad;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.entity.vo.PageQueryParams;

/**
 * 用户登录业务接口
 * 
 * @author Jiangshui
 * @date	2013-10-21
 */
public interface FileLoadService extends BaseService<FileLoad>{

	public Page pageQuery(Object queryParams, PageQueryParams pageQueryParams);


}