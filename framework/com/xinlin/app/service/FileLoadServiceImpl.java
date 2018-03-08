package com.xinlin.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinlin.app.dao.FileLoadDao;
import com.xinlin.app.entity.pojo.FileLoad;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.entity.vo.PageQueryParams;

/**
 * 用户管理业务类实现
 * 
 * @author JiangShui
 * @date	2013-10-21
 *
 */
@Service(value="fileLoadService")
@Transactional
public class FileLoadServiceImpl implements FileLoadService {

	/**用户数据库操作接口*/
	@Resource(name="fileloadDao")
	private FileLoadDao fileloadDao;
	
	
	public List<FileLoad> list() {
		return fileloadDao.list();
	}

	
	public FileLoad get(String id) {
		return fileloadDao.get(id);
	}

	
	public Object insert(FileLoad t) {
		fileloadDao.insert(t);
		return null;
	}

	
	public Object update(FileLoad t) {
		fileloadDao.update(t);
		return null;
	}

	
	public Object deleteById(String id) {
		fileloadDao.deleteById(id);
		return null;
	}
	
	public Page pageQuery(Object queryParams, PageQueryParams pageQueryParams) {
		return fileloadDao.pageQuery(queryParams, pageQueryParams);
	}

}
