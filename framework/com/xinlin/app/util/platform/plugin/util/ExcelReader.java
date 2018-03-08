package com.xinlin.app.util.platform.plugin.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Excel读取解析器实现
 * 
 * @author Jiangshui
 * @date	2013-10-29
 */
public class ExcelReader extends DataReader {
	private FileInputStream fileIns;
	private Workbook book;
	private Sheet sheet;
	private FormulaEvaluator formulaEval;

	/**
	 * 使用全局最小列和最大列坐标
	 */
	private int globalColMinIndex = 0;
	private int globalColMaxIndex = 0;

	private static final SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
	private static final DecimalFormat deciFmt = new DecimalFormat("0.0");

	@Override
	public int countTotalRow() throws InvalidFormatException, IOException {
		this.openFile();
		int rows = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
		if (rows == 1) {// 1行时进行判断，poi在无数据和仅一行时getLastRowNum都返回0
			Object[] row = this.getRow(0);
			if (row == null || row.length == 0) {
				rows = 0;
			}
		}
		return rows;
	}

	@Override
	public Object[] getRow(int index) throws InvalidFormatException, IOException {
		this.openFile();
		Row row = sheet.getRow(index);
		Object[] rowData = null;
		if (row != null) {
			int colMinIndex = row.getFirstCellNum();
			if (colMinIndex > 0 && colMinIndex < globalColMinIndex) {// 以最小一列作为全局最小列
				globalColMinIndex = colMinIndex;
			}
			int colMaxIndex = row.getLastCellNum();
			if (colMaxIndex > globalColMaxIndex) {// 以最大一列作为全局最大列
				globalColMaxIndex = colMaxIndex;
			}
			// 列数，colMaxIndex 1-base then -1
			int colsNum = globalColMaxIndex - globalColMinIndex;
			if (colMinIndex == -1 || colMaxIndex == -1 || colMinIndex>=colMaxIndex || colsNum < 1) {// 空行不返回数据
				return new Object[0];
			}
			rowData = new Object[colsNum];
			int rdIndex = 0;
			for (int colIndex = globalColMinIndex; colIndex < globalColMaxIndex; colIndex++) {
				Cell cell = row.getCell(colIndex);
				Object value = getCellValue(cell);
				rowData[rdIndex++] = value;
			}
		}
		return rowData;
	}

	/**
	 * 获取每个单元格的值
	 * 
	 * @param cell	Excel的单元格
	 * 
	 * @return	解析出来的单元格的值
	 */
	private Object getCellValue(Cell cell) {
		Object value = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				value = this.getNumbericCellValue(cell);
				break;
			case Cell.CELL_TYPE_FORMULA:
				value = formulaEval.evaluate(cell).getStringValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			default:
				value = null;
				break;
			}
		}
		if ("".equals(value)) {
			value = null;
		}
		return value;
	}

	/**
	 * 取得数据值单元格的值，日期，数字字符串等
	 * 
	 * @param cell
	 * @return
	 */
	private Object getNumbericCellValue(Cell cell) {
		double val = cell.getNumericCellValue();
		Object value = val;
		try {
			if (DateUtil.isCellInternalDateFormatted(cell)) {// poi 日期与数值区分
				value = dateFmt.format(cell.getDateCellValue());

			} else {
				long valLong = new Double(val).longValue();
				if (val == valLong) {// 区分字符串数字
					value = Long.toString(valLong);
				} else {
					value = deciFmt.format(val);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return value;
	}

	@Override
	protected void openFile() throws InvalidFormatException, IOException {
		if (fileIns == null) {
			fileIns = new FileInputStream(this.getFile());
		}
		if (book == null) {
			book = WorkbookFactory.create(fileIns);
			formulaEval = book.getCreationHelper().createFormulaEvaluator();
		}
		if (sheet == null) {
			sheet = book.getSheetAt(0);
			if ("StartUp".equals(sheet.getSheetName())) {
				sheet = book.getSheetAt(1);
			}
		}

	}

	@Override
	protected void closeFile() throws IOException {
		sheet = null;
		book = null;
		formulaEval = null;
		if (fileIns != null) {
			fileIns.close();
			fileIns = null;
		}
	}
}
