package com.nil.security.demo.controller;



import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.nil.security.demo.dto.User;
import com.nil.security.demo.dto.User.UserDetailView;
import com.nil.security.demo.dto.User.UserSimpleView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
//import com.nil.security.demo.exception.UserNotExitExecption;

@RestController
public class UserController {
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	@JsonView(UserSimpleView.class)
	@ApiOperation(value="用户列表获取")
	public List<User> query(@ApiParam(value="用户名") @RequestParam(required=false, value="userName")String userName){
		System.out.println(userName);
		ArrayList<User> list = new ArrayList<>();
		list.add(new User("Jack","123456"));
		list.add(new User("Rose","123456"));
		list.add(new User("Tom","123456"));
		return list;
	}
	/**
	 * 正则限制参数的类型，pl:{id:\\d+}
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/userInfo/{id:\\d+}", method=RequestMethod.GET)
	@JsonView(UserDetailView.class)
	public User userInfo(@PathVariable(value="id") Integer id){
		
//		throw new UserNotExitExecption(id);
//		throw new RuntimeException("User Not Exit");
		
		System.out.println(id);
		User user = new User("tom","123456");
		return user;
		
	}
	
	
	@PostMapping("/userAdd")
	@JsonView(UserSimpleView.class)
	public User userAdd(@Valid @RequestBody User user) {
		/**
		 * 如果方法中不加参数 BindingResult，参数不满足要求的时候，直接将错误信息封装在error中返回前端
		 * 不过由于springBoot的默认一场处理机制，针对浏览器会隐藏一些信息，可以使用RESTer插件访问，
		 * 此时，不进入方法
		 */
//		if (result.hasErrors()) {
//			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//		}
		
		System.out.println(user.getBrithDay());
		System.out.println(user);
		user.setId(1);
		return user;
	}


}
