/**  
 * @Title: SlServiceImpl.java
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
import com.xinlin.baseInfo.dao.SlDao;
import com.xinlin.baseInfo.entity.SupLevel;

/**
 * ClassName: SlServiceImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Service(value="slService")
@Transactional
public class SlServiceImpl implements SlService {
	
	@Resource(name="slDao")
	private SlDao slDao;

	/**
	 * 通过供应商级别的ids删除对应的供应商级别
	 */
	@Override
	public Object deleteById(String id) {
		Message msg = null;
		try {
			slDao.deleteById(id);
			msg = new Message(true, "供应商级别删除成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,删除供应商级别失败！");
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
	 * 添加供应商级别
	 */
	@Override
	public Object insert(Map<String, Object> t){
		Message msg = null;
		try {
			t.put("ID", JCDFDateUtil.getRandomNum());
			Random rand = new Random();
	 		int temp = rand.nextInt(99999999);
			t.put("SL_CD", temp);//级别编码
			slDao.insert(t);
			msg = new Message(true, "供应商级别添加成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,添加供应商级别失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 查询供应商级别,下拉框用
	 */
	@Override
	public List<Map<String, Object>> list() {
		return slDao.list();
	}

	/**
	 * 修改供应商级别
	 */
	@Override
	public Object update(Map<String, Object> t) {
		Message msg = null;
		try {
			slDao.update(t);
			msg = new Message(true, "供应商级别修改成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,修改供应商级别失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 分页查询供应商级别
	 */
	@Override
	public Page pageQuery(SupLevel supLevel) {
		Page page = null;
		try {
			return slDao.pageQuery(supLevel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

}
