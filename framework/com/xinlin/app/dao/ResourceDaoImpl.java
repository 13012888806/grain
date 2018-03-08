package com.xinlin.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xinlin.app.entity.pojo.Resource;
import com.xinlin.app.mapper.ResourceMapper;
import com.xinlin.app.util.platform.filter.JcdfDaoSupport;

/**
 * 资源数据库访问接口实现
 * 
 * @author Jiangshui
 * @date	2013-10-13
 *
 */
@Repository(value="resourceDao")
public class ResourceDaoImpl extends JcdfDaoSupport implements ResourceDao {

	/**资源数据库操作工具*/
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Resource get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Resource t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Resource> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Resource t) {
		// TODO Auto-generated method stub
		
	}

}
