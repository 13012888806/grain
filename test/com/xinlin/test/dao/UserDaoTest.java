package com.xinlin.test.dao;


import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.xinlin.app.dao.UserDao;
import com.xinlin.app.entity.pojo.User;
import com.xinlin.app.mapper.UserMapper;

/**
 * dao层测试样本
 * 
 * @author jxq
 * @date 2013-10-11
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:jcdf-springmvc-config.xml","classpath*:jcdf-context-test.xml"})
//@ContextConfiguration({"/app-config.xml","/jdbc-context.xml"})
public class UserDaoTest extends SqlSessionDaoSupport  {
	
	@Autowired
	private UserMapper userMapper = null;

	//删除
	@Test
	public void deleteById() {
		String id = "abcdef";
		userMapper.deleteById(id);
	}

	//新增
	@Test
	public void insert() {
		User u = new User();
		u.setId("abcdef");
		u.setUserName("test dao");
		u.setPassword("123");
		userMapper.addUser(u);
	}

	//列表
	@Test
	public void list() {
		List<User> users =  userMapper.getAllUser();
		Assert.notEmpty(users);
	}

	//修改
	@Test
	public void update() {
		String id = "abcdef";
		User u = userMapper.getUserById(id);
		u.setUserName("update");
		userMapper.update(u);
	}

	//获取对象
	@Test
	public void get() {
		String id = "abcdef";
		User u = userMapper.getUserById(id);
		Assert.notNull(u);
	}

}
