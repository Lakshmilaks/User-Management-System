package com.User.ums.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.User.ums.Exception.UserNotFoundByIdException;


@RestControllerAdvice
public class ApplicationExceotionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> allErrors=ex.getAllErrors();
		
		Map<String, String> errors = new HashMap();
		
		allErrors.forEach(error->{
			FieldError fieldError = (FieldError)error;
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		});
		return structure(HttpStatus.BAD_REQUEST,ex.getMessage(), errors);
	}
	
	private ResponseEntity<Object> structure(HttpStatus status,String message,Object rootCause){
		return new ResponseEntity<Object>(Map.of(
				"status",status.value(),
				"message",message,
				"rootCause",rootCause
				),status);	
	}
	@ExceptionHandler
	public ResponseEntity<Object> handleUserNotFoundById(UserNotFoundByIdException ex){
		return structure(HttpStatus.NOT_FOUND,ex.getMessage(), "User Object with the given Id doesn't exist!!");
	}
	public ResponseEntity<Object> handelRuntime(RuntimeException ex){
		return structure(HttpStatus.BAD_REQUEST, ex.getMessage(), "Runtime exceptioin occured");

	}

}
