package com.xinlin.app.util.platform.ajax;

import java.io.OutputStream;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.xinlin.app.util.JCDFXmlUtil;

public class  XmlResponse {
	private static Log log = LogFactory.getLog(XmlResponse.class);
	
	public final static String CODE_SUCCESS = ResponseConstant.CODE_SUCCESS;
	public final static String CODE_ERROR = ResponseConstant.CODE_ERROR;
	public final static String CODE_FATAL = ResponseConstant.CODE_FATAL;
	public final static String CODE_EXCEPTION= ResponseConstant.CODE_EXCEPTION;
	public final static String CODE_TIMEOUT = ResponseConstant.CODE_TIMEOUT;
	
	public final static String TAG$ROOT = "ajax";
	public final static String TAG$CODE = "code";
	public final static String TAG$ERRORS = "errors";
	public final static String TAG$ERROR = "error";
	public final static String TAG$FIELD = "field";
	public final static String TAG$NO = "no";
	public final static String TAG$MESSAGE = "message";
	public final static String TAG$FATAL = "fatal";
	public final static String TAG$EXCEPTION = "exception";
	public final static String TAG$BEANS = "beans";

	
	private Document dom;
	private int count=0;
	
	public XmlResponse(){
		this.dom = DocumentHelper.createDocument();
		Element rootElement = dom.addElement(TAG$ROOT);
		Element codeElement = rootElement.addElement(TAG$CODE);
		codeElement.addText(CODE_SUCCESS);
	}
	
	public XmlResponse(ValidateResult vr){
		this.dom = DocumentHelper.createDocument();
		Element rootElement = dom.addElement(TAG$ROOT);
		Element codeElement = rootElement.addElement(TAG$CODE);
		if(vr.getCode()==vr.CODE_SUCCESS){
			codeElement.addText(CODE_SUCCESS);
		}else if(vr.getCode() == vr.CODE_FATAL){
			codeElement.addText(CODE_FATAL);
			Element fatalElement = rootElement.addElement(TAG$FATAL);
			fatalElement.addText(vr.getFatal().getErrorMessage());
		}else{
			codeElement.addText(CODE_ERROR);
			Element errorsElement = rootElement.addElement(TAG$ERRORS);
			Iterator<ValidateResultDetail> errors = vr.detailIterator();
			while(errors.hasNext()){
				ValidateResultDetail detail = errors.next();
				Element errorElement = errorsElement.addElement(TAG$ERROR);
				errorElement.addElement(TAG$FIELD).addText(detail.getFieldName());
				errorElement.addElement(TAG$NO).addText(detail.getSerialNumber()+"");
				errorElement.addElement(TAG$MESSAGE).addText(detail.getErrorMessage());
			}
		}
	}
	
	public boolean isValid(){
		return this.dom.getRootElement().selectSingleNode(TAG$CODE).getText().equals(CODE_SUCCESS);
	}
	
	private void setCode(String code){
		
		this.dom.getRootElement().selectSingleNode(TAG$CODE).setText(code);
	}
	
	public void setException(String exception){
		this.setCode(CODE_EXCEPTION);
		Node exceptionElement = this.dom.getRootElement().selectSingleNode(TAG$EXCEPTION);
		if(exceptionElement==null){
			exceptionElement = this.dom.getRootElement().addElement(TAG$EXCEPTION);
		}
		exceptionElement.setText(exception);
	}
	
	public void setFatal(String fatal){
		this.setCode(CODE_FATAL);
		Node exceptionElement = this.dom.getRootElement().selectSingleNode(TAG$FATAL);
		if(exceptionElement==null){
			exceptionElement = this.dom.getRootElement().addElement(TAG$FATAL);
		}
		exceptionElement.setText(fatal);
	}
	
	public void setTimeout(){
		this.setCode(CODE_TIMEOUT);
	}
	
	public void addBean(Object obj){
		try {
			Node beansElement = this.dom.getRootElement().selectSingleNode(TAG$BEANS);
			if(beansElement==null){
				beansElement = this.dom.getRootElement().addElement(TAG$BEANS);
			}
			
			
			
			StringReader reader = new StringReader(JCDFXmlUtil.toXml(obj));
			SAXReader saxReader = new SAXReader();
			Document odom = saxReader.read(reader);
			Element oroot = odom.getRootElement();
			odom.remove(oroot);
			
			((Element)beansElement).add(oroot);
			
			
		} catch (Exception e) {
			log.debug("addBean()",e);
			this.setException(e.getMessage());
		}
		
	}
	
	public void addBeans(List list){
		Iterator iteratot = list.iterator();
		while(iteratot.hasNext()){
			this.addBean(iteratot.next());
		}
	}
	
	public void outputXml(OutputStream out)throws Exception{
		OutputFormat format = new OutputFormat();
		format.setEncoding("UTF-8");
		format.setNewlines(true);
		format.setTrimText(true);
		XMLWriter writer = new XMLWriter(out,format);
		writer.write(dom);
	}
	
	public static void main(String[] args)throws Exception{
		XmlResponse ajax = new XmlResponse();
	
		ajax.outputXml(System.out);
	}
}
