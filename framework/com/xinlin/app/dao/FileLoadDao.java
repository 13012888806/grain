package com.xinlin.app.dao;

import com.xinlin.app.base.BaseDao;
import com.xinlin.app.entity.pojo.FileLoad;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.entity.vo.PageQueryParams;

public interface FileLoadDao  extends BaseDao<FileLoad>{
	Page pageQuery(Object queryParams, PageQueryParams pageQueryParams);
}
