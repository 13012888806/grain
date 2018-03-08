package com.xinlin.app.util.platform.plugin.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * 写数据到Csv文件
 * 
 * @author Jiangshui
 * @date	2013-11-10
 */
public class CsvWriter extends DataWriter {
	
	private PrintWriter writer;
	/**
	 * CSV转义字符,半角双引号\"
	 */
	public static final String CSV_QUOTE = "\"";

	public CsvWriter(File file) {
		super(file);
	}
	
	@Override
	protected void createFile() throws Exception {
		if (writer == null) {
			if (!file.exists()) {
				file.getParentFile().mkdirs();
			}
			writer = new PrintWriter(new FileWriter(file, true));// support append mode
		}
	}

	@Override
	public int writeRow(Object[] row) throws Exception {
		if (row == null || row.length == 0) {
			return 0;
		}
		this.createFile();
		StringBuilder rowString = new StringBuilder();
		for (Object value : row) {
			rowString.append(",");
			if (value == null || "null".equalsIgnoreCase(String.valueOf(value))) {
				value = "";
			}
			// 检查字符串并进行转义
			if (value instanceof String) {
				String str = (String) value;
				if(str.indexOf(CSV_QUOTE) > -1 || str.indexOf("\n")>-1 || str.indexOf(",") > -1){//在需要转义的值[\n\"\,]两端添加双引号进行转义
					if (str.indexOf(CSV_QUOTE) > -1) {//把一个双引号替换为两个双引号进行转义
						str=str.replaceAll(CSV_QUOTE, CSV_QUOTE + CSV_QUOTE);					
					}
					str=CSV_QUOTE+str+CSV_QUOTE;
				}
				value=str;
			}
			rowString.append(value);
		}
		writer.println(rowString.substring(1));
		writer.flush();
		return 1;
	}
	
	@Override
	protected void closeFile() throws Exception {
		if (writer != null) {
			writer.close();
			writer = null;
		}
	}
}