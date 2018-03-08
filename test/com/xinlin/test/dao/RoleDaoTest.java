package com.xinlin.test.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.xinlin.app.entity.pojo.Role;
import com.xinlin.app.entity.vo.PageQueryParams;
import com.xinlin.app.mapper.RoleMapper;

/**
 * dao层测试样本
 * 
 * @author jxq
 * @date 2013-10-15
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:jcdf-springmvc-config.xml","classpath*:jcdf-context-test.xml"})
//@ContextConfiguration({"/app-config.xml","/jdbc-context.xml"})
public class RoleDaoTest extends SqlSessionDaoSupport  {
	
	@Autowired
	private RoleMapper roleMapper = null;

	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	//删除
	@Test
	public void deleteById() {
		String id = "abcdef";
		roleMapper.deleteById(id);
	}

	//新增
	@Test
	public void insert() {
		Role u = new Role();
		u.setRoleId("dfdfd");
		u.setRoleName("test dao");
		u.setRoleMark("dfdf");
		roleMapper.insert(u);
	}

	//列表
	@Test
	public void list() {
		List<Role> roles =  roleMapper.list();
		Assert.notEmpty(roles);
	}

	//修改
	@Test
	public void update() {
		String id = "abcdef";
		Role u = roleMapper.get(id);
		u.setRoleName("update");
		roleMapper.update(u);
	}

	//获取对象
	@Test
	public void get() {
		String id = "abcdef";
		Role u = roleMapper.get(id);
		Assert.notNull(u);
	}
	
	@Test
	public void pageQuery() throws Exception {
		// TODO Auto-generated method stub
		//return null;
			// TODO Auto-generated method stub
		//Object queryParams, PageQueryParams pageQueryParams
		Object queryParams = new ArrayList();
		PageQueryParams pageQueryParams = PageQueryParams.getInstance(1, 10);
		List list = sqlSessionFactory.getObject().openSession().selectList("com.xinlin.app.mapper.RoleMapper.pageQuery", queryParams, new RowBounds(pageQueryParams.getPageNo(),pageQueryParams.getPageSize()));
		//List list = sqlSessionFactory.
		//.getSqlSession()
		Assert.notNull(list);
		
	}

}
