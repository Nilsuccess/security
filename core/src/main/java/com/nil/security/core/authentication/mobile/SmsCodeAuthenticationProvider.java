package com.nil.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 自定义smsCode 的校验逻辑
 * @author NIL
 *
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
	
	private UserDetailsService userDetailsService;

	/**
	 * 通过authentication中的用户信息和userDetaileService获取的用户信息重新组装user
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//获取token
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken)authentication;
		//根据token中的信息查询用户信息
		UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
		//判断用户信息是否为空，为空校验失败
		if (user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		//创建登陆认证成功之后的token
		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}

	/**
	 * 判断当前参数【authentication】是不是SmsCodeAuthenticationToken的类型
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	
}



























