/**  
 * @Title: VarServiceImpl.java
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
import com.xinlin.baseInfo.dao.VarDao;
import com.xinlin.baseInfo.entity.Variety;

/**
 * ClassName: VarServiceImpl 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-3
 */
@Service(value="varService")
@Transactional
public class VarServiceImpl implements VarService {
	
	@Resource(name="varDao")
	private VarDao varDao;

	/**
	 * 通过品种管理的ids删除对应的品种管理
	 */
	@Override
	public Object deleteById(String id) {
		Message msg = null;
		try {
			varDao.deleteById(id);
			msg = new Message(true, "品种删除成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,删除品种失败！");
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
	 * 添加品种管理
	 */
	@Override
	public Object insert(Map<String, Object> t){
		Message msg = null;
		try {
			t.put("ID", JCDFDateUtil.getRandomNum());
			Random rand = new Random();
	 		int temp = rand.nextInt(99999999);
			t.put("VAR_CD", temp);//品种编码
			varDao.insert(t);
			msg = new Message(true, "品种添加成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,添加品种失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 查询品种的键值对集合,在下拉框中使用
	 */
	@Override
	public List<Map<String, Object>> list() {
		return varDao.list();
	}

	/**
	 * 修改品种管理
	 */
	@Override
	public Object update(Map<String, Object> t) {
		Message msg = null;
		try {
			varDao.update(t);
			msg = new Message(true, "品种修改成功！");
		} catch (Exception e) {
			msg = new Message(false, "系统异常,修改品种失败！");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 分页查询品种管理
	 */
	@Override
	public Page pageQuery(Variety variety) {
		Page page = null;
		try {
			return varDao.pageQuery(variety);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

}
