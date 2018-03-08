/**  
 * @Title: LibDaoImpl.java
 * @Package com.xinlin.baseInfo.dao
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.util.platform.filter.JcdfDaoSupport;
import com.xinlin.baseInfo.entity.Library;
import com.xinlin.baseInfo.mapper.LibMapper;

/**
 * ClassName: LibDaoImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Repository(value="libDao")
public class LibDaoImpl extends JcdfDaoSupport implements LibDao {
	
	@Resource
	private LibMapper libMapper = null;

	/**
	 * 通过出库信息的ids删除对应的出库信息
	 */
	@Override
	public void deleteById(String id) {
		libMapper.deleteById(id);

	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseDao#get(java.lang.String)
	 */
	@Override
	public Map<String, Object> get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 新增出库信息
	 */
	@Override
	public void insert(Map<String, Object> t) {
		libMapper.insert(t);
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseDao#list()
	 */
	@Override
	public List<Map<String, Object>> list() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 修改出库信息
	 */
	@Override
	public void update(Map<String, Object> t) {
		libMapper.update(t);

	}

	/**
	 * 分页查找出库信息
	 * @throws Exception 
	 */
	@Override
	public Page pageQuery(Library library) throws Exception {
		return this.pageQuery(libMapper, "pageQuery", library);
	}

}
