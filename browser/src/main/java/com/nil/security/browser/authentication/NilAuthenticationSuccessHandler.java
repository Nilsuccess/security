package com.nil.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nil.security.core.properties.LoginType;
import com.nil.security.core.properties.SecurityProperties;

@Component("nilAuthenticationSuccessHandler")
public class NilAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 登陆成功之后会被调用
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		logger.info("登陆成功");
		//如果请求的方式是json，
		if (LoginType.JSON.equals(securityProperties.getBrowserProperties().getLoginType())) {
			response.setContentType("application/json:charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		} else {//如果是同步请求就调用父类的方法（跳转）
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}

































