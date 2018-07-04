package com.nil.security.demo.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class TimeAspect {
	
	@Around("execution(* com.nil.security.demo.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("TimeAspect start ");
		long start = new Date().getTime();
		//h获取方法所有的参数
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg--->"+arg);
		}
		//执行被拦截的方法【object就是控制器执行之后的返回值】
		Object object = pjp.proceed();
		System.out.println("TimeAspect 耗时：" + (new Date().getTime() - start) + "ms");
		System.out.println("TimeAspect end ");
		return object;
	}

}
