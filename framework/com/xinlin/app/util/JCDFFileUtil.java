package com.xinlin.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 * 文件操作工具类
 * 
 * 
 * @author jxq
 * @date 2013-10-10
 *
 */
public final class JCDFFileUtil {
	/**
	 * 生成下载文件
	 * 
	 * @param file_name
	 * @param is
	 * @return
	 * @author xuesheng
	 */
	public static File createFileFromInputStream(String file_name,
			InputStream is) {
		File file = new File(file_name);
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			while ((is.read(buffer)) != -1) {
				os.write(buffer);
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 获得WEB-INF/classes根目录
	 * 
	 * @return
	 */
	public static String getClassesPath() {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource("/");
		if (url == null)
			url = Thread.currentThread().getContextClassLoader()
					.getResource("");
		String rootPath = url.getPath().substring(1);
		try {
			rootPath = java.net.URLDecoder.decode(rootPath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rootPath;
	}

	public static String getRootPath() {
		String classPath = JCDFFileUtil.class.getClassLoader().getResource("/")
				.getPath();
		String rootPath = "";
		// windows下
		if ("\\".equals(File.separator)) {
			rootPath = classPath.substring(1,
					classPath.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("/", "\\");
		}
		// linux下
		if ("/".equals(File.separator)) {
			rootPath = classPath.substring(0,
					classPath.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("\\", "/");
		}
		rootPath = rootPath + File.separator;

		try {
			rootPath = java.net.URLDecoder.decode(rootPath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rootPath;
	}

	public static String getWebRealPath(String contextPath) {
		String classPath = JCDFFileUtil.class.getClassLoader().getResource("/")
				.getPath();
		String rootPath = "";
		// windows下
		if ("\\".equals(File.separator)) {
			rootPath = classPath.substring(1,
					classPath.indexOf("/WEB-INF/classes"))
					+ contextPath;
			rootPath = rootPath.replace("/", "\\");
		}
		// linux下
		if ("/".equals(File.separator)) {
			rootPath = classPath.substring(0,
					classPath.indexOf("/WEB-INF/classes"))
					+ contextPath;
			rootPath = rootPath.replace("\\", "/");
		}
		try {
			rootPath = java.net.URLDecoder.decode(rootPath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rootPath;
	}

	public static String getClassPath() {
		String classPath = JCDFFileUtil.class.getClassLoader().getResource("/")
				.getPath();
		String rootPath = "";
		// windows下
		if ("\\".equals(File.separator)) {
			rootPath = classPath.substring(1);
			rootPath = rootPath.replace("/", "\\");
		}
		// linux下
		if ("/".equals(File.separator)) {
			rootPath = classPath;// .substring(0);
			rootPath = rootPath.replace("\\", "/");
		}
		try {
			rootPath = java.net.URLDecoder.decode(rootPath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return rootPath;
	}
}
