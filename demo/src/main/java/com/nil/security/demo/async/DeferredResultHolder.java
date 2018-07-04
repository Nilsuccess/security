package com.nil.security.demo.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

@Component
public class DeferredResultHolder {
	
	/**
	 * map作为存储订单处理的容器<订单号， 处理结果>
	 */
	private Map<String, DeferredResult<String>> map = new HashMap<>();

	public Map<String, DeferredResult<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, DeferredResult<String>> map) {
		this.map = map;
	}
	
}
