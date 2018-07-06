package com.nil.security.core.validate.code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSmsCodeSender implements SmsCodeSender{

	private Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Override
	public void send(String mobile, String Code) {
		logger.info("手机号："+ mobile +"===>短信验证码："+ Code);
	}
	
}
