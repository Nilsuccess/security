package com.nil.security.core.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo.security.validateCode")
public class ValidateCodeProperties {
	
	@Autowired
	private ImageCodeProperties imageCode;
	
	@Autowired
	private SmsCodeProperties smsCode;

	public ImageCodeProperties getImageCodeProperties() {
		return imageCode;
	}

	public void setImageCodeProperties(ImageCodeProperties imageCodeProperties) {
		this.imageCode = imageCodeProperties;
	}
	
	public SmsCodeProperties getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(SmsCodeProperties smsCode) {
		this.smsCode = smsCode;
	}

}
