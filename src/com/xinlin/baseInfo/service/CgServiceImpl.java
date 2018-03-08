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
import com.xinlin.baseInfo.dao.CgDao;
import com.xinlin.baseInfo.entity.Cg;

/**
 * ClassName: CgServiceImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Service(value="cgService")
@Transactional
public class CgServiceImpl implements CgService {
	
	@Resource(name="cgDao")
	private CgDao cgDao;

	/**
	 * 通过种类管理的ids删除对应的种类管理
	 */
	@Override
	public Object deleteById(String id) {
		Message msg = null;
		try {
			cgDao.deleteById(id);
			msg = new Message(true, "种类删除成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,删除种类失败！");
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
	 * 添加种类管理
	 */
	@Override
	public Object insert(Map<String, Object> t){
		Message msg = null;
		try {
			t.put("ID", JCDFDateUtil.getRandomNum());
			cgDao.insert(t);
			msg = new Message(true, "种类添加成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,添加种类失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 查询种类的键值对集合,在下拉框中使用
	 */
	@Override
	public List<Map<String, Object>> list() {
		return cgDao.list();
	}

	/**
	 * 修改种类管理
	 */
	@Override
	public Object update(Map<String, Object> t) {
		Message msg = null;
		try {
			cgDao.update(t);
			msg = new Message(true, "种类修改成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,修改种类失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 分页查询种类管理
	 */
	@Override
	public Page pageQuery(Cg cg) {
		Page page = null;
		try {
			return cgDao.pageQuery(cg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

}
