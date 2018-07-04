package com.nil.security.demo.code;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nil.security.core.validate.code.ImageCode;
import com.nil.security.core.validate.code.ValidateCodeGenerator;

/**
 * demo项目中自定义的图片验证吗生成器，覆盖core中的图片验证码生成器
 * @author NIL
 *
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator{
	
	private Logger logger = LoggerFactory.getLogger(getClass()); 

	@Override
	public ImageCode createImageCode(HttpServletRequest request) {
		logger.info("demo项目中自定义的图片验证吗生成器，覆盖core中的图片验证码生成器");
		return null;
	}

}
