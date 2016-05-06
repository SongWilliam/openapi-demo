package main.java.com.shinemo.openapi.domain;

import java.io.Serializable;

public class Result<T> implements Serializable{

	private static final long serialVersionUID = 4381058511101354629L;
	
	private Integer status;
	
	private T data;
	
	private String message;
	
	public Result(Integer status, T data, String message){
		this.data = data;
		this.status = status;
		this.message = message;
	}
	
	public static <T> Result<T> buildSuccessResult(T data){
		return new Result<T>(200, data, null);
	}
	
	public static <T> Result<T> buildFailedResult(Integer status, String message){
		return new Result<T>(status, null, message);
	}
	
	public static <T> ResultBuilder<T> builder(T data){
		return new ResultBuilder<T>(data);
	}
	
	public Result(ResultBuilder<T> rb){
		this.status = rb.status;
		this.data = rb.data;
		
	}
	
	public static class ResultBuilder<T>{
		
		private Integer status;
		
		private T data;
		
		private String message;
		
		public ResultBuilder(T data){
			this.data = data;
		}
		
		public  ResultBuilder<T> status(Integer status){
			this.status = status;
			return this;
		}
		public ResultBuilder<T> data(T data){
			this.data = data;
			return this;
		}
		
		public ResultBuilder<T> message(String message){
			this.message = message;
			return this;
		}
		
		public Result<T> build(){
			return new Result<T>(this);
		}
		
	}
	
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
