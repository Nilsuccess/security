package com.nil.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码生成器
 * @author NIL
 *
 */
public interface ValidateCodeGenerator {

	ImageCode createImageCode(HttpServletRequest request);
}
