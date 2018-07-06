package com.nil.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nil.security.core.properties.SecurityProperties;

/**
 * 验证码生成Bean配置
 * @author NIL
 *
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name = "validateCodeGenerator")//系统初始化的时候，会先去容器内寻找名字相同的bean，如果没有才创建，否则不创建
	public ValidateCodeGenerator validateCodeGenerator() {
		ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
		imageCodeGenerator.setSecurityProperties(securityProperties);
		return imageCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)//系统初始化的时候，会先去容器内寻找此接口的实现，如果没有才创建，否则不创建
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
}






























