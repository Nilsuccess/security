package com.nil.security.demo.config;

import java.util.ArrayList;
import java.util.List;

//import javax.annotation.Resource;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nil.security.demo.filter.TimeFilter;
//import com.nil.security.demo.interceptor.TimeInterceptor;

/**
 * filter 的配置文件
 * @author NIL
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
//	@Resource
//	private TimeInterceptor timeInterceptor;
	
	/**
	 * 异步支持[拦截器]
	 */
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		super.configureAsyncSupport(configurer);
		//异步请求拦截器的注册
//		configurer.registerCallableInterceptors(interceptors)
//		configurer.registerDeferredResultInterceptors(interceptors)
		//异步请求的超时时间
//		configurer.setDefaultTimeout(timeout)
		//设置线程池
//		configurer.setTaskExecutor(taskExecutor)
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(timeInterceptor);
	}
	
	
//	@Bean
	public FilterRegistrationBean initTimeFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		filterRegistrationBean.setFilter(timeFilter);
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		return filterRegistrationBean;
	}

}
