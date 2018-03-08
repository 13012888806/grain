/**  
 * @Title: CusServiceImpl.java
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
import com.xinlin.baseInfo.dao.CusDao;
import com.xinlin.baseInfo.entity.Customer;

/**
 * ClassName: CusServiceImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Service(value="cusService")
@Transactional
public class CusServiceImpl implements CusService {
	
	@Resource(name="cusDao")
	private CusDao cusDao;

	/**
	 * 通过客户信息的ids删除对应的客户信息
	 */
	@Override
	public Object deleteById(String id) {
		Message msg = null;
		try {
			cusDao.deleteById(id);
			msg = new Message(true, "客户信息删除成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,删除客户信息失败！");
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
	 * 添加客户信息
	 */
	@Override
	public Object insert(Map<String, Object> t){
		Message msg = null;
		try {
			t.put("ID", JCDFDateUtil.getRandomNum());
			Random rand = new Random();
	 		int temp = rand.nextInt(99999999);
			t.put("CUS_CD", temp);//客户编码
			cusDao.insert(t);
			msg = new Message(true, "客户信息添加成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,添加客户信息失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 查询客户信息放到下拉框
	 */
	@Override
	public List<Map<String, Object>> list() {
		
		return cusDao.list();
	}

	/**
	 * 修改客户信息
	 */
	@Override
	public Object update(Map<String, Object> t) {
		Message msg = null;
		try {
			cusDao.update(t);
			msg = new Message(true, "客户信息修改成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,修改客户信息失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 分页查询客户信息
	 */
	@Override
	public Page pageQuery(Customer customer) {
		Page page = null;
		try {
			return cusDao.pageQuery(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

}
