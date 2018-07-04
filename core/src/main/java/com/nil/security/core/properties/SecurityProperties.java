package com.nil.security.core.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo.security")
public class SecurityProperties {

	@Autowired
	private BrowserProperties browser;
	
	@Autowired
	private ValidateCodeProperties validateCode;

	public BrowserProperties getBrowserProperties() {
		return browser;
	}

	public void setBrowserProperties(BrowserProperties browser) {
		this.browser = browser;
	}

	public ValidateCodeProperties getValidateCodeProperties() {
		return validateCode;
	}

	public void setValidateCodeProperties(ValidateCodeProperties validateCodeProperties) {
		this.validateCode = validateCodeProperties;
	}
	
	
}
