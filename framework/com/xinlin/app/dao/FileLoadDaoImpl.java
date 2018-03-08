package com.xinlin.app.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xinlin.app.entity.pojo.FileLoad;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.entity.vo.PageQueryParams;
import com.xinlin.app.mapper.FileLoadMapper;


@Repository(value="fileloadDao")
public class FileLoadDaoImpl extends SqlSessionDaoSupport implements FileLoadDao{

	@Autowired
	private FileLoadMapper fileloadMapper;
	
	
	public Page pageQuery(Object queryParams, PageQueryParams pageQueryParams) {
		System.out.println("FileuploadBeanMapper..................");
		List list = this.getSqlSession().selectList("com.xinlin.app.mapper.FileLoadMapper.pageQuery", queryParams, new RowBounds(0,pageQueryParams.getPageSize()));
		return new Page(list);
	}
	
	public List<FileLoad> list() {
		return fileloadMapper.listAll();
	}

	
	public FileLoad get(String id) {
		return fileloadMapper.selectByPrimaryKey(id);
	}

	
	public void insert(FileLoad t) {
		fileloadMapper.insert(t);
	}

	
	public void update(FileLoad t) {
		fileloadMapper.updateByPrimaryKeySelective(t);
	}

	
	public void deleteById(String id) {
		fileloadMapper.deleteByPrimaryKey(id);
	}

}
