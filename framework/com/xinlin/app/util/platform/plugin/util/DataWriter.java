package com.xinlin.app.util.platform.plugin.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 数据写入器基类
 * 
 * @author JiangShui
 * @date	2013-11-10
 */
public abstract class DataWriter {
	protected File file;
	private Object[] objs = null;

	public DataWriter(File file) {
		this.file = file;
	}
	
	public int writeRows(List<Map<String, Object>> rows, String[] fieldNames) throws Exception {
		if (rows == null || rows.size() <= 0 || null == fieldNames || fieldNames.length <= 0) {
			return 0;
		}
		int count = 0;
		int len = fieldNames.length;
		for (Map<String, Object> row : rows) {
			if(null == objs) {
				objs = new Object[fieldNames.length];
			}
			for (int i = 0; i < len; i++) {
				objs[i] = row.get(fieldNames[i]);
			}
			count = count + writeRow(objs);
		}
		return count;
	}
	
	/**
	 * 多行数据写入，循环调用writeRow(Object[] row)实现
	 * 
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	public int writeRows(ArrayList<Object[]> rows) throws Exception {
		if (rows == null) {
			return 0;
		}
		int count = 0;
		for (Object[] row : rows) {
			count += writeRow(row);
		}
		return count;
	}

	/**
	 * 单行数据定入
	 * 
	 * @param row
	 *            行数据
	 * @return 返回影响行数
	 * @throws Exception
	 */
	public abstract int writeRow(Object[] row) throws Exception;

	/**
	 * 创建新文件
	 * 
	 * @throws IOException
	 */
	protected abstract void createFile() throws Exception ;

	/**
	 * 关闭文件
	 * 
	 * @throws Exception
	 */
	protected abstract void closeFile() throws Exception;

	/**
	 * 关闭数据读入器，释放资源
	 * 
	 * @throws Exception
	 */
	public void close() throws Exception {
		closeFile();
	}
}