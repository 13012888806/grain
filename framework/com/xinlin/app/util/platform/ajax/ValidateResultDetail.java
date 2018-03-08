package com.xinlin.app.util.platform.ajax;

public class ValidateResultDetail {
	private String fieldName;
	private int serialNumber;
	private String errorMessage;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	public ValidateResultDetail(String fieldName,int serialNumber,String errorMessage){
		this.fieldName = fieldName;
		this.serialNumber = serialNumber;
		this.errorMessage = errorMessage;
	}
	public ValidateResultDetail(String errorMessage){
		
		this.errorMessage = errorMessage;
	}
}
