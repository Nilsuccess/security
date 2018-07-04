package com.nil.security.demo.async;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private MockQueue queue;
	
	@Resource
	private DeferredResultHolder deferredResultHolder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		new Thread(() -> {
			while (true) {
				
				if (StringUtils.isNotBlank(queue.getCompleteOrder())) {
					String orderNumber = queue.getCompleteOrder();
					logger.info("返回订单处理结果：" + orderNumber);
					deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
					queue.setCompleteOrder(null);
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		
		
	}
	
	
	
}











