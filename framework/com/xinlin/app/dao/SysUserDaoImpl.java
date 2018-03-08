package com.xinlin.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xinlin.app.entity.pojo.SysUser;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.mapper.SysUserMapper;
import com.xinlin.app.util.platform.filter.JcdfDaoSupport;

/**
 * 
 * ClassName: SysUserDaoImpl 
 * @Description: 用户数据库访问接口实现
 * @author Chenxl
 * @date 2015-5-15
 */
@Repository(value="sysUserDao")
public class SysUserDaoImpl extends JcdfDaoSupport implements SysUserDao {

	/**用户数据库操作工具*/
	@Autowired
	private SysUserMapper sysUserMapper = null;
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.dao.ISysUserDao#pageQuery(com.xinlin.app.entity.pojo.SysUser)
	 */
	@Override
	public Page pageQuery(SysUser sysUser) throws Exception {
		return this.pageQuery(sysUserMapper, "pageQuery", sysUser);
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseDao
	 */
	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		sysUserMapper.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseDao
	 */
	@Override
	public SysUser get(String id) {
		return sysUserMapper.get(id);
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseDao
	 */
	@Override
	public void insert(SysUser t) {
		sysUserMapper.insert(t);
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseDao
	 */
	@Override
	public List<SysUser> list() {
		return sysUserMapper.list();
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseDao
	 */
	@Override
	public void update(SysUser t) {
		sysUserMapper.update(t);
	}

	@Override
	public List<String> queryUserMenuCode(String userId) {
		// TODO Auto-generated method stub
		return sysUserMapper.queryUserMenuCode(userId);
	}
}