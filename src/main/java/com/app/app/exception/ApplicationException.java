package com.app.app.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationException extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handelAllException(Exception ex, WebRequest request){
		ExceptionResponse exception = new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ExceptionResponse>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(RecordNotFound.class)
	public ResponseEntity<ExceptionResponse> handelRecordNotFoundException(RecordNotFound ex, WebRequest request){
		ExceptionResponse exception = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
	}
}
