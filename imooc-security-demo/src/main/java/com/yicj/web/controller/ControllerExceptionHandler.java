package com.yicj.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yicj.exception.UserNotExistException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleUserNotExistException(UserNotExistException ex){
		Map<String,Object> result = new HashMap<>() ;
		result.put("message", ex.getMessage()) ;
		result.put("id", ex.getId()) ;
		return result ;
	}
	

}
