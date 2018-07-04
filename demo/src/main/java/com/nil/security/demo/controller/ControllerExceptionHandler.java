package com.nil.security.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nil.security.demo.exception.UserNotExitExecption;

/**
 * 异常处理的控制器 [其他控制器抛出异常时触发]
 * @author NIL
 *
 */
@ControllerAdvice//注解的作用就是专门捕获控制器抛出的异常
public class ControllerExceptionHandler {
	
	@ExceptionHandler(UserNotExitExecption.class)//制定要处理的捕获的异常类
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleUserNotExitException (UserNotExitExecption e) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("id", e.getId());
		result.put("message", e.getMessage());
		return result;
	}

}
