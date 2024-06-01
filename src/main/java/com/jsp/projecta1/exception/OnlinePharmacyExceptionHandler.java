package com.jsp.projecta1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.projecta1.util.ResponseStructure;
@RestControllerAdvice
public class OnlinePharmacyExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleAdminIdNotFoundException(AdminIdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("ADMIN ID IS NOT FOUND");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleAdminEmailFoundException(AdminEmailInvalidException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("ADMIN Email IS invalid");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleAdminPasswordnotFoundException(ADminPasswordInvalidException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("ADMIN Password IS invalid");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}
