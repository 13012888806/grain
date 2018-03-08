/**  
 * @Title: CusDaoImpl.java
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
import com.xinlin.baseInfo.entity.Customer;
import com.xinlin.baseInfo.mapper.CusMapper;

/**
 * ClassName: CusDaoImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Repository(value="cusDao")
public class CusDaoImpl extends JcdfDaoSupport implements CusDao {
	
	@Resource
	private CusMapper cusMapper = null;

	/**
	 * 通过仓库信息的ids删除对应的仓库信息
	 */
	@Override
	public void deleteById(String id) {
		cusMapper.deleteById(id);

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
	 * 新增仓库信息
	 */
	@Override
	public void insert(Map<String, Object> t) {
		cusMapper.insert(t);
	}

	/**
	 * 查询客户键值对,在下拉框使用
	 */
	@Override
	public List<Map<String, Object>> list() {
		return cusMapper.queryCus();
	}

	/**
	 * 修改仓库信息
	 */
	@Override
	public void update(Map<String, Object> t) {
		cusMapper.update(t);

	}

	/**
	 * 分页查找仓库信息
	 * @throws Exception 
	 */
	@Override
	public Page pageQuery(Customer customer) throws Exception {
		return this.pageQuery(cusMapper, "pageQuery", customer);
	}

}
