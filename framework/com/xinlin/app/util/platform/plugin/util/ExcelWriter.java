package com.xinlin.app.util.platform.plugin.util;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 写数据到Excel文件
 * 
 * @author Jiangshui
 * @date	2013-11-10
 */
public class ExcelWriter extends DataWriter{

	private HSSFWorkbook wb = new HSSFWorkbook();
	private HSSFSheet sheet = null;
	private FileOutputStream os = null;
	private int currentIndex = 0;
	private static final int SHEET_SIZE = 40000;
	
	public ExcelWriter(File file) {
		super(file);
	}

	@Override
	protected void createFile() throws Exception {
		if (null == os) {
			if (!file.exists()) {
				file.getParentFile().mkdirs();
			}
			os = new FileOutputStream(file);
			wb = new HSSFWorkbook();
		} 
	}

	@Override
	public int writeRow(Object[] row) throws Exception {
		if (row == null || row.length == 0) {
			return 0;
		}
		this.createFile();
		if (0 == currentIndex) {
			sheet = wb.createSheet();
			sheet.setDefaultColumnWidth(12);
		}
		HSSFRow sheetRow = sheet.createRow(currentIndex++);
		for (int i = 0; i < row.length; i++) {
			if (null == row[i] || "null".equals(String.valueOf(row[i]).toLowerCase())) {
				sheetRow.createCell(i).setCellValue("");	
			} else {
				sheetRow.createCell(i).setCellValue(String.valueOf(row[i]));
			}
		}
		currentIndex =  currentIndex == SHEET_SIZE ? 0 : currentIndex;
		return 1;
	}
	
	@Override
	protected void closeFile() throws Exception {
		wb.write(os);
		os.close();
	}
}
