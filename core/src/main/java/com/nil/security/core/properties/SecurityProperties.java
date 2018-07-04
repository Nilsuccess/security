package com.nil.security.core.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo.security")
public class SecurityProperties {

	@Autowired
	private BrowserProperties browser;

	public BrowserProperties getBrowserProperties() {
		return browser;
	}

	public void setBrowserProperties(BrowserProperties browser) {
		this.browser = browser;
	}
	
	
}
