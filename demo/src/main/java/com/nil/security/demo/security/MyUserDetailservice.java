package com.nil.security.demo.security;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailservice implements UserDetailsService, SocialUserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private PasswordEncoder passwordEncoder;

	//可以注入userServiceImpl，去数据库获取用户信息
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("表单登陆的用户是：" + username);
		return buildUser(username);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("Social登陆的用户是：" + userId);
		return buildUser(userId);
	}

	private SocialUser buildUser(String userIdOrName) {
		// 根据用户名或者用户Id查询用户信息
		//logger.info("登陆的用户是：" + userIdOrName);
		
		String password = passwordEncoder.encode("123456");
		logger.info("加密密码是：" + password);
		
		//这里的user相当于冲数据库查询出来的
		return new SocialUser(userIdOrName, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

	

}
















































