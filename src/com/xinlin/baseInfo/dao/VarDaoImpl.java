/**  
 * @Title: VarDaoImpl.java
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
import com.xinlin.baseInfo.entity.Variety;
import com.xinlin.baseInfo.mapper.VarMapper;

/**
 * ClassName: VarDaoImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Repository(value="varDao")
public class VarDaoImpl extends JcdfDaoSupport implements VarDao {
	
	@Resource
	private VarMapper varMapper = null;

	/**
	 * 通过品种管理的ids删除对应的品种管理
	 */
	@Override
	public void deleteById(String id) {
		varMapper.deleteById(id);

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
	 * 新增品种管理
	 */
	@Override
	public void insert(Map<String, Object> t) {
		varMapper.insert(t);
	}

	/**
	 * 查询品种的键值对集合,在下拉框中使用
	 */
	@Override
	public List<Map<String, Object>> list() {
		return varMapper.queryVar();
	}

	/**
	 * 修改品种管理
	 */
	@Override
	public void update(Map<String, Object> t) {
		varMapper.update(t);

	}

	/**
	 * 分页查找品种管理
	 * @throws Exception 
	 */
	@Override
	public Page pageQuery(Variety variety) throws Exception {
		return this.pageQuery(varMapper, "pageQuery", variety);
	}

}
