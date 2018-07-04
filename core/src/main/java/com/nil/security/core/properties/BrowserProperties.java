package com.nil.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo.security.browser")
public class BrowserProperties {

	
	
	public BrowserProperties() {
		//@ConfigurationProperties注解的类在spring容器中是单例的
		System.out.println("BrowserProperties初始化》》》》》》");
	}

	private String loginPage = "/singIn.html";
	
	private LoginType loginType = LoginType.JSON;

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
	
	
	
}
