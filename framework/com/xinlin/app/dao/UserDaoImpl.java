package com.xinlin.app.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xinlin.app.entity.pojo.User;
import com.xinlin.app.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Value;   
import org.springframework.stereotype.Controller;   
import org.springframework.web.bind.annotation.RequestMapping;   



@Repository(value="userDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
	
	@Autowired
	private UserMapper userMapper = null;

	
	private String dbname;

	@Value("#{settings['datasource.dbname']}")
	public void setDbname(String dbname) {
		this.dbname = dbname;
	} 
	
	
	/*@Autowired
	public void setMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}*/
	
	//删除
	public void deleteById(String id) {
		userMapper.deleteById(id);
	}

	//新增
	public void insert(User u) {
		userMapper.addUser(u);
	}

	//列表
	public List<User> list() {
		return userMapper.getAllUser();
	}

	//修改
	public void update(User u) {
		userMapper.update(u);
	}

	//获取对象
	public User get(String id) {
		return userMapper.getUserById(id);
	}

}
