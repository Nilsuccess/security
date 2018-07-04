package com.nil.security.core.validate.code;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 图片验证码过滤器
 * @author NIL
 *
 */
public class ValidateCodeFilter extends OncePerRequestFilter {
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (StringUtils.equals("/authentication/form", request.getRequestURI())
				&& StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
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
		ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, VaildateCodeController.SESSION_KEY);
		//2.获取表单中的验证码
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
		
		//3.比较验证码
		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码不能为空");
		}
		if (codeInSession == null) {
			throw new ValidateCodeException("验证码不存在");
		}
		if (codeInSession.isExpried()) {
			//移除过期的session
			sessionStrategy.removeAttribute(request, VaildateCodeController.SESSION_KEY);
			throw new ValidateCodeException("验证码已经过期");
		}
		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException("验证码不正确");
		}
		sessionStrategy.removeAttribute(request, VaildateCodeController.SESSION_KEY);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}
	
	

}































