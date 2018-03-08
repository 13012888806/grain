package com.xinlin.app.mapper;

import java.util.List;

import com.xinlin.app.entity.pojo.User;


public interface UserMapper {

	public void addUser(User user);

	public List<User> getAllUser();

	public User getUserById(String id);

	public void deleteById(String id);

	public void update(User user);

}
