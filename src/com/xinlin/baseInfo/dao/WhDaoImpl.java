/**  
 * @Title: WhDaoImpl.java
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
import com.xinlin.baseInfo.entity.WareHouse;
import com.xinlin.baseInfo.mapper.WhMapper;

/**
 * ClassName: WhDaoImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Repository(value="whDao")
public class WhDaoImpl extends JcdfDaoSupport implements WhDao {
	
	@Resource
	private WhMapper whMapper = null;

	/**
	 * 通过仓库信息的ids删除对应的仓库信息
	 */
	@Override
	public void deleteById(String id) {
		whMapper.deleteById(id);

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
		whMapper.insert(t);
	}

	/**
	 * 查询仓库信息的键值对在下拉框显示
	 */
	@Override
	public List<Map<String, Object>> list() {
		return whMapper.queryWh();
	}

	/**
	 * 修改仓库信息
	 */
	@Override
	public void update(Map<String, Object> t) {
		whMapper.update(t);

	}

	/**
	 * 分页查找仓库信息
	 * @throws Exception 
	 */
	@Override
	public Page pageQuery(WareHouse wareHouse) throws Exception {
		return this.pageQuery(whMapper, "pageQuery", wareHouse);
	}

}
