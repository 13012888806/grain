package com.xinlin.test.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.xinlin.app.dao.UserDao;
import com.xinlin.app.entity.pojo.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:jcdf-springmvc-config.xml","classpath*:jcdf-context-test.xml"})
@Transactional
public class UserServiceTest  {

	//@Resource(name="userDao")
	@Autowired
	private UserDao userDao;
	

	@Test
	public void deleteById() {
		String id = "c6bd1652-f7e1-4121-827d-8302b3bc8ae9";
		userDao.deleteById(id);
		//测试事务
		//int j = 1/0;
	}

	@Test
	public void get() {
		String id = "c6bd1652-f7e1-4121-827d-8302b3bc8ae9";
		User u =  userDao.get(id);
		Assert.notNull(u);
	}

	@Test
	public void insert() {
		User u = new User();
		u.setId(UUID.randomUUID().toString());
		u.setUserName("test ser");
		u.setPassword("123");
		Assert.notNull(userDao);
		userDao.insert(u);
	}

	@Test
	public void list() {
		  List<User> list = userDao.list();
		  Assert.isTrue(list.size() > 0);
	}

	@Test
	public void update() {
		String id = "c6bd1652-f7e1-4121-827d-8302b3bc8ae9";
		User u =  userDao.get(id);
		u.setUserName("after update");
		userDao.update(u);
	}

}
