package com.nil.security.core.validate.code;

import java.time.LocalDateTime;


/**
 * 短信验证码封装类
 * @author NIL
 *
 */
public class ValidateCode {
	
	/**
	 * 验证码
	 */
	private String code;
	
	/**
	 * 过期时间
	 */
	private LocalDateTime exprieTime;

	public ValidateCode(String code, LocalDateTime exprieTime) {
		this.code = code;
		this.exprieTime = exprieTime;
	}
	
	/**
	 * 构造，传入的过期时间是秒
	 * @param image
	 * @param code
	 * @param exprieSecond
	 */
	public ValidateCode(String code, int exprieSecond) {
		this.code = code;
		this.exprieTime = LocalDateTime.now().plusSeconds(exprieSecond);
	}
	
	/**
	 * 判断验证码是否过期
	 * @return
	 */
	public boolean isExpried () {
		return LocalDateTime.now().isAfter(exprieTime);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExprieTime() {
		return exprieTime;
	}

	public void setExprieTime(LocalDateTime exprieTime) {
		this.exprieTime = exprieTime;
	}
	
	
}
