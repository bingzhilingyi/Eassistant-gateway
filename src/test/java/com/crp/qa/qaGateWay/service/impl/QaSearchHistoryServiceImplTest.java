package com.crp.qa.qaGateWay.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaGateWay.domain.dto.QaSearchCountDto;
import com.crp.qa.qaGateWay.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaGateWay.service.inte.QaSearchHistoryService;
import com.crp.qa.qaGateWay.util.exception.QaSearchHistoryException;
import com.crp.qa.qaGateWay.util.transfer.QaGenericListTransfer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaSearchHistoryServiceImplTest {
	
	@Resource(name="QaSearchHistoryService")
	QaSearchHistoryService qaSearchHistoryService;
	
	DateFormat sdf  = new SimpleDateFormat("yyyy-mm-dd");
	
	private void setFail() {
		fail("excepted a QaSearchHistoryException to be throwed ");
	}

	@Test
	public void findTopRank() throws Exception{
		QaGenericListTransfer<QaSearchHistoryDto> d = qaSearchHistoryService.findTopRank(20);
		assertEquals(20,d.getContent().size());
	}
	
	@Test
	public void findTopRank2() throws Exception{
		QaGenericListTransfer<QaSearchHistoryDto> d = qaSearchHistoryService.findTopRank(null);
		assertEquals(10,d.getContent().size());
	}
	
	@Test
	public void findTopRankException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericListTransfer<QaSearchHistoryDto> d = qaSearchHistoryService.findTopRank(0);
			setFail();
		}catch(QaSearchHistoryException e) {
			assertThat(e.getMessage(),is("传入数值小于1！"));
		}
	}

	@Test
	public void findCount()throws Exception{
		Date from = sdf.parse("1000-01-01");
		Date to = sdf.parse("1000-01-05");
		QaGenericListTransfer<QaSearchCountDto> d = qaSearchHistoryService.findCount(from,to);
		assertEquals(5,d.getContent().size());
	}
}
