package com.nil.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nil.security.core.properties.SecurityProperties;
import com.nil.security.core.validate.code.ValidateCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * 自定义的认证成功处理器
	 */
	@Autowired
	private AuthenticationSuccessHandler nilAuthenticationSuccessHandler;
	
	/**
	 * 自定义的认证失败处理器
	 */
	@Autowired
	private AuthenticationFailureHandler nilAuthenticationFailureHandler;
	
	
	//security密码加密器
	@Bean
	public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		ValidateCodeFilter filter = new ValidateCodeFilter();
		filter.setAuthenticationFailureHandler(nilAuthenticationFailureHandler);
		
		http//.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)//添加自定义的过滤器【验证码】
			.formLogin()										//表单登陆
			.loginPage(securityProperties.getBrowserProperties().getLoginPage())				//设置登陆页面
			.loginProcessingUrl("/authentication/form")			//form表单需要身份认证时，跳转的url
//			.successHandler(nilAuthenticationSuccessHandler) 	//自定义的认证成功处理器
//			.failureHandler(nilAuthenticationFailureHandler)	//自定义的认证失败处理器
			
			.and()
			.authorizeRequests()								//下面都是授权的配置
			.antMatchers(
							"/authentication/require",
							securityProperties.getBrowserProperties().getLoginPage(),
							"/code/image"
						).permitAll()								//配置不需要认证的url
			.anyRequest()										//对于任何的配置
			.authenticated()									//都需要认证
			.and()
			.csrf().disable();									//关闭csrf防护
		
		System.out.println("★★★★★★★★★★");
		
		
		
		
		
//		http.httpBasic()			//Basic登陆
//			.and()
//			.authorizeRequests()	//下面都是授权的配置
//			.anyRequest()			//对于任何的配置
//			.authenticated();		//都需要认证
	}
}

























