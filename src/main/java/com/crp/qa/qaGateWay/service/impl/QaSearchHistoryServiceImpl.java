package com.crp.qa.qaGateWay.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaGateWay.domain.dto.QaSearchCountDto;
import com.crp.qa.qaGateWay.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaSearchHistoryService;
import com.crp.qa.qaGateWay.util.exception.QaSearchHistoryException;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
import com.crp.qa.qaGateWay.util.transfer.QaGenericListTransfer;

@Service(value="QaSearchHistoryService")
public class QaSearchHistoryServiceImpl extends QaBaseServiceImpl implements QaSearchHistoryService {

	@Override
	public QaGenericListTransfer<QaSearchHistoryDto> findTopRank(Integer rank) throws QaSearchHistoryException {
		rank = rank==null?10:rank;
		if(rank<1) {
			throw new QaSearchHistoryException("传入数值小于1！");
		}
		String url = URL_CORE+"/history/findTopRank?token={token}&rank={rank}";
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("rank", rank.toString());
		
		//当返回的数据类型比较复杂时（比如返回List）,需要用ParameterizedTypeReference来返回特定数据类型
		ParameterizedTypeReference<QaGenericListTransfer<QaSearchHistoryDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericListTransfer<QaSearchHistoryDto>>() {};

		try {
			QaGenericListTransfer<QaSearchHistoryDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(Exception e) {
			throw new QaSearchHistoryException("调用服务出错了！ uri:" + url);
		}
	}

	@Override
	@Async
	public void searchRecord(String title) throws QaTreeException {
		String url = URL_CORE+"/history/searchRecord";
		//注意，post只能用MultiValueMap传递表单
		MultiValueMap<String,String> uriVariables= this.getMultiValueMap("CORE");
		uriVariables.add("title", title);
		try {
			//发起rest请求
			restTemplate.postForEntity(url, uriVariables, JSONObject.class).getBody();
		}catch(Exception e) {
			throw new QaTreeException("调用服务出错了！ uri:" + url);
		}
	}

	@Override
	public QaGenericListTransfer<QaSearchCountDto> findCount(Date from, Date to) throws QaSearchHistoryException {
		from = from==null?new Date():from;
		to = to==null?new Date():to;
		DateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		String fromStr = sdf.format(from);
		String toStr = sdf.format(to);
		String url = URL_CORE+"/hiscount/findByDateDuring?token={token}&from={from}&to={to}";
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("from", fromStr);
		uriVariables.put("to", toStr);
		
		//当返回的数据类型比较复杂时（比如返回List）,需要用ParameterizedTypeReference来返回特定数据类型
		ParameterizedTypeReference<QaGenericListTransfer<QaSearchCountDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericListTransfer<QaSearchCountDto>>() {};

		try {
			QaGenericListTransfer<QaSearchCountDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(Exception e) {
			throw new QaSearchHistoryException("调用服务出错了！ uri:" + url);
		}
	}
}
