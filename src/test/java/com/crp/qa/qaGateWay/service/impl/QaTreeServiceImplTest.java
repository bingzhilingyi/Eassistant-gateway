package com.crp.qa.qaGateWay.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaGateWay.domain.dto.QaTreeDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaTreeService;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericListTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaTreeServiceImplTest {
	
	@Resource(name="qaTreeService")
	private QaTreeService qaTreeService;
	
	//测试查找所有根节点
	@Test
	public void findRoot() throws Exception{
		QaGenericListTransfer<QaTreeSimpleDto> dto = qaTreeService.findRoot();
		assertEquals("success",dto.getStatus());
		assertNotNull(dto.getContent());
		assertEquals(4,dto.getContent().size());
	}
	
	//测试根据父级查找子级,有结果
	@Test
	public void findByParentId() throws Exception{
		QaGenericListTransfer<QaTreeSimpleDto> dto = qaTreeService.findByParentId(0);
		assertEquals("success",dto.getStatus());
		assertEquals(4,dto.getContent().size());
	}
	//测试根据父级查找子级 无结果
	@Test
	public void findByParentId2() throws Exception{
		QaGenericListTransfer<QaTreeSimpleDto> dto = qaTreeService.findByParentId(999999);
		assertEquals("success",dto.getStatus());
		assertEquals(0,dto.getContent().size());
	}
	//测试根据父级查找子级 传入null
	@Test
	public void findByParentIdException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericListTransfer<QaTreeSimpleDto> dto = qaTreeService.findByParentId(null);
			fail("expected a Exception to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("传入parentId为null！"));
		}
	}
	
	//测试根据id查询 有结果
	@Test
	public void findById() throws Exception{
		QaGenericBaseTransfer<QaTreeDto> dto = qaTreeService.findById(1);
		assertEquals("success",dto.getStatus());
		assertEquals("SRM",dto.getContent().getTitle());
	}
	
	//测试根据id查询 无结果
	@Test
	public void findById2() throws Exception{
		QaGenericBaseTransfer<QaTreeDto> dto = qaTreeService.findById(-111);
		assertEquals("success",dto.getStatus());
		assertNull(dto.getContent());
	}

	//测试根据id查询 报错
	@Test
	public void findByIdException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericBaseTransfer<QaTreeDto> dto = qaTreeService.findById(null);
			fail("expected a Exception to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("传入id为null！"));
		}
	}
	
	//test find by title,success
	@Test
	public void findByTitle() throws Exception{
		QaGenericBaseTransfer<QaTreeDto> d = qaTreeService.findByTitle("SRM");
		assertEquals("success", d.getStatus());
		assertEquals(new Integer(1),d.getContent().getTreeId());
	}
	
	//test find by title,not exist
	@Test
	public void findByTitle2() throws Exception{
		QaGenericBaseTransfer<QaTreeDto> d = qaTreeService.findByTitle("SRM123123");
		assertEquals("success", d.getStatus());
		assertNull(d.getContent());
	}
	
	//test find paged data by keyword,success
	@Test
	public void findByTitle3() throws Exception{
		List<String> domain = new ArrayList<String>();
		domain.add("SRM");
		QaGenericBaseTransfer<QaTreeDto> p = qaTreeService.findByTitle("SRM",domain);
		assertEquals("success",p.getStatus());
		assertNotNull(p.getContent());	
	}
	//test find paged data by keyword,success
	@Test
	public void findByTitle4() throws Exception{
		List<String> domain = new ArrayList<String>();
		domain.add("PAY");
		QaGenericBaseTransfer<QaTreeDto> p = qaTreeService.findByTitle("SRM",domain);
		assertEquals("success",p.getStatus());
		assertNull(p.getContent());	
	}
	
	//test find by title,exception
	@Test
	public void findByTitleException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericBaseTransfer<QaTreeDto> dto = qaTreeService.findByTitle(null);
			fail("expected a Exception to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("title is null"));
		}
	}
	
	
	
	//test find by title like ,success
	@Test
	public void findPagedByTitleLike() throws Exception{
		QaGenericPagedTransfer<QaTreeSimpleDto> d = qaTreeService.findPagedByTitleLike("SRM",0,10);
		assertEquals("success", d.getStatus());
		assertTrue(d.getContent().size()>=1);
	}
	
	//test find by title like ,not exist
	@Test
	public void findPagedByTitleLike2() throws Exception{
		QaGenericPagedTransfer<QaTreeSimpleDto> d = qaTreeService.findPagedByTitleLike("SRM123123",0,10);
		assertEquals("success", d.getStatus());
		assertTrue(d.getContent().size()==0);
	}
	
	//test find by title like,exception
	@Test
	public void findPagedByTitleLikeException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericPagedTransfer<QaTreeSimpleDto> dto = qaTreeService.findPagedByTitleLike(null,1,10);
			fail("expected a Exception to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("title is null"));
		}
	}
	
	@Test
	public void findChildrenByTitle() throws Exception{
		QaGenericBaseTransfer<QaTreeDto> d = qaTreeService.findChildrenByTitle("SRM");
		assertEquals(new Integer(1),d.getContent().getTreeId());
		assertTrue(d.getContent().getChild().size()>0);
	}
	
	@Test
	public void findChildrenByTitle2() throws Exception{
		QaGenericBaseTransfer<QaTreeDto> d = qaTreeService.findChildrenByTitle("SRM123123");
		assertNull(d.getContent());
	}
	
	@Test
	public void findChildrenByTitleException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericBaseTransfer<QaTreeDto> d = qaTreeService.findChildrenByTitle("");
			fail("expected a Exception to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("title is null"));
		}
	}


	@Test
	public void findByTitleOrKeywordException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericListTransfer<QaTreeSimpleDto> d = qaTreeService.findByTitleOrKeyword("");
			fail("expected a Exception to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("keyword is null"));
		}
	}
	//测试关键字存在时返回有值
	@Test
	public void findByTitleOrKeyword() throws Exception{
		QaGenericListTransfer<QaTreeSimpleDto> d = qaTreeService.findByTitleOrKeyword("srm 供应商管理系统  供应商关系");
		assertNotNull(d.getContent());
		List<QaTreeSimpleDto> content = d.getContent();
		assertEquals("SRM",content.get(0).getTitle());
	}
	//测试关键字不存在时返回无值
	@Test
	public void findByTitleOrKeywordWrong() throws Exception{
		QaGenericListTransfer<QaTreeSimpleDto> d = qaTreeService.findByTitleOrKeyword("devops1");
		List<QaTreeSimpleDto> content = (List<QaTreeSimpleDto>)d.getContent();
		assertEquals(0,content.size());
	}
	
	
	//test find paged data by keyword,success
	@Test
	public void findPagedByTitleOrKeyword() throws Exception{
		QaGenericPagedTransfer<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleOrKeyword("srm 供应商管理系统  供应商关系",0,10);
		assertEquals("success",p.getStatus());
		assertTrue(p.getContent().size()>0);
	}
	//test find paged data by keyword,not exist
	@Test
	public void findPagedByTitleOrKeyword2() throws Exception{
		QaGenericPagedTransfer<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleOrKeyword("devops123",0,10);
		assertEquals("success",p.getStatus());
		assertTrue(p.getContent().size()==0);
		assertTrue(p.getTotalElements()==0);
	}
	//test find paged data by keyword,not exist2
	@Test
	public void findPagedByTitleOrKeyword3() throws Exception{
		QaGenericPagedTransfer<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleOrKeyword("srm 供应商管理系统  供应商关系",1,10);
		assertEquals("success",p.getStatus());
		assertTrue(p.getContent().size()==0);
		assertTrue(p.getTotalElements()>0);
	}
	//test find paged data by keyword,success
	@Test
	public void findPagedByTitleOrKeyword4() throws Exception{
		List<String> domain = new ArrayList<String>();
		domain.add("SRM");
		QaGenericPagedTransfer<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleOrKeyword("srm 供应商管理系统  供应商关系",0,10,domain);
		assertEquals("success",p.getStatus());
		assertTrue(p.getContent().size()>0);	
	}
	
	//test find paged data by keyword,success
	@Test
	public void findPagedByTitleOrKeyword5() throws Exception{
		List<String> domain = new ArrayList<String>();
		domain.add("PAY");
		QaGenericPagedTransfer<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleOrKeyword("srm 供应商管理系统  供应商关系",0,10,domain);
		assertEquals("success",p.getStatus());
		assertTrue(p.getContent().size()==0);	
	}
	//test find paged data by keyword,exception
	@Test
	public void findPagedByTitleOrKeywordException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericPagedTransfer<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleOrKeyword(null,0,10);
			fail("expected a Exception to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("keyword is null"));
		}
	}
	//test find paged data by keyword,exception2
	@Test
	public void findPagedByTitleOrKeywordException2() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericPagedTransfer<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleOrKeyword("srm 供应商管理系统  供应商关系",-1,10);
			fail("expected a Exception to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("current page must not less than zero"));
		}
	}
	
	//test find paged data by keyword,exception3
	@Test
	public void findPagedByTitleOrKeywordException3() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericPagedTransfer<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleOrKeyword("srm 供应商管理系统  供应商关系",0,0);
			fail("expected a Exception to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("page size must not less than 1"));
		}
	}
	
	@Test
	public void findTopRank()throws Exception{
		QaGenericPagedTransfer<QaTreeSimpleDto> p = qaTreeService.findTopRank(50);
		assertEquals(50,p.getContent().size());
		p = qaTreeService.findTopRank(null);
		assertEquals(100,p.getContent().size());
	}
	
	@Test
	public void findTopRank2()throws Exception{
		List<String> domain = new ArrayList<String>();
		domain.add("SRM");
		QaGenericPagedTransfer<QaTreeSimpleDto> p = qaTreeService.findTopRank(50,domain);
		assertEquals(50,p.getContent().size());
	}
	
	@Test
	public void findTopRankException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaGenericPagedTransfer<QaTreeSimpleDto> p = qaTreeService.findTopRank(50,null);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("传入域为空"));
		}
	}
}
