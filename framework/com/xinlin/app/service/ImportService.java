package com.xinlin.app.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.xinlin.app.entity.vo.Message;

/**
 * 数据导入业务类接口
 * 
 * @author JiangShui
 * @date	2013-10-28
 *
 */
public interface ImportService {

	/**
	 * 数据导入
	 * 
	 * @param importFile
	 * @param params
	 * @return
	 */
	public abstract Message processImport(HashMap<String,String> params, ArrayList<Object[]> fileDatas);

}