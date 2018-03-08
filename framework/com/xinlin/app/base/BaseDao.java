package com.xinlin.app.base;

import java.util.List;

/**
 * 定义泛型通用DAO接口
 * @author Chenxl
 *
 * @param <T>
 */
public interface BaseDao <T>{
	public List<T> list();
	public T get(String id);
	public void insert(T t);
	public void update(T t);
	public void deleteById(String id);
}
