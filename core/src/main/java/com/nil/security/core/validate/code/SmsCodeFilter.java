package com.nil.security.core.validate.code;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nil.security.core.properties.SecurityProperties;

/**
 * 短信验证码过滤器
 * @author NIL
 *
 */
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean{
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private SecurityProperties securityProperties;
	
	private Set<String> urls = new HashSet<>();
	
	private AntPathMatcher pathMatcher = new AntPathMatcher();
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		String[] configUrls = StringUtils.split(securityProperties.getValidateCodeProperties().getSmsCode().getUrls(), ",");
		for (String string : configUrls) {
			urls.add(string);
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info(request.getRequestURI()+"====>"+request.getMethod());
		//判断当前url是否需要验证图片验证码
		boolean action = false;
		for (String url : urls) {
			if (pathMatcher.match(url, request.getRequestURI())) {
				action = true;
			}
		}
		if (action) {
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
	}

	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		//1.获取session中的验证码
		ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request, VaildateCodeController.SESSION_KEY_SMS);
		//2.获取表单中的验证码
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");
		
		//3.比较验证码
		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码不能为空");
		}
		if (codeInSession == null) {
			throw new ValidateCodeException("验证码不存在");
		}
		if (codeInSession.isExpried()) {
			//移除过期的session
			sessionStrategy.removeAttribute(request, VaildateCodeController.SESSION_KEY_SMS);
			throw new ValidateCodeException("验证码已经过期");
		}
		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException("验证码不正确");
		}
		sessionStrategy.removeAttribute(request, VaildateCodeController.SESSION_KEY_SMS);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public SessionStrategy getSessionStrategy() {
		return sessionStrategy;
	}

	public void setSessionStrategy(SessionStrategy sessionStrategy) {
		this.sessionStrategy = sessionStrategy;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
	

}































