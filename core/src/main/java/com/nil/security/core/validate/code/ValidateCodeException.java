package com.nil.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 * @author NIL
 *
 */
public class ValidateCodeException extends AuthenticationException{

	private static final long serialVersionUID = -3356140109871911565L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
