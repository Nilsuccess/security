package com.nil.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo.security.validateCode.imageCode")
public class ImageCodeProperties {
	
	/**
	 * 图片验证码的宽度
	 */
	private int width = 67;
	/**
	 * 图片验证码的高度
	 */
	private int height = 23;
	/**
	 * 图片验证码的位数
	 */
	private int length = 4;
	/**
	 * 图片验证码的过期时间，单位秒
	 */
	private int expireIn = 60;
	/**
	 * 需要验证图片验证码的url
	 */
	private String urls;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
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
