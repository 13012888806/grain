package com.xinlin.app.util.platform.plugin.util;

import java.io.File;
import java.util.ArrayList;

/**
 * 数据读入器基类
 * 
 * @author JiangShui
 * @date	2013-10-29
 */
public abstract class DataReader {
	/**将要被解析读取的文件*/
	private File file;
	/**文件内容分隔符(一般针对txt等文件有效，默认采用逗号)*/
	protected String separator;

	/**
	 * 获取多行文件数据，调用getRow(int index)实现
	 * 
	 * @param beginIndex
	 *            起始行下标，从0开始
	 * @param endIndex
	 *            结束行下标
	 * @return 返回beginIndex到endIndex(含)之间的所有数据行
	 * @throws Exception
	 */
	public ArrayList<Object[]> getRows(int beginIndex, int endIndex) throws Exception {
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		for (int i = beginIndex; i <= endIndex; i++) {
			rows.add(getRow(i));
		}
		return rows;
	}

	/**
	 * 获取单行数据
	 * 
	 * @param index
	 *            行号，从0开始
	 * @return 行号不存在时返回null
	 * @throws Exception
	 */
	public abstract Object[] getRow(int index) throws Exception;
	
	/**
	 * 统计文件行数
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract int countTotalRow() throws Exception;

	/**
	 * 打开文件
	 * 
	 * @throws Exception
	 */
	protected abstract void openFile() throws Exception;

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

	/**
	 * 获得文件对象
	 * 
	 * @return
	 */
	public File getFile() {
		return file;
	}

	/**
	 * 设置文件对象
	 * 
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 获得分隔符
	 * 
	 * @return
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * 设置分隔符
	 * 
	 * @param separator
	 */
	public void setSeparator(String separator) {
		this.separator=separator;
	}
}