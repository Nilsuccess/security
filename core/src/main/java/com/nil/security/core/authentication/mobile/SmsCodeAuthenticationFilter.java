package com.nil.security.core.authentication.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

/**
 * 自定义的SmsCodeAuthenticationFilter
 * @author NIL
 *
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter{


	public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";
	/**
	 * request中的请求参数是什么
	 */
	private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;
	/**
	 * 当前过滤器只处理post请求
	 */
	private boolean postOnly = true;

	/**
	 * 确定要处理的请求对应的url和请求方式
	 */
	public SmsCodeAuthenticationFilter() {
		super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
	}


	/**
	 * 认证
	 */
	public Authentication attemptAuthentication(HttpServletRequest request,	HttpServletResponse response) throws AuthenticationException {
		//判断当前的请求方式是不是post
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		//获取手机号
		String mobile = obtainMobile(request);
		if (mobile == null) {
			mobile = "";
		}
		mobile = mobile.trim();

		//构建登陆前的token
		SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

		//将请求的信息设置到token中
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);//这里就是调用AuthenticationManager
	}

	/**
	 * 从request中获取手机号
	 * @param request
	 * @return
	 */
	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}

	
	protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	
	public void setUsernameParameter(String mobileParameter) {	
		Assert.hasText(mobileParameter, "Username parameter must not be empty or null");
		this.mobileParameter = mobileParameter;
	}

	
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getMobileParameter() {
		return mobileParameter;
	}

}
