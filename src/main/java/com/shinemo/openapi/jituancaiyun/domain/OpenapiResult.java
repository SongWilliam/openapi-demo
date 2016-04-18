package com.shinemo.openapi.jituancaiyun.domain;

public class OpenapiResult<T> {

	private Integer status;
	
	private T data;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	} 
}
