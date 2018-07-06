package com.nil.security.core.authentication.mobile;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * 自定义的SmsCodeAuthenticationToken
 * @author NIL
 *
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken{
	


	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	/**
	 * 存放认证信息，登陆前存放手机号，登录后存放认证后的信息
	 */
	private final Object principal;
	/**
	 * 在UsernamePasswordAuthenticationToken中存放密码，此处没有用
	 */
	//	private Object credentials;

	
	/**
	 * 登陆前的构造
	 *
	 */
	public SmsCodeAuthenticationToken(String mobile) {
		super(null);
		this.principal = mobile;
		setAuthenticated(false);//没有登陆的状态
	}

	/**
	 * 登陆后的构造
	 *
	 * @param principal
	 * @param credentials
	 * @param authorities
	 */
	public SmsCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		super.setAuthenticated(true); // must use super, as we override
	}

	// ~ Methods
	// ========================================================================================================

	public Object getCredentials() {
		return null;
	}

	public Object getPrincipal() {
		return this.principal;
	}

	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
	}

}
