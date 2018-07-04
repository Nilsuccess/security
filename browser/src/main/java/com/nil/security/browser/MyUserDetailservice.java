package com.nil.security.browser;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailservice implements UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private PasswordEncoder passwordEncoder;

	//可以注入userServiceImpl，去数据库获取用户信息
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 根据用户名查询用户信息
		logger.info("登陆的用户是：" + username);
		
		String password = passwordEncoder.encode("123456");
		logger.info("加密密码是：" + password);
		
		//这里的user相当于冲数据库查询出来的
		User user = new User(username, password, false, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//		new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		return user;
	}

}
















































