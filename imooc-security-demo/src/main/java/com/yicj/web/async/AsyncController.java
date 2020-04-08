package com.yicj.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {
	private Logger logger = LoggerFactory.getLogger(this.getClass()) ;
	
	@RequestMapping("/order")
	public String order() {
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
