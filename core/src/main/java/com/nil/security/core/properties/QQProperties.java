package com.nil.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo.security.socialProperties.qqProperties")
public class QQProperties extends SocialProperties{

	private String providerId = "qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
	
}
