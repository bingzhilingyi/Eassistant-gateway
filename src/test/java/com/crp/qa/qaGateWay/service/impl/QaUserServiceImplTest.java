package com.crp.qa.qaGateWay.service.impl;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaGateWay.service.inte.QaUserService;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaUserServiceImplTest {
	
	@Resource(name="qaUserService")
	QaUserService qaUserService;

	@Test
	public void findAll() throws Exception{
		QaBaseTransfer d = qaUserService.findAll();
	}

}
