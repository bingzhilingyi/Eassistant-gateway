package com.crp.qa.qaGateWay.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaGateWay.domain.dto.QaTreeDto;
import com.crp.qa.qaGateWay.service.inte.QaClientService;
import com.crp.qa.qaGateWay.util.exception.QaClientException;
import com.crp.qa.qaGateWay.util.transfer.QaGenericBaseTransfer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaClientServiceImplTest {
	
	@Resource(name="qaClientService")
	QaClientService qaClientService;

	@Test
	public void testFindByTitle() throws Exception{
		QaGenericBaseTransfer<QaTreeDto> d = qaClientService.findByTitle("SRM");
		assertEquals(new Integer(1),d.getContent().getTreeId());
		assertTrue(d.getContent().getChild().size()>0);
	}
	
	@Test
	public void testFindByTitle2() throws Exception{
		QaGenericBaseTransfer<QaTreeDto> d = qaClientService.findByTitle(" S R M ");
		assertTrue(d.getContent().getChild().size()>0);
	}
	
	@Test
	public void testFindByTitle3() throws Exception{
		QaGenericBaseTransfer<QaTreeDto> d = qaClientService.findByTitle("srm 供应商管理 关系");
		assertTrue(d.getContent().getChild().size()>0);
	}
	
	@Test
	public void testFindByTitle4() throws Exception{
		QaGenericBaseTransfer<QaTreeDto> d = qaClientService.findByTitle("testKeyword1");
		assertTrue(d.getContent().getChild().size()==0);
	}
	
	@Test
	public void testFindByTitleException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericBaseTransfer<QaTreeDto> d = qaClientService.findByTitle("");
			fail("expected a QaClientException to be throwed");
		}catch(QaClientException e) {
			assertThat(e.getMessage(),is("输入的title为空！"));
		}
	}

	@Test
	@Ignore
	public void testFindTopRank() {
		fail("Not yet implemented");
	}

}
