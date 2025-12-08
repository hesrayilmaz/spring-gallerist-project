package com.esrayilmaz.handler;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.esrayilmaz.exception.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//baseexception'ları yakalaması için
	@ExceptionHandler(value = {BaseException.class})
	public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request) {
		return ResponseEntity.badRequest().body(createApiError(ex.getMessage(), request));
	}
	
	//sping validation'ın fırlattığı hataları yakalamak için
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<ApiError<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		Map<String, List<String>> errorMap = new HashMap<>();
		for (ObjectError objError : ex.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError)objError).getField();
			
			if(errorMap.containsKey(fieldName)) {
				errorMap.put(fieldName, addValue(errorMap.get(fieldName), objError.getDefaultMessage()));
			}
			else {
				errorMap.put(fieldName, addValue(new ArrayList<>(), objError.getDefaultMessage()));
			}
		}
		
		return ResponseEntity.badRequest().body(createApiError(ex.getMessage(), request));
	}
	
	private List<String> addValue(List<String> list, String newValue) {
		list.add(newValue);
		return list;
	}
	
	public <E> ApiError<E> createApiError(E message, WebRequest request){
		ApiError<E> apiError = new ApiError<>();
		apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		Exception<E> exception = new Exception<>();
		exception.setPath(request.getDescription(false).substring(4)); //gelen response uri="..." şeklinde olacağından ilk 4 karakteri istemiyoruz
		exception.setCreatedTime(new Date());
		exception.setHostName(getHostName());
		exception.setMessage(message);
		
		apiError.setException(exception);
		return apiError;
	}
	
	private String getHostName() {
		try {
			return Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
