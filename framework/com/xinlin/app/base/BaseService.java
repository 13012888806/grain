package com.xinlin.app.base;

import java.util.List;

/**
 * 定义泛型通用Service接口
 * @author JXQ
 * @date 2013-10-15
 *
 * @param <T>
 */
public interface BaseService<T> {
	public List<T> list();
	public T get(String id);
	public Object insert(T t);
	public Object update(T t);
	public Object deleteById(String id);
}
