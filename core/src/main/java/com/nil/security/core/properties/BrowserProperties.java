package com.nil.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo.security.browser")
public class BrowserProperties {

	
	
	

	/**
	 * 配置默认的登陆页
	 */
	private String loginPage = "/singIn.html";
	
	/**
	 * 返回数据的格式
	 */
	private LoginType loginType = LoginType.JSON;
	/**
	 * 记住我的时间，也就是tooken的有效期，单位秒
	 */
	private int rememberMeSecond;

	public BrowserProperties() {
		//@ConfigurationProperties注解的类在spring容器中是单例的
		System.out.println("BrowserProperties初始化》》》》》》");
	}
	
	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		System.out.println("loginPage赋值::::::::::"+loginPage);
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSecond() {
		return rememberMeSecond;
	}

	public void setRememberMeSecond(int rememberMeSecond) {
		this.rememberMeSecond = rememberMeSecond;
	}
	
	
	
}
