package com.xinlin.app.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xinlin.app.entity.pojo.JcdfLog;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.mapper.JcdfLogMapper;
import com.xinlin.app.util.JCDFWebUtil;
import com.xinlin.app.util.platform.filter.JcdfDaoSupport;


@Repository(value="jcdfLogDao")
public class JcdfLogDaoImpl extends JcdfDaoSupport implements JcdfLogDao{

	@Autowired
	private JcdfLogMapper jcdfLogMapper;
	
	
	public Page pageQuery(JcdfLog log) throws Exception {
		return pageQuery(jcdfLogMapper, "pageQuery", log);
	}
	
	public List<JcdfLog> list() {
		return jcdfLogMapper.listAll();
	}

	
	public JcdfLog get(String id) {
		return jcdfLogMapper.selectByPrimaryKey(id);
	}

	
	public void deleteById(String id) {
		jcdfLogMapper.deleteByPrimaryKey(id);
	}

	public void insert(JcdfLog t) {
		t.setLogId(JCDFWebUtil.getGlobalKey());
		t.setOperatorTime(new java.util.Date());
		jcdfLogMapper.insert(t);
	}

	public void update(JcdfLog t) {
		jcdfLogMapper.updateByPrimaryKey(t);
	}

}
