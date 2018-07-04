package com.nil.security.demo.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 * @author NIL
 *
 */
//@Component
public class TimeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("TimeInterceptor start");
		request.setAttribute("start", new Date().getTime());
		//获取类名
		System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
		//获取方法
		System.out.println(((HandlerMethod) handler).getMethod().getName());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		long start = (long)request.getAttribute("start");
		System.out.println("TimeInterceptor 耗时：" + (new Date().getTime() - start) + "ms");
		System.out.println("TimeInterceptor end");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		long start = (long)request.getAttribute("start");
		System.out.println("TimeInterceptor afterCompletion 耗时：" + (new Date().getTime() - start) + "ms");
		System.out.println("ex.getMessage:"+ ex);
	}

}
