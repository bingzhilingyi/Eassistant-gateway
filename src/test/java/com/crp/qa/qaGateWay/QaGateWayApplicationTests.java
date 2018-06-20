package com.crp.qa.qaGateWay;

import org.junit.Test;
import java.util.*;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaGateWay.service.impl.QaBaseServiceImpl;
import com.crp.qa.qaGateWay.service.impl.QaTreeServiceImpl;
import com.crp.qa.qaGateWay.service.inte.QaTreeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaGateWayApplicationTests {
	
	final Logger LOGGER = LoggerFactory.getLogger(QaGateWayApplicationTests.class);
	
	@Resource(name="qaTreeService")
	QaTreeService qaTreeService;
	
	@Autowired
	QaTreeServiceImpl qaTreeServiceImpl;
	
//	@Autowired
//	TetsAsync tetsAsync;
	

	@Test
	public void contextLoads() {
		
	}
	
	
	@Test
	public void test() {
		System.out.println("main");
//		try {
//			//Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//LOGGER.info("main");
	}
}