package com.nil.security.demo.dto;

import java.sql.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.nil.security.demo.validator.MyContraint;

import io.swagger.annotations.ApiModelProperty;

public class User {
	
	//jsonView的接口
	public interface UserSimpleView {};
	//继承的目的就是将UserSimpleView的内容在UserDetailView显示的时候也显示出来
	public interface UserDetailView extends UserSimpleView{};
	
	private Integer id;
	
	/**
	 * 自定义注解【valid】在出错误(也就是验证不通过)的时候将此信息存储在error中
	 */
	@MyContraint(message="自定义验证注解")
	@ApiModelProperty(value="用户名")
	private String userName;
	
	@NotBlank(message="密码不能为空")
	@ApiModelProperty(value="密码")
	private String passWord;
	
	@ApiModelProperty(value="生日")
	private Date brithDay;
	
	public User() {
	}

	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}

	@JsonView(UserSimpleView.class)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonView(UserDetailView.class)
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	@JsonView(UserSimpleView.class)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonView(UserSimpleView.class)
	public Date getBrithDay() {
		return brithDay;
	}

	public void setBrithDay(Date brithDay) {
		this.brithDay = brithDay;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", brithDay=" + brithDay + "]";
	}

	

	
	
	
}
