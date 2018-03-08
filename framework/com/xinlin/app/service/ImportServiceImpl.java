package com.xinlin.app.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinlin.app.entity.vo.Message;

/**
 * 数据导入业务实现
 * 
 * @author JiangShui
 * @date	2013-10-28
 *
 */
@Service(value="importService")
@Transactional
public class ImportServiceImpl implements ImportService {
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.service.ImportService#importData(java.io.File, java.util.Map)
	 */
	public Message processImport(HashMap<String,String> params, ArrayList<Object[]> fileDatas) {
          System.out.println("params="+params);
          System.out.println("fileDatas"+fileDatas);
          return new Message(true, "呵呵收到了！");
	}
}