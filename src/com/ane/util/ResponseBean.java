package com.ane.util;

import java.io.Serializable;

/**
 * 返回JSON数据对象
 * @author Administrator
 *
 */
public class ResponseBean implements Serializable{

	private static final long serialVersionUID = 6049041519552360876L;
	private ResultStatus status;
	private String msg;
	public ResponseBean(){
		
	}
	public ResponseBean(ResultStatus status,String msg){
		this.status = status;
		this.msg = msg;
	}
	public ResultStatus getStatus() {
		return status;
	}
	public void setStatus(ResultStatus status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
