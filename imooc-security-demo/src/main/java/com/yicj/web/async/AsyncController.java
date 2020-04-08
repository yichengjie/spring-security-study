package com.yicj.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {
	private Logger logger = LoggerFactory.getLogger(this.getClass()) ;
	@Autowired
	private MockQueue mockQueue ;
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	@RequestMapping("/orderDeferred")
	public DeferredResult<String> orderDeferred() {
		logger.info("主线程开始");
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		
		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);
		
		logger.info("主线程返回");
		return result ;
	}
	
	
	@RequestMapping("/orderCallable")
	public Callable<String> orderCallable() {
		logger.info("主线程开始");
		Callable<String> result = new Callable<String>() {
			@Override
			public String call() throws Exception {
				logger.info("副线程开始");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				logger.info("副线程返回");
				return "success"  ;
			}
		};
		logger.info("主线程返回");
		return result ;
	}
	
	@RequestMapping("/orderSync")
	public String orderSync() {
		logger.info("主线程开始");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("主线程返回");
		return "success"  ;
	}
	
}
