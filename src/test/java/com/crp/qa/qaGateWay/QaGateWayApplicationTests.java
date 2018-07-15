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
import com.crp.qa.qaGateWay.service.inte.QaGroupService;
import com.crp.qa.qaGateWay.service.inte.QaTreeService;
import com.crp.qa.qaGateWay.util.exception.QaGroupException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaPagedTransfer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaGateWayApplicationTests {
	
	final Logger LOGGER = LoggerFactory.getLogger(QaGateWayApplicationTests.class);
	
	@Resource(name="qaGroupService")
	QaGroupService qaGroupService;
	
	@Autowired
	QaTreeServiceImpl qaTreeServiceImpl;
	
//	@Autowired
//	TetsAsync tetsAsync;
	

	@Test
	public void contextLoads() {
		
	}
	
	
	@Test
	public void test() {
		try {
			QaBaseTransfer pg = qaGroupService.findById(11);
			qaGroupService.update(pg.getContent().toString());
		} catch (QaGroupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}