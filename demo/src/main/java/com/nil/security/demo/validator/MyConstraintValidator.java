package com.nil.security.demo.validator;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nil.security.demo.service.IHelloService;

public class MyConstraintValidator implements ConstraintValidator<MyContraint, Object> {
	
	/**
	 * 可以注入任何的bean
	 */
	@Resource
	private IHelloService HelloServiceImpl;

	@Override
	public void initialize(MyContraint constraintAnnotation) {
		System.out.println("MyConstraintValidator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		System.out.println(value);
		return false;
	}

}
