/**
 * huangyue
 * 2018年6月1日
 */
package com.crp.qa.qaGateWay.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaGateWay.domain.dto.QaTreeDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaClientService;
import com.crp.qa.qaGateWay.service.inte.QaSearchHistoryService;
import com.crp.qa.qaGateWay.service.inte.QaTreeService;
import com.crp.qa.qaGateWay.util.exception.QaClientException;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;


/**
 * @author huangyue
 * @date 2018年6月1日 上午11:51:37
 * @ClassName QaClientController
 */
@RestController
@RequestMapping(value="/client")
public class QaClientController extends QaBaseController{
	
	@Resource(name="qaClientService")
	private QaClientService qaClientService;
	
	@Resource(name="qaTreeService")
	private QaTreeService qaTreeService;
	
	@Resource(name="QaSearchHistoryService")
	private QaSearchHistoryService qaSearchHistoryService;
	
	/**
	 * 根据标题查找
	 * @param title
	 * @return
	 * @Date 2018年6月26日
	 * @author huangyue
	 */
	@GetMapping(path="/findByTitle")
	public QaGenericBaseTransfer<QaTreeDto> findByTitle(
			@RequestParam(value="title") String title,
			@Nullable @RequestParam(value="domain") String domain) {
		QaGenericBaseTransfer<QaTreeDto> dto = new QaGenericBaseTransfer<QaTreeDto>();
		try {
			//异步记录查询历史
			qaSearchHistoryService.searchRecord(title);
			//进行查询
			if(domain==null) {
				dto = qaClientService.findByTitle(title);
			}else {
				List<String> domainList = Arrays.asList(domain.split(","));
				dto = qaClientService.findByTitle(title,domainList);
			}
		} catch (Exception e ) {
			returnError(e, dto);
		}
		return dto;
	}

	/**
	 * 查找排名最高的
	 * @param size
	 * @return
	 * @Date 2018年6月26日
	 * @author huangyue
	 */
	@GetMapping(path="/findTopRank")
	public QaGenericPagedTransfer<QaTreeSimpleDto> findTopRank(@RequestParam(value="size") Integer size,
			@Nullable @RequestParam(value="domain") String domain) {
		QaGenericPagedTransfer<QaTreeSimpleDto> dto = new QaGenericPagedTransfer<QaTreeSimpleDto>();
		try {
			//进行查询
			if(domain==null) {
				dto = qaTreeService.findTopRank(size);
			}else {
				List<String> domainList = Arrays.asList(domain.split(","));
				dto = qaTreeService.findTopRank(size,domainList);
			}
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 找到根节点
	 * @return
	 * @Date 2018年6月26日
	 * @author huangyue
	 */
	@GetMapping(path="/findRoot")
	public QaBaseTransfer findRoot() {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.findRoot();
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
}
