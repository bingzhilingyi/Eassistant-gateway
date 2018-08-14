package com.crp.qa.qaGateWay.service.impl;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaGateWay.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaGateWay.service.inte.QaSearchNoResultService;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaSearchNoResultServiceImplTest {

	@Resource(name="qaSearchNoResultService")
	QaSearchNoResultService qaSearchNoResultService;
	
	@Test
	public void findPagedAll()throws Exception{
		QaGenericPagedTransfer<QaSearchNoResultDto> d = qaSearchNoResultService.findPagedAll(0, 20);
		assertTrue(d.getContent().size()>0);
	}
}
