package com.example.spring.webservices.microservice.employee.exception;

public class ExceptionResponse {
    
	private String code;
	private String message;
	
	
	public ExceptionResponse(String code, String message) {
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
