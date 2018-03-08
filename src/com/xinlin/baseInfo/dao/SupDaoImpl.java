/**  
 * @Title: SupDaoImpl.java
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
import com.xinlin.baseInfo.entity.Supplier;
import com.xinlin.baseInfo.mapper.SupMapper;

/**
 * ClassName: SupDaoImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Repository(value="supDao")
public class SupDaoImpl extends JcdfDaoSupport implements SupDao {
	
	@Resource
	private SupMapper supMapper = null;

	/**
	 * 通过供应商信息的ids删除对应的供应商信息
	 */
	@Override
	public void deleteById(String id) {
		supMapper.deleteById(id);

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
	 * 新增供应商信息
	 */
	@Override
	public void insert(Map<String, Object> t) {
		supMapper.insert(t);
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
	 * 修改供应商信息
	 */
	@Override
	public void update(Map<String, Object> t) {
		supMapper.update(t);

	}

	/**
	 * 分页查找供应商信息
	 * @throws Exception 
	 */
	@Override
	public Page pageQuery(Supplier supplier) throws Exception {
		return this.pageQuery(supMapper, "pageQuery", supplier);
	}

}
