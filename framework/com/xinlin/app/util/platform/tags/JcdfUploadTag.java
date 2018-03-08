package com.xinlin.app.util.platform.tags;

/**
 * JSP自定义标签实现
 * 通用上传标签定义
 * 
 * 
 * @author Administrator
 *
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class JcdfUploadTag extends BodyTagSupport {
	
	private static final String IS_LOCAL_FILE = "true";
	private static final String PAGE_FUNCTION_PREFIX= "<script type='text/javascript'> "
				+"function makerUpload(chunk){"
				+"Uploader(chunk,function(files){"
				+" if(files && files.length>0){ "
				+"$('#" ;
			//+messageDiv
	private static final String PAGE_FUNCTION_SUFFIX="').text('成功上传：'+files.join(','));"
				+" } "
				+" }); "
				+"}"
				+"</script>"
				+"<a class='easyui-linkbutton' href='javascript:makerUpload(false)'>" ;

				
	
	private String messageDiv;
	
	private String localFile;
	
	private HttpServletRequest reqeust;
	private JspWriter out;

	public void init() {
		reqeust = (HttpServletRequest) pageContext.getRequest();
		out = pageContext.getOut();
	}

	@Override
	public int doStartTag() throws JspException {
		init();
		try {
			StringBuffer output = new StringBuffer(200);
			/*output.append("<script type='text/javascript'> ")
			.append("function makerUpload(chunk){")
		 	.append("  Uploader(chunk,function(files){")
 			.append(" if(files && files.length>0){ ")
			.append("$('#"+messageDiv+"').text('成功上传：'+files.join(','));")
			.append(" } ")
			.append(" }); ")
			.append("}")
			.append("</script>")
			.append("<a class='easyui-linkbutton' href='javascript:makerUpload(false)'>");*/
			output.append(PAGE_FUNCTION_PREFIX)
				  .append(messageDiv)
				  .append(PAGE_FUNCTION_SUFFIX);
			
			
			/*out.println("function _upload(f){");
			out.println("alert(f.id);");
			out.println("alert(document.getElementById(f.id).value.length);");
			out.println("}");*/
			out.print(output.toString());
			//long fileLabel = System.currentTimeMillis();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.EVAL_BODY_INCLUDE;
	}

	// 设置当前标签体
	@Override
	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
		System.out.println("setBodyContent...");
	}

	// 需要初始化bodyContent
	@Override
	public void doInitBody() throws JspException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("init.....");
	}

	@Override
	public int doAfterBody() throws JspException {
		/*if (count >= 1) {
			try {
				out.print("</input></form>");
				
				out.println(count);
				out.println("<Br>");
				
			} catch (IOException e) {
				e.printStackTrace();
			}

			count--;*/
			//return this.EVAL_BODY_AGAIN;
			return this.SKIP_BODY;
		/*} else {
			return this.SKIP_BODY;
		}*/
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			out.print("</a><div id='"+messageDiv+"'></div>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.EVAL_PAGE;
	}


	// 必须实现setXX()方法
	public void setMessageDiv(String messageDiv) {
		this.messageDiv = messageDiv;
	}

	public void setLocalFile(String localFile) {
		this.localFile = localFile;
	}
	
}
