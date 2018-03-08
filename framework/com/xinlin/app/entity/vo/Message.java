package com.xinlin.app.entity.vo;

/**
 * 操作结果消息
 * 
 * @author Js
 * @date 2013-04-09
 */
public class Message {

	/**操作结果*/
	private boolean result;
	/**操作结果消息*/
	private String msg;
	/**其他需要传到前台的信息*/
	private Object param;
	
	public Message(boolean result, String msg) {
		this.result = result;
		this.msg = msg;
	}
	
	public Message(boolean result, String msg, Object param) {
		this.result = result;
		this.msg = msg;
		this.param = param;
	}
	
	public Message(String msg) {
		this.msg = msg;
	}

	public Message(boolean result) {
		this.result = result;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}
}
