package com.xinlin.app.util.platform.ajax;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ValidateResult {
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_ERROR = 1;
	public static final int CODE_FATAL = 2;
	
	private int code;
	private List<ValidateResultDetail> validateResultDetails;
	private ValidateResultDetail fatal;
	public ValidateResult(){
		this.code = CODE_SUCCESS;
		this.validateResultDetails = new ArrayList<ValidateResultDetail>();
	}
	
	public void addError(String fieldName,int serialNumber,String errorMessage){
		validateResultDetails.add(new ValidateResultDetail(fieldName,serialNumber,errorMessage));
		this.code = CODE_ERROR;
	}
	
	public void setFatal(String fatalMessage){
		this.fatal = new ValidateResultDetail(fatalMessage);
		this.code = CODE_FATAL;
	}
	
	public int getCode(){
		return this.code;
	}
	
	public Iterator<ValidateResultDetail> detailIterator(){
			return 	validateResultDetails.iterator();
	}
	
	public ValidateResultDetail getFatal(){
		return this.fatal;
	}

}
