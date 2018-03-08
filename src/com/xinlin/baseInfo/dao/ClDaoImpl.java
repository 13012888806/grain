/**  
 * @Title: ClDaoImpl.java
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
import com.xinlin.baseInfo.entity.CusLevel;
import com.xinlin.baseInfo.mapper.ClMapper;

/**
 * ClassName: ClDaoImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Repository(value="clDao")
public class ClDaoImpl extends JcdfDaoSupport implements ClDao {
	
	@Resource
	private ClMapper clMapper = null;

	/**
	 * 通过客户级别的ids删除对应的客户级别
	 */
	@Override
	public void deleteById(String id) {
		clMapper.deleteById(id);

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
	 * 新增客户级别
	 */
	@Override
	public void insert(Map<String, Object> t) {
		clMapper.insert(t);
	}

	/**
	 * 查询客级别，下拉框用
	 */
	@Override
	public List<Map<String, Object>> list() {
		
		return clMapper.queryCl();
	}

	/**
	 * 修改客户级别
	 */
	@Override
	public void update(Map<String, Object> t) {
		clMapper.update(t);

	}

	/**
	 * 分页查找客户级别
	 * @throws Exception 
	 */
	@Override
	public Page pageQuery(CusLevel cusLevel) throws Exception {
		return this.pageQuery(clMapper, "pageQuery", cusLevel);
	}

}
