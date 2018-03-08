/**  
 * @Title: SlDaoImpl.java
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
import com.xinlin.baseInfo.entity.SupLevel;
import com.xinlin.baseInfo.mapper.SlMapper;

/**
 * ClassName: SlDaoImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Repository(value="slDao")
public class SlDaoImpl extends JcdfDaoSupport implements SlDao {
	
	@Resource
	private SlMapper slMapper = null;

	/**
	 * 通过供应商级别的ids删除对应的供应商级别
	 */
	@Override
	public void deleteById(String id) {
		slMapper.deleteById(id);

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
	 * 新增供应商级别
	 */
	@Override
	public void insert(Map<String, Object> t) {
		slMapper.insert(t);
	}

	/**
	 * 查询供应商级别,下拉框用
	 */
	@Override
	public List<Map<String, Object>> list() {
		return slMapper.querySl();
	}

	/**
	 * 修改供应商级别
	 */
	@Override
	public void update(Map<String, Object> t) {
		slMapper.update(t);

	}

	/**
	 * 分页查找供应商级别
	 * @throws Exception 
	 */
	@Override
	public Page pageQuery(SupLevel supLevel) throws Exception {
		return this.pageQuery(slMapper, "pageQuery", supLevel);
	}

}
