package com.nil.security.core.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo.security.socialProperties")
public class SocialProperties {

	@Autowired
	private QQProperties qqProperties;

	public QQProperties getQqProperties() {
		return qqProperties;
	}

	public void setQqProperties(QQProperties qqProperties) {
		this.qqProperties = qqProperties;
	}
	
	
}
