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
import com.crp.qa.qaGateWay.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaSearchNoResultService;
import com.crp.qa.qaGateWay.util.exception.QaSearchNoResultException;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

@Service(value="qaSearchNoResultService")
@Transactional
public class QaSearchNoResultServiceImpl extends QaBaseServiceImpl implements QaSearchNoResultService{

	@Override
	public QaGenericPagedTransfer<QaSearchNoResultDto> findPagedAll(Integer page, Integer size)
			throws QaSearchNoResultException {
		page = page==null?0:page;
		size = size==null?20:size;
		String url = URL_CORE+"/noresult/findPagedAll?token={token}&page={page}&size={size}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("page", page.toString());
		uriVariables.put("size", size.toString());
		ParameterizedTypeReference<QaGenericPagedTransfer<QaSearchNoResultDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericPagedTransfer<QaSearchNoResultDto>>() {};
		try {
			QaGenericPagedTransfer<QaSearchNoResultDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(Exception e) {
			throw new QaSearchNoResultException("调用服务出错了！ uri:" + url);
		}
	}

	@Override
	@Async
	public QaBaseTransfer save(QaSearchNoResultDto dto) throws QaSearchNoResultException ,NullPointerException{
		checkNull(dto,"传入对象为空");
		checkNull(dto.getRecordTitle(),"标题为空");
		if(dto.getRecordId()!=null) {
			throw new QaSearchNoResultException("传入对象已有主键");
		}
		
		String url = URL_CORE+"/noresult/save";
		//注意，post只能用MultiValueMap传递表单
		MultiValueMap<String,String> uriVariables= this.getMultiValueMap("CORE");
		uriVariables.add("record", JSONObject.toJSONString(dto));
		try {
			//发起rest请求
			JSONObject json = restTemplate.postForEntity(url, uriVariables, JSONObject.class).getBody();
			QaBaseTransfer d = json.toJavaObject(QaBaseTransfer.class);
			return d;
		}catch(Exception e) {
			throw new QaSearchNoResultException("调用服务出错了！ uri:" + url);
		}
	}
	
	@Override
	public QaGenericPagedTransfer<QaSearchNoResultDto> findByCreationDateBetween(Date from,Date to,Integer page,Integer size)throws QaSearchNoResultException{
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
		ParameterizedTypeReference<QaGenericPagedTransfer<QaSearchNoResultDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericPagedTransfer<QaSearchNoResultDto>>() {};
		try {
			QaGenericPagedTransfer<QaSearchNoResultDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(Exception e) {
			throw new QaSearchNoResultException("调用服务出错了！ uri:" + url);
		}
	}
}
