/**
 * huangyue
 * 2018年6月1日
 */
package com.crp.qa.qaGateWay.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaGateWay.domain.dto.QaTreeDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaClientService;
import com.crp.qa.qaGateWay.service.inte.QaTreeService;
import com.crp.qa.qaGateWay.util.exception.QaClientException;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
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
	
	@GetMapping(path="/findByTitle")
	public QaGenericBaseTransfer<QaTreeDto> findByTitle(@RequestParam(value="title") String title) {
		QaGenericBaseTransfer<QaTreeDto> dto = new QaGenericBaseTransfer<QaTreeDto>();
		try {
			//异步记录查询历史
			qaTreeService.searchRecord(title);
			//进行查询
			dto = qaClientService.findByTitle(title);
		} catch (QaClientException | QaTreeException e ) {
			returnError(e, dto);
		}
		return dto;
	}

	@GetMapping(path="/findTopRank")
	public QaGenericPagedTransfer<List<QaTreeSimpleDto>> findTopRank(@RequestParam(value="size") Integer size) {
		QaGenericPagedTransfer<List<QaTreeSimpleDto>> dto = new QaGenericPagedTransfer<List<QaTreeSimpleDto>>();
		try {
			dto = qaClientService.findTopRank(size);
		} catch (QaClientException e) {
			returnError(e, dto);
		}
		return dto;
	}
}
