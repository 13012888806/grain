/**  
 * @Title: StoDaoImpl.java
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
import com.xinlin.baseInfo.entity.Storage;
import com.xinlin.baseInfo.mapper.StoMapper;

/**
 * ClassName: StoDaoImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Repository(value="stoDao")
public class StoDaoImpl extends JcdfDaoSupport implements StoDao {
	
	@Resource
	private StoMapper stoMapper = null;

	/**
	 * 通过入库信息的ids删除对应的入库信息
	 */
	@Override
	public void deleteById(String id) {
		stoMapper.deleteById(id);

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
	 * 新增入库信息
	 */
	@Override
	public void insert(Map<String, Object> t) {
		stoMapper.insert(t);
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
	 * 修改入库信息
	 */
	@Override
	public void update(Map<String, Object> t) {
		stoMapper.update(t);

	}

	/**
	 * 分页查找入库信息
	 * @throws Exception 
	 */
	@Override
	public Page pageQuery(Storage storage) throws Exception {
		return this.pageQuery(stoMapper, "pageQuery", storage);
	}

}
