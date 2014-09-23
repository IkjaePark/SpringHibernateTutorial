package com.inpcreations.BlogEntry.utils;

public class RestResponse<T> {
	private T Data;
	private String message;
	private int status;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return Data;
	}

	public void setData(T data) {
		Data = data;
	}
}
