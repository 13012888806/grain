package com.xinlin.test.service;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.xinlin.app.entity.pojo.FileLoad;
import com.xinlin.app.entity.pojo.User;
import com.xinlin.app.service.FileLoadService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:jcdf-springmvc-config.xml","classpath*:jcdf-context-test.xml"})
@Transactional
public class IFileLoadServiceTest  {

	//@Resource(name="userDao")
	@Autowired
	private FileLoadService iFileLoadService;
	

	
	@Test
	public void get() {
		String keyId = "43dde3e0-6ddf-4a5c-b72e-7a1289b03c17";
		FileLoad f = this.iFileLoadService.get(keyId);
		Assert.notNull(f);
	}
/*
	@Test
	public void insert() {
		User u = new User();
		u.setId(UUID.randomUUID().toString());
		u.setUserName("test ser");
		u.setPassword("123");
		Assert.notNull(userDao);
		userDao.insert(u);
	}*/

	/*@Test
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
	}*/

}
