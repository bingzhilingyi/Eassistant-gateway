package com.crp.qa.qaGateWay.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaGateWay.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaSearchNoResultService;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

@RestController
@RequestMapping(path="/noresult")
public class QaSearchNoResultController extends QaBaseController{

	@Resource(name="qaSearchNoResultService")
	QaSearchNoResultService qaSearchNoResultService;
	
	@GetMapping(path="/findPagedAll")
	public QaGenericPagedTransfer<QaSearchNoResultDto> findPagedAll(
			@RequestParam(value="page") Integer page,
			@RequestParam(value="size") Integer size){
		//创建返回对象
		QaGenericPagedTransfer<QaSearchNoResultDto> dto = new QaGenericPagedTransfer<QaSearchNoResultDto>();
		try {
			dto = qaSearchNoResultService.findPagedAll(page, size);
		} catch (Exception e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	@GetMapping(path="/findByCreationDateBetween")
	public QaGenericPagedTransfer<QaSearchNoResultDto> findByCreationDateBetween(
			@RequestParam(value="from") @Nullable String from,
			@RequestParam(value="to") @Nullable String to,
			@RequestParam(value="page") Integer page,
			@RequestParam(value="size") Integer size){
		//创建返回对象
		QaGenericPagedTransfer<QaSearchNoResultDto> dto = new QaGenericPagedTransfer<QaSearchNoResultDto>();
		Date fromDate=null,toDate=null;
		try {
			if(from!=null && !from.trim().equals("")) {		
				fromDate = sdf.parse(from);
			}
			if(to!=null && !to.trim().equals("")) {
				toDate = sdf.parse(to);
			}
			dto = qaSearchNoResultService.findByCreationDateBetween(fromDate,toDate,page, size);
		} catch (Exception e) {
			returnError(e, dto);
		}
		return dto;
	}
}
