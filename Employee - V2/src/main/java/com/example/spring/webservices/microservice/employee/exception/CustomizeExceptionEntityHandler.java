package com.example.spring.webservices.microservice.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.spring.webservices.microservice.employee.EmployeeNotFoundException;

@ControllerAdvice
@RestController
public class CustomizeExceptionEntityHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request)  {
			ExceptionResponse exceptionResponse = new ExceptionResponse("Err001", ex.getMessage());
			return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<Object> handleEmployeeNotFoundExceptions(EmployeeNotFoundException ex, WebRequest request)  {
			ExceptionResponse exceptionResponse = new ExceptionResponse("Err004", ex.getMessage());
			return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request)  {
			ExceptionResponse exceptionResponse = new ExceptionResponse("Err004", ex.getMessage());
			return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * @ExceptionHandler(Exception.class) public final ResponseEntity<Object>
	 * handleEmployeeNotFoundExceptions(EmployeeNotFoundException ex, WebRequest
	 * request) { ExceptionResponse exceptionResponse = new
	 * ExceptionResponse("Err004", ex.getMessage()); return new
	 * ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND); }
	 */
}
