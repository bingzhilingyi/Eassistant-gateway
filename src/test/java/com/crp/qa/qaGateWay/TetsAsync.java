package com.crp.qa.qaGateWay;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TetsAsync{

	
	@Async
	public void testasync() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//LOGGER.info("async");
		System.out.println("async");
	}
	
}
