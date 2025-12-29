package com.supplier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.supplier.dto.ResponseStructure;

@ControllerAdvice
public class GlobelException extends ResponseEntityExceptionHandler{
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>>handleIdNEE(IdNotFoundException exception){
		ResponseStructure<String>response=new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("failure");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotRecordFoundExecption.class)
	public ResponseEntity<ResponseStructure<String>>handleNRFE(NotRecordFoundExecption exception){
		ResponseStructure<String>response=new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(" failure");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
		
		
	}

}
