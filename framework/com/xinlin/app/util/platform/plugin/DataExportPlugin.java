package com.xinlin.app.util.platform.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import com.xinlin.app.entity.vo.Message;
import com.xinlin.app.util.JCDFFileUtil;
import com.xinlin.app.util.StaticVar;
import com.xinlin.app.util.platform.plugin.util.CsvWriter;
import com.xinlin.app.util.platform.plugin.util.DataWriter;
import com.xinlin.app.util.platform.plugin.util.ExcelWriter;

/**
 * 数据导出插件
 * 
 * @author Jiangshui
 * @date	2013-11-10
 */
public class DataExportPlugin {

	/**系统导出功能的所有参数配置*/
	public static Map<String, String> exportConfig;
	private String className;
	private String methodName;
	private String mapperSqlId;
	
	/**
	 * 默认构造函数，负责初始化导入配置参数
	 */
	public DataExportPlugin(String mapperId) {
		if(null == exportConfig) {
			initExportConfig();
		}
		this.className = exportConfig.get(mapperId+".className");
		this.methodName = exportConfig.get(mapperId+".methodName");
		this.mapperSqlId = exportConfig.get(mapperId+".mapperId");
	}
	
	public Message exportData(List<Map<String, Object>> rows, String mapperId) {
		int count = 0;
		DataWriter dw = null;
		
		String exportFileTempPath = exportConfig.get("export.tempfilePath");
		String fileType = exportConfig.get(mapperId+".fileType");
		String[] columnNames=exportConfig.get(mapperId+".columnNames").split(",");
		String[] fieldNames=exportConfig.get(mapperId+".fieldNames").split(",");
		
		String fileTempName = null;
		try {
			if(null != rows && rows.size() > 0) {
				if (null != fileType && "csv".equals(fileType.trim().toLowerCase())) {
					 fileTempName = UUID.randomUUID().toString()+".csv";
				} else {
					fileTempName = UUID.randomUUID().toString()+".xls";
				}
				File file = this.createFileByPathName(exportFileTempPath, fileTempName);
				if (null != fileType && "csv".equals(fileType.trim().toLowerCase())) {
					dw = new CsvWriter(file);
				} else {
					dw = new ExcelWriter(file);
				}
				count = dw.writeRow(columnNames);
				count = count + dw.writeRows(rows, fieldNames);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(count <= 0) {
			return new Message(false,"文件导出失败");
		} else {
			return new Message(true,fileTempName);
		}
	}
	
	/**
	 * 创建指定路径和名称的文件
	 * 
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private File createFileByPathName(String filePath, String fileName){
		File path = new File(filePath);
		File file = null;
		try {
			if(!path.exists()) {
				path.mkdirs();
			}
			if(path.exists()) {
				file = new File(path, fileName);
				file.deleteOnExit();
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	/**
	 * 读配置文件内容
	 * 
	 */
	public void initExportConfig() {
		InputStream in = null;
		String rootPath = JCDFFileUtil.getWebRealPath("/");
		if(null == exportConfig)exportConfig = new HashMap<String,String>();
		try {
			// 加载导出功能配置文件(config/export/export.properties)
			Properties props = new Properties();
			in = StaticVar.class.getClassLoader().getResourceAsStream("export/export.properties");
			props.load(in);
			Set<Object> keys = props.keySet();
			for (Object key : keys) {
				String value = (new String(props.getProperty(key.toString()).getBytes("ISO-8859-1"),"utf-8")).trim();
				//文件导出临时文件存放路径需要特殊处理
				if("export.tempfilePath".equals(key.toString())) {
					//如果是相对项目根目录的路径，则加上项目根目录
					if(value.startsWith("root:")) {
						value = value.replace("root:\\", "").replace("root:/", "").replace("root:", "");
						value = (value.endsWith("/")||value.endsWith("\\")) ? value : value+"/";
						value = rootPath + ((rootPath.endsWith("/")||rootPath.endsWith("\\")) ? "" : "/") + value;
					}
					//为兼容linux系统，路径分隔符统一采用/
					value = value.replaceAll("\\\\", "/");
				}
				exportConfig.put(key.toString(), value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(in != null){
					in.close();
					in = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getMapperSqlId() {
		return mapperSqlId;
	}
}
