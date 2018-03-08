/**  
 * @Title: WhServiceImpl.java
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
import com.xinlin.baseInfo.dao.WhDao;
import com.xinlin.baseInfo.entity.WareHouse;

/**
 * ClassName: WhServiceImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Service(value="whService")
@Transactional
public class WhServiceImpl implements WhService {
	
	@Resource(name="whDao")
	private WhDao whDao;

	/**
	 * 通过仓库信息的ids删除对应的仓库信息
	 */
	@Override
	public Object deleteById(String id) {
		Message msg = null;
		try {
			whDao.deleteById(id);
			msg = new Message(true, "仓库信息删除成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,删除仓库信息失败！");
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
	 * 添加仓库信息
	 */
	@Override
	public Object insert(Map<String, Object> t){
		Message msg = null;
		try {
			t.put("ID", JCDFDateUtil.getRandomNum());
			Random rand = new Random();
	 		int temp = rand.nextInt(99999999);
			t.put("WS_CD", temp);//仓库编码
			whDao.insert(t);
			msg = new Message(true, "仓库信息添加成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,添加仓库信息失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 查询仓库信息的键值对在下拉框显示
	 */
	@Override
	public List<Map<String, Object>> list() {
		return whDao.list();
	}

	/**
	 * 修改仓库信息
	 */
	@Override
	public Object update(Map<String, Object> t) {
		Message msg = null;
		try {
			whDao.update(t);
			msg = new Message(true, "仓库信息修改成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,修改仓库信息失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 分页查询仓库信息
	 */
	@Override
	public Page pageQuery(WareHouse wareHouse) {
		Page page = null;
		try {
			return whDao.pageQuery(wareHouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

}
