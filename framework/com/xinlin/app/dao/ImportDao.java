package com.xinlin.app.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.xinlin.app.entity.vo.Message;

public interface ImportDao {

	/**
	 * 导出业务处理
	 * 
	 * @param params
	 * @param fileDatas
	 * @return
	 */
	public abstract Message processImport(HashMap<String, String> params, ArrayList<Object[]> fileDatas);

}