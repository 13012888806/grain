package com.xinlin.app.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinlin.app.base.BaseController;
import com.xinlin.app.service.ExportService;
import com.xinlin.app.util.platform.plugin.DataExportPlugin;

/**
 * 数据导出控制器(目前支持Excel、csv)
 * 
 * @author JiangShui
 * @date	2013-10-24
 * 
 */
@Controller
@RequestMapping(value = "/export.do")
public class ExplortController extends BaseController{

	/**数据导出管理业务类*/
	@Resource(name="exportService")
	private ExportService exportService;
	
	/**
	 * 转发到数据导出页面
	 * 
	 * @param response
	 * 
	 * @return	数据导入jsp页面
	 */
	@RequestMapping(params="method=export")
	public String forwardToPage(HttpServletResponse response, HttpServletRequest request){
		return "test/export";
	}
	
	/**
	 * 数据导出
	 * 
	 * @param response
	 * @param request
	 * @param fileType	文件类型，excel：excel文件(默认)，csv：csv文件
	 * @param mapperId	配置文件中对应配置的id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void exportDataByGet(HttpServletResponse response, HttpServletRequest request, String mapperId){
		//考虑到底层反射调用的需要，这里需将获得参数map转化成java.util.HashMap.
		//（request.getParameterMap()获得本质不是：org.apache.catalina.util.ParameterMap<String, String[]>）
		Map parameterMap = request.getParameterMap();
		HashMap params = new HashMap();
		for (Object key : parameterMap.keySet()) {
			if(null != parameterMap.get(key)) {
				String[] values = (String[])parameterMap.get(key);
				if(values.length > 0) {
					params.put(key, values[0]);
				}
			}
		}
		sendJsonDataToClient(exportService.exportData(mapperId, params), response);
	}
	
	/**
	 * 数据导出
	 * 
	 * @param response
	 * @param request
	 * @param mapperId	配置文件中对应配置的id
	 * @param fileTempName	为导出生成的临时文件
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void downloadExportFile(HttpServletResponse response, HttpServletRequest request, String mapperId, String fileTempName){
		//从配置文件中读取导出文件的临时存放路径
		String exportFileTempPath = DataExportPlugin.exportConfig.get("export.tempfilePath");
		//从配置文件中读取导出文件的文件名
		String fileName = DataExportPlugin.exportConfig.get(mapperId+".fileName");
		boolean isSuccess = false;
		try {
			if(null != fileTempName && !"".equals(fileTempName)) {
				this.downloadFileByStream(request, response, exportFileTempPath+fileTempName, fileName);
				isSuccess = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(!isSuccess) {
			sendJsonDataToClient("文件导出失败！", response);
		}
	}
	
	/**
	 * 以流的形式下载指定的文件
	 * 
	 * @param request	由下载文件请求客户端发起的http请求
	 * @param response	
	 * @param filePath	文件绝对路径，包含文件名
	 * @param displayName	文件下载时，在客户端展示的文件名(如果不提供，则默认采用当前文件的真实文件名)
	 * @throws FileNotFoundException 
	 */
	public void downloadFileByStream(HttpServletRequest request, HttpServletResponse response, String filePath, String displayName)
			throws FileNotFoundException {
		File file = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		if (null == filePath || "".equals(filePath)) {
			throw new FileNotFoundException("没有指定目标下载文件的路径！");
		} else {
			file = new File(filePath);
			if (null == file || !file.exists() || file.isDirectory()) {
				throw new FileNotFoundException("指定路径的文件不存在!-->>[" + filePath+ "]");
			} else {
				try {
					response.setContentType("binary/octet-stream");
					if (null == displayName || "".equals(displayName)) {
						int lastIndex = filePath.lastIndexOf("\\");
						lastIndex = -1 == lastIndex ? filePath.lastIndexOf("/"): lastIndex;
						displayName = -1 == lastIndex ? filePath : filePath.substring(lastIndex + 1);
					}
					response.addHeader("Content-Disposition","attachment;filename="+ new String(displayName.getBytes("gbk"),"iso-8859-1"));
					bis = new BufferedInputStream(new FileInputStream(file));
					bos = new BufferedOutputStream(response.getOutputStream());
					byte[] b = new byte[1024];
					int i = 0;
					while ((i = bis.read(b)) > 0) {
						bos.write(b, 0, i);
					}
					bos.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						bis = null;
					}
					if (bos != null) {
						try {
							bos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						bos = null;
					}
				}
			}
		}
	}
}