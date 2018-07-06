package com.nil.security.core.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.nil.security.core.properties.SecurityProperties;
import com.nil.security.core.social.qq.connect.QQConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix="demo.security.socialProperties.qqProperties",name="app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter{
	
	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		
		return new QQConnectionFactory(
					securityProperties.getSocialProperties().getQqProperties().getProviderId(), 
					securityProperties.getSocialProperties().getQqProperties().getAppId(), 
					securityProperties.getSocialProperties().getQqProperties().getAppSecret()
				);
	}

	
}



























