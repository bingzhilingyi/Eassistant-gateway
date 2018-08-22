package com.crp.qa.qaGateWay.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaGateWay.domain.dto.QaSearchCountDto;
import com.crp.qa.qaGateWay.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaGateWay.service.inte.QaSearchHistoryService;
import com.crp.qa.qaGateWay.util.exception.QaSearchHistoryException;
import com.crp.qa.qaGateWay.util.transfer.QaGenericListTransfer;

@RestController
@RequestMapping(path="/history")
public class QaSearchHistoryController extends QaBaseController {

	@Resource(name="QaSearchHistoryService")
	QaSearchHistoryService QaSearchHistoryService;
	
	@GetMapping(path="findTopRank")
	public QaGenericListTransfer<QaSearchHistoryDto> findTopRank(@RequestParam(name="size")Integer size) {
		QaGenericListTransfer<QaSearchHistoryDto>  dto = new QaGenericListTransfer<QaSearchHistoryDto>();
		try {
			dto = QaSearchHistoryService.findTopRank(size);
		}catch(QaSearchHistoryException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	@GetMapping(path="findCount")
	public QaGenericListTransfer<QaSearchCountDto> findCount(
			@RequestParam(name="from") @Nullable String from
			,@RequestParam(name="to") @Nullable String to){
		QaGenericListTransfer<QaSearchCountDto>  dto = new QaGenericListTransfer<QaSearchCountDto>();
		try {
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = sdf.parse(from);
			Date toDate = sdf.parse(to);
			dto = QaSearchHistoryService.findCount(fromDate,toDate);
		}catch(Exception e) {
			returnError(e, dto);
		}
		return dto;
	}

}
