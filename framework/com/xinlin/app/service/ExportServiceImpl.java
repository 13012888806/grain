package com.xinlin.app.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinlin.app.dao.ExportDao;
import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.util.platform.plugin.DataExportPlugin;

/**
 * 角色管理业务类实现
 * 
 * @author JiangShui
 * @date	2013-10-13
 *
 */
@Service(value="exportService")
@Transactional
public class ExportServiceImpl implements ExportService{

	/**角色数据库操作接口*/
	@Resource(name="exportDao")
	private ExportDao exportDao;
	/**系统导出功能的所有参数配置*/
//	public static Map<String, String> exportConfig;
	/**系统根目录*/
//	private static String rootPath;
	
	/* (non-Javadoc)
	 * @see com.xinlin.app.service.ExportService#exportData(java.lang.String, java.lang.String, java.lang.Object)
	 */
	public Message exportData(String mapperId, Object params) {
		DataExportPlugin dxp = new DataExportPlugin(mapperId);
		List<Map<String, Object>> datas = null;
		Message msg = null;
		try {
			//优先选择采用Mapper接口调用mybatis的底层查询
			if(null != dxp.getClassName() && null != dxp.getMethodName()) {
				datas = exportDao.exportData(dxp.getClassName(), dxp.getMethodName(), params);
			} else {
				datas = exportDao.exportData(dxp.getMapperSqlId(), params);
			}
			if (null != datas && datas.size() > 0) {
				msg = dxp.exportData((List<Map<String, Object>>)datas, mapperId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
		
		
//		if(null == exportConfig) {
//			this.rootPath = rootPath;
//			initExportConfig();
//		}
//		String exportFileTempPath = ExportServiceImpl.exportConfig.get("export.tempfilePath");
//		String className = ExportServiceImpl.exportConfig.get(mapperId+".className");
//		String methodName = ExportServiceImpl.exportConfig.get(mapperId+".methodName");
//		String mapperSqlId = ExportServiceImpl.exportConfig.get(mapperId+".mapperId");
//		String fileType = ExportServiceImpl.exportConfig.get(mapperId+".fileType");
//		String[] columnNames=ExportServiceImpl.exportConfig.get(mapperId+".columnNames").split(",");
//		String[] fieldNames=ExportServiceImpl.exportConfig.get(mapperId+".fieldNames").split(",");
//		
//		boolean result = false;
//		String fileTempName = null;
//		try {
//			List<Object> datas = null;
//			//优先选择采用Mapper接口调用mybatis的底层查询
//			if(null != className && null != methodName) {
//				datas = exportDao.exportData(className, methodName, params);
//			} else {
//				datas = exportDao.exportData(mapperSqlId, params);
//			}
//			if(null != datas && datas.size() > 0) {
//				if (null != fileType && "csv".equals(fileType.trim().toLowerCase())) {
//					 fileTempName = UUID.randomUUID().toString()+".csv";
//				} else {
//					fileTempName = UUID.randomUUID().toString()+".xls";
//				}
//				 File file = this.createFileByPathName(exportFileTempPath, fileTempName);
//				 if (null != fileType && "csv".equals(fileType.trim().toLowerCase())) {
//					result = this.writeDataToFile(datas, file, fileType,columnNames,fieldNames);
//				} else {
//					result = this.writeExcelData(datas, file, columnNames, fieldNames);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if(!result) {
//			return new Message(false,"文件导出失败");
//		} else {
//			return new Message(true,fileTempName);
//		}
	}
	
//	public boolean writeExcelData(List<Object> datas, File file, String[] columnNames, String[] fieldNames) {
//		boolean result = false;
//		
//		int sheetSize = 40000;
//		HSSFWorkbook wb = new HSSFWorkbook();
//		int size=datas.size();
//		int pages=(int)Math.ceil((double)size/(double)sheetSize);
//		if(pages == 0){
//			pages=1;
//		}
//		for(int p=0 ; p< pages ; p++){
//			int pageEnd = sheetSize*(p+1);
//			if(p == (pages-1)){
//				pageEnd =size;
//			}
//			//可扩展多个sheet页的导出数据
//			HSSFSheet sheet = wb.createSheet();
//			HSSFRow sheetRow = sheet.createRow(0);
//			sheet.setDefaultColumnWidth(12);
//			// 设置列名
//			for (int i = 0; i < columnNames.length; i++) {
//				sheetRow.createCell(i).setCellValue(columnNames[i].trim());	
//			}
//			
//			// 填充内容
//			Map map = null;
//			if (datas != null) {
//				int index=1;
//				for (int j = p*sheetSize; j < pageEnd; j++) {
//					sheetRow = sheet.createRow(index++);
//					//如果是Map，则强制转换成Map并遍历处理各元素
//					map = (Map)datas.get(j);
//					int m = 0;
//					for (String fieldName : fieldNames) {
//						String value=String.valueOf(map.get(fieldName));
//						if(null == value || "NULL".equals(value.toUpperCase())) {
//							value="";
//						}
//						sheetRow.createCell(m++).setCellValue(value);
//					}
//				}
//			}
//		
//		}
//		FileOutputStream os = null;
//		try {
//			os = new FileOutputStream(file);
//			wb.write(os);
//			result = true;
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				os.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
	
	/**
	 * 将查询出的列表数据写入文件
	 * 
	 * @param datas
	 * @param file
	 * @param fileType
	 * @param columnNames
	 * @param fieldNames
	 * @return
	 */
//	private boolean writeDataToFile(List<Object> datas, File file, String fileType, String[] columnNames, String[] fieldNames) {
//		boolean result = false;
//		FileWriter fw = null;
//		try {
//			if(null != datas) {
//				fw = new FileWriter(file);
//				int index = 0;
//				
//				//如果是csv文件
//				if (null != fileType && "csv".equals(fileType.toLowerCase())) {
//					for (Object data : datas) {
//						index = index + 1;
//						fw.write(this.getDataFromMapToCsv(data, 1 == index?columnNames:null, fieldNames));
//					}
//				//默认按照excel文件处理
//				} else {
//					fw.write("<table border='1'>\n");
//					for (Object data : datas) {
//						index = index + 1;
//						fw.write(this.getDataFromMapToExcel(data, 1 == index?columnNames:null, fieldNames));
//					}
//					fw.write("</table>");
//				}
//				fw.flush();
//				result = true;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(null != fw) {
//					fw.close();
//					fw = null;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
	
	/**
	 * 
	 * 
	 * @param map
	 * @return
	 */
//	private String getDataFromMapToExcel(Object data, String[] columnNames, String[] fieldNames) {
//		StringBuffer bodyBuffer = null;
//		StringBuffer headBuffer = null;
//		//处理文件头
//		if(null != columnNames) {
//			headBuffer = new StringBuffer("<tr>");
//			for (String columnName : columnNames) {
//				headBuffer.append("<td>").append(columnName).append("</td>");
//			}
//			headBuffer.append("</tr>\n");
//		}
//		//如果是Map，则强制转换成Map并遍历处理各元素
//		if(null != fieldNames && data instanceof Map) {
//			Map map = (Map)data;
//			bodyBuffer = new StringBuffer("<tr>");
//			for (String fieldName : fieldNames) {
//				bodyBuffer.append("<td>").append((null==map.get(fieldName)|| "null".equals(map.get(fieldName).toString().toLowerCase())) ? "" : map.get(fieldName).toString()).append("</td>");
//			}
//			bodyBuffer.append("</tr>\n");
//		}
//		if(null != headBuffer && null != bodyBuffer) {
//			return headBuffer.append(bodyBuffer).toString();
//		} else if (null != headBuffer){
//			return headBuffer.toString();
//		} else if(null != bodyBuffer){
//			return bodyBuffer.toString();
//		} else {
//			return "";
//		}
//	}
	
	/**
	 * 
	 * 
	 * @param map
	 * @return
	 */
//	private String getDataFromMapToCsv(Object data, String[] columnNames, String[] fieldNames) {
//		StringBuffer bodyBuffer = null;
//		StringBuffer headBuffer = null;
//		//处理文件头
//		if(null != columnNames) {
//			headBuffer = new StringBuffer();
//			for (String columnName : columnNames) {
//				if(headBuffer.length() <= 0) {
//					headBuffer.append(columnName);
//				} else {
//					headBuffer.append(",").append(columnName);
//				}
//			}
//			headBuffer.append("\n");
//		}
//		//如果是Map，则强制转换成Map并遍历处理各元素
//		if(null != fieldNames && data instanceof Map) {
//			Map map = (Map)data;
//			bodyBuffer = new StringBuffer();
//			for (String fieldName : fieldNames) {
//				if(bodyBuffer.length() <= 0) {
//					bodyBuffer.append(map.get(fieldName));
//				} else {
//					//如果是null(包括从数据库中查得的null字符串)，则按空字符串处理，考虑到英文逗号是csv的分隔符，自动替换成中文逗号。
//					bodyBuffer.append(",").append((null==map.get(fieldName)|| "null".equals(map.get(fieldName).toString().toLowerCase())) ? "" : map.get(fieldName).toString().replaceAll(",", "，"));
//				}
//			}
//			bodyBuffer.append("\n");
//		}
//		if(null != headBuffer && null != bodyBuffer) {
//			return headBuffer.append(bodyBuffer).toString();
//		} else if (null != headBuffer){
//			return headBuffer.toString();
//		} else if(null != bodyBuffer){
//			return bodyBuffer.toString();
//		} else {
//			return "";
//		}
//	}
	
	/**
	 * 创建指定路径和名称的文件
	 * 
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
//	private File createFileByPathName(String filePath, String fileName){
//		File path = new File(filePath);
//		File file = null;
//		try {
//			if(!path.exists()) {
//				path.mkdirs();
//			}
//			if(path.exists()) {
//				file = new File(path, fileName);
//				file.deleteOnExit();
//				file.createNewFile();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return file;
//	}
	
//	/**
//	 * 读配置文件内容
//	 * 
//	 */
//	public void initExportConfig() {
//		InputStream in = null;
//		if(null == exportConfig)exportConfig = new HashMap<String,String>();
//		try {
//			// 加载导出功能配置文件(config/export/export.properties)
//			Properties props = new Properties();
//			in = StaticVar.class.getClassLoader().getResourceAsStream("export/export.properties");
//			props.load(in);
//			Set<Object> keys = props.keySet();
//			for (Object key : keys) {
//				String value = (new String(props.getProperty(key.toString()).getBytes("ISO-8859-1"),"utf-8")).trim();
//				//文件导出临时文件存放路径需要特殊处理
//				if("export.tempfilePath".equals(key.toString())) {
//					//如果是相对项目根目录的路径，则加上项目根目录
//					if(value.startsWith("root:")) {
//						value = value.replace("root:\\", "").replace("root:/", "").replace("root:", "");
//						value = (value.endsWith("/")||value.endsWith("\\")) ? value : value+"/";
//						value = this.rootPath + ((this.rootPath.endsWith("/")||rootPath.endsWith("\\")) ? "" : "/") + value;
//					}
//					//为兼容linux系统，路径分隔符统一采用/
//					value = value.replaceAll("\\\\", "/");
//				}
//				exportConfig.put(key.toString(), value);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			try {
//				if(in != null){
//					in.close();
//					in = null;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}