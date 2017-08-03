package com.ane.util;

public class ErrorResult {
	private String code;
	private String message;

	public ErrorResult(String message) {
		this(message, "");
	}

	public ErrorResult(String message, String code) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
