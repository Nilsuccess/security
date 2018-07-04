package com.nil.security.demo.async;

import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private MockQueue queue;
	@Resource
	private DeferredResultHolder deferredResultHolder;
	
	
	/**
	 * Callable 实现多线程
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/order")
	public Callable<String> order() throws Exception {
		logger.info("主线程开始");
		Callable<String> result = new Callable<String>() {
			@SuppressWarnings("static-access")
			@Override
			public String call() throws Exception {
				logger.info("子线程开始");
				Thread.currentThread().sleep(1000);
				logger.info("子线程结束");
				return "success";
			}
		};
		logger.info("主线程结束");
		return result;
	}
	
	
	@GetMapping("/order2")
	public DeferredResult<String> order2() throws Exception {
		logger.info("主线程开始");
		
		String orderNumber = RandomStringUtils.randomNumeric(9);
		queue.setPlaceOrder(orderNumber);
		DeferredResult<String> result = new DeferredResult<>();
		
		deferredResultHolder.getMap().put(orderNumber, result);
		
		logger.info("主线程结束");
		return result;
	}

}






