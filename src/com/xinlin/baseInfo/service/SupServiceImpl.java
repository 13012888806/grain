/**  
 * @Title: SupServiceImpl.java
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
import com.xinlin.baseInfo.dao.SupDao;
import com.xinlin.baseInfo.entity.Supplier;

/**
 * ClassName: SupServiceImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Service(value="supService")
@Transactional
public class SupServiceImpl implements SupService {
	
	@Resource(name="supDao")
	private SupDao supDao;

	/**
	 * 通过供应商信息的ids删除对应的供应商信息
	 */
	@Override
	public Object deleteById(String id) {
		Message msg = null;
		try {
			supDao.deleteById(id);
			msg = new Message(true, "供应商信息删除成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,删除供应商信息失败！");
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
	 * 添加供应商信息
	 */
	@Override
	public Object insert(Map<String, Object> t){
		Message msg = null;
		try {
			t.put("ID", JCDFDateUtil.getRandomNum());
			Random rand = new Random();
	 		int temp = rand.nextInt(99999999);
			t.put("SUP_CD", temp);//供应商编码
			supDao.insert(t);
			msg = new Message(true, "供应商信息添加成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,添加供应商信息失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/* (non-Javadoc)
	 * @see com.xinlin.app.base.BaseService#list()
	 */
	@Override
	public List<Map<String, Object>> list() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 修改供应商信息
	 */
	@Override
	public Object update(Map<String, Object> t) {
		Message msg = null;
		try {
			supDao.update(t);
			msg = new Message(true, "供应商信息修改成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,修改供应商信息失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 分页查询供应商信息
	 */
	@Override
	public Page pageQuery(Supplier supplier) {
		Page page = null;
		try {
			return supDao.pageQuery(supplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

}
