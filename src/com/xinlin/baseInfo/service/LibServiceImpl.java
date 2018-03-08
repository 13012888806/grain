/**  
 * @Title: LibServiceImpl.java
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
import com.xinlin.baseInfo.dao.LibDao;
import com.xinlin.baseInfo.entity.Library;

/**
 * ClassName: LibServiceImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Service(value="libService")
@Transactional
public class LibServiceImpl implements LibService {
	
	@Resource(name="libDao")
	private LibDao libDao;

	/**
	 * 通过出库信息的ids删除对应的出库信息
	 */
	@Override
	public Object deleteById(String id) {
		Message msg = null;
		try {
			libDao.deleteById(id);
			msg = new Message(true, "出库信息删除成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,删除出库信息失败！");
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
	 * 添加出库信息
	 */
	@Override
	public Object insert(Map<String, Object> t){
		Message msg = null;
		try {
			t.put("ID", JCDFDateUtil.getRandomNum());
			Random rand = new Random();
	 		int temp = rand.nextInt(99999999);
			t.put("WS_CD", temp);//仓库编码
			libDao.insert(t);
			msg = new Message(true, "出库信息添加成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,添加出库信息失败！");
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
	 * 修改出库信息
	 */
	@Override
	public Object update(Map<String, Object> t) {
		Message msg = null;
		try {
			libDao.update(t);
			msg = new Message(true, "出库信息修改成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,修改出库信息失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 分页查询出库信息
	 */
	@Override
	public Page pageQuery(Library library) {
		Page page = null;
		try {
			return libDao.pageQuery(library);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

}
