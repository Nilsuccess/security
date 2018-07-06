package com.nil.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo.security.validateCode.smsCode")
public class SmsCodeProperties {
	
	
	/**
	 * 图片验证码的位数
	 */
	private int length = 6;
	/**
	 * 图片验证码的过期时间，单位秒
	 */
	private int expireIn = 60;
	/**
	 * 需要验证图片验证码的url
	 */
	private String urls;
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
	public String getUrls() {
		return urls;
	}
	public void setUrls(String urls) {
		this.urls = urls;
	}
	
	
}
