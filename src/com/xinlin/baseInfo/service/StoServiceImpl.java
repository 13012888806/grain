/**  
 * @Title: StoServiceImpl.java
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
import com.xinlin.baseInfo.dao.StoDao;
import com.xinlin.baseInfo.entity.Storage;

/**
 * ClassName: StoServiceImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Service(value="stoService")
@Transactional
public class StoServiceImpl implements StoService {
	
	@Resource(name="stoDao")
	private StoDao stoDao;

	/**
	 * 通过入库信息的ids删除对应的入库信息
	 */
	@Override
	public Object deleteById(String id) {
		Message msg = null;
		try {
			stoDao.deleteById(id);
			msg = new Message(true, "入库信息删除成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,删除入库信息失败！");
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
	 * 添加入库信息
	 */
	@Override
	public Object insert(Map<String, Object> t){
		Message msg = null;
		try {
			t.put("ID", JCDFDateUtil.getRandomNum());
			Random rand = new Random();
	 		int temp = rand.nextInt(99999999);
			t.put("WS_CD", temp);//仓库编码
			stoDao.insert(t);
			msg = new Message(true, "入库信息添加成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,添加入库信息失败！");
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
	 * 修改入库信息
	 */
	@Override
	public Object update(Map<String, Object> t) {
		Message msg = null;
		try {
			stoDao.update(t);
			msg = new Message(true, "入库信息修改成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,修改入库信息失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 分页查询入库信息
	 */
	@Override
	public Page pageQuery(Storage storage) {
		Page page = null;
		try {
			return stoDao.pageQuery(storage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

}
