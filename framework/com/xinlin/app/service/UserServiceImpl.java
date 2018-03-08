package com.xinlin.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinlin.app.dao.UserDao;
import com.xinlin.app.entity.pojo.User;


@Service(value="userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
 
	public Object deleteById(String id) {
		try {
			userDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//测试事务
		//int j = 1/0;
		return null;
	}

	public User get(String id) {
		User u = null;
		try {
			u = userDao.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	public Object insert(User u) {
		try {
			userDao.insert(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<User> list() {
		return userDao.list();
	}

	public Object update(User u) {
		try {
			userDao.update(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
