package com.nil.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import com.nil.security.core.authentication.mobile.SmsCodeAuthenticationFilter;
import com.nil.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.nil.security.core.properties.SecurityProperties;
import com.nil.security.core.validate.code.SmsCodeFilter;
import com.nil.security.core.validate.code.ValidateCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * 配置文件
	 */
	@Autowired
	private SecurityProperties securityProperties;
	/**
	 * 数据源【这里是为了让记住我存贮在数据库】
	 */
	@Autowired
	private DataSource dataSource;
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
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer nilSocialSecurityConfig;
	
	
	//security密码加密器
	@Bean
	public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	
	//存储token的配置
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);//项目启动的时候创建表，如果已经表已经存在会报错
		return tokenRepository;
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		ValidateCodeFilter filter = new ValidateCodeFilter();
		filter.setAuthenticationFailureHandler(nilAuthenticationFailureHandler);
		filter.setSecurityProperties(securityProperties);
		filter.afterPropertiesSet();
		
		SmsCodeFilter filter2 = new SmsCodeFilter();
		filter2.setAuthenticationFailureHandler(nilAuthenticationFailureHandler);
		filter2.setSecurityProperties(securityProperties);
		filter2.afterPropertiesSet();
		
		http
			.addFilterBefore(filter2, UsernamePasswordAuthenticationFilter.class)//添加自定义的过滤器【短信验证码】
			.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)//添加自定义的过滤器【验证码】
			.formLogin()										//表单登陆
				.loginPage(securityProperties.getBrowserProperties().getLoginPage())				//设置登陆页面
				.loginProcessingUrl("/authentication/form")			//form表单需要身份认证时，跳转的url[UsernamePasswordAuthenticationFilter]会从这个请求中获取登陆信息并验证
				.successHandler(nilAuthenticationSuccessHandler) 	//自定义的认证成功处理器
				.failureHandler(nilAuthenticationFailureHandler)	//自定义的认证失败处理器
				.and()	
			.apply(nilSocialSecurityConfig)							//social配置类的添加
				.and()
			.rememberMe()											//记住我【免登陆的配置】
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowserProperties().getRememberMeSecond())
				.userDetailsService(userDetailsService)
				.and()
			.authorizeRequests()								//下面都是授权的配置
				.antMatchers(
								"/authentication/require",
								securityProperties.getBrowserProperties().getLoginPage(),
								"/code/*"								//所有的验证码都不拦截
							).permitAll()								//配置不需要认证的url
			.anyRequest()										//对于任何的配置
				.authenticated()									//都需要认证
				.and()
			.csrf().disable()									//关闭csrf防护
			.apply(smsCodeAuthenticationSecurityConfig);		//应用自定义的配置
		
		System.out.println("★★★★★★★★★★");
		
		
		
		
		
//		http.httpBasic()			//Basic登陆
//			.and()
//			.authorizeRequests()	//下面都是授权的配置
//			.anyRequest()			//对于任何的配置
//			.authenticated();		//都需要认证
	}
}

























