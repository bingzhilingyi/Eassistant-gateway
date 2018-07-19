/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.service.impl;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaGateWay.domain.dto.QaTreeDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaTreeService;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericListTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author huangyue
 * @date 2018年5月31日 下午9:07:03
 * @ClassName QaTreeServiceImpl
 */
@Service(value="qaTreeService")
@Transactional
public class QaTreeServiceImpl extends QaBaseServiceImpl implements QaTreeService {

	@Override
	public QaGenericListTransfer<QaTreeSimpleDto> findRoot() throws QaTreeException {
		String url = URL_CORE+"/tree/getRoot?token={token}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		//当返回的数据类型比较复杂时（比如返回List）,需要用ParameterizedTypeReference来返回特定数据类型
		ParameterizedTypeReference<QaGenericListTransfer<QaTreeSimpleDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericListTransfer<QaTreeSimpleDto>>() {};
		try {
			QaGenericListTransfer<QaTreeSimpleDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(JsonProcessingException | RestClientException e) {
			throw new QaTreeException(new StringBuilder("调用服务出错了！ uri:").append(url).toString());
		}
	}

	@Override
	public QaGenericListTransfer<QaTreeSimpleDto> findByParentId(Integer parentId) throws QaTreeException {
		if(parentId==null) {
			throw new QaTreeException("传入parentId为null！");
		}
		String url = URL_CORE+"/tree/getByParentId/{parentId}?token={token}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("parentId", parentId.toString());
		//当返回的数据类型比较复杂时（比如返回List）,需要用ParameterizedTypeReference来返回特定数据类型
		ParameterizedTypeReference<QaGenericListTransfer<QaTreeSimpleDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericListTransfer<QaTreeSimpleDto>>() {};
		try {
			QaGenericListTransfer<QaTreeSimpleDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(JsonProcessingException | RestClientException e) {
			throw new QaTreeException(new StringBuilder("调用服务出错了！ uri:").append(url).append(" parentId:").append(parentId).toString());
		}
	}

	@Override
	public QaGenericBaseTransfer<QaTreeDto> findById(Integer id) throws QaTreeException {
		if(id==null) {
			throw new QaTreeException("传入id为null！");
		}
		String url = URL_CORE+"/tree/getById/{Id}?token={token}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("Id", id.toString());
		//当返回的数据类型比较复杂时（比如返回List）,需要用ParameterizedTypeReference来返回特定数据类型
		ParameterizedTypeReference<QaGenericBaseTransfer<QaTreeDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericBaseTransfer<QaTreeDto>>() {};
		try {
			QaGenericBaseTransfer<QaTreeDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(JsonProcessingException | RestClientException e) {
			throw new QaTreeException(new StringBuilder("调用服务出错了！ uri:").append(url).append(" Id:").append(id).toString());
		}
	}

	@Override
	public QaGenericBaseTransfer<QaTreeDto> findByTitle(String title) throws QaTreeException {
		if(title==null || title.trim().equals("")) {
			throw new QaTreeException("title is null");
		}
		title = title.trim();
		String url = URL_CORE+"/tree/getByTitle?token={token}&title={title}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("title", title);
		ParameterizedTypeReference<QaGenericBaseTransfer<QaTreeDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericBaseTransfer<QaTreeDto>>() {};
		try {
			QaGenericBaseTransfer<QaTreeDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(Exception e) {
			throw new QaTreeException(new StringBuilder("调用服务出错了！ uri:").append(url).append(" title:").append(title).toString());
		}
	}

	@Override
	public QaGenericPagedTransfer<QaTreeSimpleDto> findPagedByTitleLike(String title, Integer page, Integer size) throws QaTreeException {
		if(title==null || title.trim().equals("")) {
			throw new QaTreeException("title is null");
		}
		title = title.trim();
		String url = URL_CORE+"/tree/getPagedByTitleLike?token={token}&title={title}&page={page}&size={size}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("title", title);
		uriVariables.put("page", page.toString());
		uriVariables.put("size", size.toString());
		ParameterizedTypeReference<QaGenericPagedTransfer<QaTreeSimpleDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericPagedTransfer<QaTreeSimpleDto>>() {};
		try {
			QaGenericPagedTransfer<QaTreeSimpleDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(Exception e) {
			throw new QaTreeException("调用服务出错了！ uri:" + url);
		}
	}

	@Override
	public QaBaseTransfer save(String node) throws QaTreeException {
		String url = URL_CORE+"/tree/save";
		//注意，post只能用MultiValueMap传递表单
		MultiValueMap<String,String> uriVariables= this.getMultiValueMap("CORE");
		uriVariables.add("node", node);
		try {
			//发起rest请求
			JSONObject json = restTemplate.postForEntity(url, uriVariables, JSONObject.class).getBody();
			QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
			return dto;
		}catch(Exception e) {
			throw new QaTreeException("调用服务出错了！ uri:" + url);
		}
	}

	@Override
	public QaBaseTransfer update(String node) throws QaTreeException {
		String url = URL_CORE+"/tree/update?token={token}";
		Map<String,String> uriVariables= this.getParamMap("CORE");
		Map<String,String> bodyVariables= new HashMap<String,String>();
		bodyVariables.put("node", node);		
		try {
			JSONObject json = this.exchange(url, HttpMethod.PUT, JSONObject.class,bodyVariables,uriVariables);
			QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
			return dto;
		}catch(Exception e) {
			throw new QaTreeException("调用服务出错了！ uri:" + url);
		}
	}

	@Override
	public QaBaseTransfer delete(Integer id) throws QaTreeException {
		String url = URL_CORE+"/tree/delete/{id}?token={token}";
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("id", id.toString());
		Map<String,String> bodyVariables= new HashMap<String,String>();
		try {
			JSONObject json = this.exchange(url, HttpMethod.DELETE, JSONObject.class,bodyVariables,uriVariables);
			QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
			return dto;
		}catch(Exception e) {
			throw new QaTreeException("调用服务出错了！ uri:" + url);
		}
	}

	@Override
	public QaGenericBaseTransfer<QaTreeDto> findChildrenByTitle(String title) throws QaTreeException {
		title = title==null?"":title.trim();
		if(title.equals("")) {
			throw new QaTreeException("title is null");
		}
		String url = URL_CORE+"/tree/findChildrenByTitle?token={token}&title={title}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("title", title);
		ParameterizedTypeReference<QaGenericBaseTransfer<QaTreeDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericBaseTransfer<QaTreeDto>>() {};
		try {
			QaGenericBaseTransfer<QaTreeDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(Exception e) {
			throw new QaTreeException("调用服务出错了！ uri:" + url);
		}
	}
	
	@Override
	public QaGenericListTransfer<QaTreeSimpleDto> findByTitleOrKeyword(String keyword) throws QaTreeException{
		keyword = keyword==null?"":keyword.trim();
		if(keyword.equals("")) {
			throw new QaTreeException("keyword is null");
		}
		String url = URL_CORE+"/tree/findByTitleOrKeyword?token={token}&keyword={keyword}";
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("keyword", keyword);
		
		//当返回的数据类型比较复杂时（比如返回List）,需要用ParameterizedTypeReference来返回特定数据类型
		ParameterizedTypeReference<QaGenericListTransfer<QaTreeSimpleDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericListTransfer<QaTreeSimpleDto>>() {};

		try {
			QaGenericListTransfer<QaTreeSimpleDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(Exception e) {
			throw new QaTreeException("调用服务出错了！ uri:" + url);
		}
	}
	
	@Override
	public QaGenericPagedTransfer<QaTreeSimpleDto> findPagedByTitleOrKeyword(String keyword, Integer page, Integer size) throws QaTreeException {
		keyword = keyword==null?"":keyword.trim();
		page = page==null?0:page;
		size = size==null?10:size;
		if(keyword==null || keyword.trim().equals("")) {
			throw new QaTreeException("title is null");
		}else if(page<0) {
			throw new QaTreeException("current page must not less than zero");
		}else if(size<1) {
			throw new QaTreeException("page size must not less than 1");
		}
		
		String url = URL_CORE+"/tree/findPagedByTitleOrKeyword?token={token}&keyword={keyword}&page={page}&size={size}";
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("keyword", keyword);
		uriVariables.put("page", page.toString());
		uriVariables.put("size", size.toString());
		//当返回的数据类型比较复杂时（比如返回List）,需要用ParameterizedTypeReference来返回特定数据类型
		ParameterizedTypeReference<QaGenericPagedTransfer<QaTreeSimpleDto>> typeRef = 
				new ParameterizedTypeReference<QaGenericPagedTransfer<QaTreeSimpleDto>>() {};

		try {
			QaGenericPagedTransfer<QaTreeSimpleDto> dto = this.exchange(url, HttpMethod.GET, typeRef,null,uriVariables);
			return dto;
		}catch(Exception e) {
			throw new QaTreeException(new StringBuilder("调用服务出错了！ uri:").append(url).append(" keyword:").append(keyword).toString());
		}
	}

	@Override
	public QaGenericPagedTransfer<QaTreeSimpleDto> findTopRank(Integer size) throws QaTreeException{
		String url = URL_CORE+"/tree/findTopRank?token={token}&size={size}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("size", size.toString());
		try {
			//get json数据
			@SuppressWarnings("unchecked")
			QaGenericPagedTransfer<QaTreeSimpleDto> dto = restTemplate.getForEntity(url, QaGenericPagedTransfer.class,uriVariables).getBody();
			return dto;
		}catch(Exception e) {
			throw new QaTreeException("调用服务出错了！ uri:" + url);
		}
	}

	@Override
	@Async
	public void searchRecord(String title) throws QaTreeException {
		String url = URL_CORE+"/tree/searchRecord";
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

	
}
