package com.nil.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码生成器
 * @author NIL
 *
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成图片验证码
	 * @param request
	 * @return
	 */
	ValidateCode createImageCode(HttpServletRequest request);
	
	/**
	 * 生成短信验证码
	 * @param request
	 * @return
	 */
	ValidateCode createSmsCode(HttpServletRequest request);
}
