package com.nil.security.demo.exception;

/**
 * 自定义异常声明
 * @author NIL
 *
 */
public class UserNotExitExecption extends RuntimeException {

	private static final long serialVersionUID = -5048713109368788335L;
	
	private Integer id;
	
	public UserNotExitExecption (Integer id) {
		super("User Not Exit");
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
