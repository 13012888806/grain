package com.xinlin.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.xinlin.app.entity.pojo.SysUser;
import com.xinlin.app.entity.vo.PageQueryParams;
import com.xinlin.app.mapper.SysUserMapper;


/**
 * dao层测试样本
 * 
 * @author jxq
 * @date 2013-10-16
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:jcdf-springmvc-config.xml","classpath*:jcdf-context-test.xml"})
//@ContextConfiguration({"/app-config.xml","/jdbc-context.xml"})
public class ResourceDaoTest{


	@Autowired
	private SysUserMapper sysUserMapper;
	
	
	@Test
	public void list() {
		List<SysUser> roles =  sysUserMapper.list();
		Assert.notEmpty(roles);
	}

	
	@Test
	public void get() {
		String id = "abcd";
		SysUser u = sysUserMapper.get(id);
		Assert.notNull(u);
	}

	
	@Test
	public void insert() {
		SysUser u = new SysUser();
		u.setUserId("abcd");
		u.setUserName("admin");
		u.setUserPass("1232");
		sysUserMapper.insert(u);
	}

	
	@Test
	public void update() {
		
		String id = "abcd";
		SysUser u = sysUserMapper.get(id);
		Assert.notNull(u);
		u.setUserName("update");
		sysUserMapper.update(u);
		
	}

	
	public void deleteById() {
		String id;
		// TODO Auto-generated method stub
		
	}

	

}
