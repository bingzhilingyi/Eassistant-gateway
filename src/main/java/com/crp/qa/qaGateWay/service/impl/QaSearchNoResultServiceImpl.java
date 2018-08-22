package com.crp.qa.qaGateWay.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaGateWay.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaGateWay.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaSearchNoResultService;
import com.crp.qa.qaGateWay.util.exception.QaSearchHistoryException;
import com.crp.qa.qaGateWay.util.exception.QaSearchNoResultException;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

@Service(value="qaSearchNoResultService")
@Transactional
public class QaSearchNoResultServiceImpl extends QaBaseServiceImpl implements QaSearchNoResultService{

	@Override
	public QaGenericPagedTransfer<QaSearchHistoryDto> findPagedAll(Integer page, Integer size) throws QaSearchHistoryException {
		page = page==null?0:page;
		size = size==null?20:size;
		String url = URL_CORE+"/noresult/findPagedAll?token={token}&page={page}&size={size}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("page", page.toString());
		uriVariables.put("size", size.toString());
		ParameterizedTypeReference<QaGenericPagedTransfer<QaSearchHistoryDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericPagedTransfer<QaSearchHistoryDto>>() {};
		try {
			QaGenericPagedTransfer<QaSearchHistoryDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(Exception e) {
			throw new QaSearchHistoryException("调用服务出错了！ uri:" + url);
		}
	}
	
	@Override
	public QaGenericPagedTransfer<QaSearchHistoryDto> findByCreationDateBetween(Date from,Date to,Integer page,Integer size)throws QaSearchHistoryException{
		page = page==null?0:page;
		size = size==null?20:size;
		String fromStr = "",toStr = "";
		if(from!=null) {
			fromStr = sdf.format(from);
		}
		if(to!=null) {
			toStr = sdf.format(to);
		}
		String url = URL_CORE+"/noresult/findByCreationDateBetween?token={token}&page={page}&size={size}&from={from}&to={to}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("page", page.toString());
		uriVariables.put("size", size.toString());
		uriVariables.put("from", fromStr);
		uriVariables.put("to", toStr);
		ParameterizedTypeReference<QaGenericPagedTransfer<QaSearchHistoryDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericPagedTransfer<QaSearchHistoryDto>>() {};
		try {
			QaGenericPagedTransfer<QaSearchHistoryDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(Exception e) {
			throw new QaSearchHistoryException("调用服务出错了！ uri:" + url);
		}
	}
}
