package com.ane.util;

public class RestResultResponse {
	
	private ResultStatus status;
	private Object result;

	public RestResultResponse(ResultStatus status, Object result) {
		this.status = status;
		this.result = result;
	}

	public ResultStatus getStatus() {
		return status;
	}

	public Object getResult() {
		return result;
	}
	
	
}
