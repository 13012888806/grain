package com.xinlin.baseInfo.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.util.platform.filter.JcdfDaoSupport;
import com.xinlin.baseInfo.entity.Cg;
import com.xinlin.baseInfo.mapper.CgMapper;

/**
 * ClassName: CgDaoImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Repository(value="cgDao")
public class CgDaoImpl extends JcdfDaoSupport implements CgDao {
	
	@Resource
	private CgMapper cgMapper = null;

	/**
	 * 通过种类管理的ids删除对应的种类管理
	 */
	@Override
	public void deleteById(String id) {
		cgMapper.deleteById(id);

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
	 * 新增种类管理
	 */
	@Override
	public void insert(Map<String, Object> t) {
		cgMapper.insert(t);
	}

	/**
	 * 查询种类的键值对集合,在下拉框中使用
	 */
	@Override
	public List<Map<String, Object>> list() {
		return cgMapper.queryCg();
	}

	/**
	 * 修改种类管理
	 */
	@Override
	public void update(Map<String, Object> t) {
		cgMapper.update(t);

	}

	/**
	 * 分页查找种类管理
	 * @throws Exception 
	 */
	@Override
	public Page pageQuery(Cg cg) throws Exception {
		return this.pageQuery(cgMapper, "pageQuery", cg);
	}

}
