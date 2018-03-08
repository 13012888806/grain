package com.xinlin.app.base;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinlin.app.util.StaticVar;



/**
 * 文件上传/下载  全局控制器定义
 * 
 * @author jxq
 * @date 2013-10-21
 *
 */

@Controller
@RequestMapping(value = "/xinlinfile")
public class FileLoadController extends BaseController{

	/**
	 * 上传文件到服务器
	 * 同时上传多个文件
	 * 
	 * @param jsonData
	 * @param response
	 */
	
	@RequestMapping(value = "/uploader.do")
	public  void uploadFile(HttpServletResponse response, HttpServletRequest request) {
//		response.setCharacterEncoding("UTF-8");
		String fileName = "";
		String fileStorePath = "";
		
		String repositoryPath = FileUtils.getTempDirectoryPath();
		String uploadPath = "/WEB-INF/upload";
		uploadPath = request.getSession().getServletContext().getRealPath(uploadPath);
		
		
		File up = new File(uploadPath);
		if(!up.exists()){
			up.mkdir();
		}
		
		
		Integer schunk = null;//分割块数
		Integer schunks = null;//总分割数
		String name = null;//文件名
		BufferedOutputStream outputStream=null; 
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024);
				factory.setRepository(new File(repositoryPath));//设置临时目录
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				upload.setSizeMax(StaticVar.MAXIMUM_UPLOAD_SIZE);//设置附件size最大限制值
				List<FileItem> items = upload.parseRequest(request);
				//生成新文件名
				String newFileName = null;
				for (FileItem item : items) {
					if (!item.isFormField()) {// 如果是文件类型
						name = item.getName();// 获得文件名
						newFileName = UUID.randomUUID().toString().replace("-","").concat(".").concat(FilenameUtils.getExtension(name));
						
						//业务处理begin
						fileName = newFileName;
						fileStorePath = uploadPath;
						saveFileNameAndPath(null, newFileName, fileStorePath);
						//业务处理end		
								
						if (name != null) {
							String nFname = newFileName;
							if (schunk != null) {
								nFname = schunk + "_" + name;
							}
							File savedFile = new File(uploadPath, nFname);
							item.write(savedFile);
						}
					} else {
						//判断是否带分割信息
						if (item.getFieldName().equals("chunk")) {
							schunk = Integer.parseInt(item.getString());
						}
						if (item.getFieldName().equals("chunks")) {
							schunks = Integer.parseInt(item.getString());
						}
					}
				}
				
				if (schunk != null && schunk + 1 == schunks) {
					outputStream = new BufferedOutputStream(new FileOutputStream(new File(uploadPath, newFileName)));
					//遍历文件合并
					for (int i = 0; i < schunks; i++) {
						File tempFile=new File(uploadPath,i+"_"+name);
						byte[] bytes=FileUtils.readFileToByteArray(tempFile);  
						outputStream.write(bytes);
						outputStream.flush();
						tempFile.delete();
					}
					outputStream.flush();
				}
				response.getWriter().write("{\"status\":true,\"newName\":\""+newFileName+"\"}");
			} catch (FileUploadException e) {
				e.printStackTrace();
				try {
					response.getWriter().write("{\"status\":false}");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					response.getWriter().write("{\"status\":false}");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}finally{  
	            try {  
	            	if(outputStream!=null)
	            		outputStream.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }   
		}
		
	}

	/**
	 * 上传主页面，模态窗口弹出方式
	 * 在模态窗口上传一个或多个文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/uploadPage.do")
	public String toUploaderPage(HttpServletRequest request,
			HttpServletResponse response) {
		return "public/uploader";
	}
	
	
	/**
	 * 保存后的文件名称，和路径
	 * 作具体业务处理
	 * 由实际运用的业务过程调用
	 * 
	 * 
	 * @param newFileName
	 * @param filePath
	 * @return
	 */
	public Object saveFileNameAndPath(Object target, String newFileName, String filePath){
		return null;
		
	}
	
}
