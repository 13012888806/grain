/**  
 * @Title: ClServiceImpl.java
 * @Package com.xinlin.baseInfo.service
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
package com.xinlin.baseInfo.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.entity.vo.Page;
import com.xinlin.app.util.JCDFDateUtil;
import com.xinlin.baseInfo.dao.ClDao;
import com.xinlin.baseInfo.entity.CusLevel;

/**
 * ClassName: ClServiceImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Service(value="clService")
@Transactional
public class ClServiceImpl implements ClService {
	
	@Resource(name="clDao")
	private ClDao clDao;

	/**
	 * 通过客户级别的ids删除对应的客户级别
	 */
	@Override
	public Object deleteById(String id) {
		Message msg = null;
		try {
			clDao.deleteById(id);
			msg = new Message(true, "客户级别删除成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,删除客户级别失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService#get(java.lang.String)
	 */
	@Override
	public Map<String, Object> get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 添加客户级别
	 */
	@Override
	public Object insert(Map<String, Object> t){
		Message msg = null;
		try {
			t.put("ID", JCDFDateUtil.getRandomNum());
			Random rand = new Random();
	 		int temp = rand.nextInt(99999999);
			t.put("CL_CD", temp);//客户级别编码
			clDao.insert(t);
			msg = new Message(true, "客户级别添加成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,添加客户级别失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 查询客级别，下拉框用
	 */
	@Override
	public List<Map<String, Object>> list() {
		
		return clDao.list();
	}

	/**
	 * 修改客户级别
	 */
	@Override
	public Object update(Map<String, Object> t) {
		Message msg = null;
		try {
			clDao.update(t);
			msg = new Message(true, "客户级别修改成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,修改客户级别失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 分页查询客户级别
	 */
	@Override
	public Page pageQuery(CusLevel cusLevel) {
		Page page = null;
		try {
			return clDao.pageQuery(cusLevel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

}
