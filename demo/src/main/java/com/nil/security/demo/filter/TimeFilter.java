package com.nil.security.demo.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//import org.springframework.stereotype.Component;

/**
 * 自定义过滤器
 * @author NIL
 *
 */
//@Component
public class TimeFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("TimeFilter destroy");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("TimeFilter start");
		long start = new Date().getTime();
		chain.doFilter(request, response);
		System.out.println("TimeFilter 耗时：" + (new Date().getTime() - start) + "ms");
		System.out.println("TimeFilter end");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("TimeFilter init");
	}

}
