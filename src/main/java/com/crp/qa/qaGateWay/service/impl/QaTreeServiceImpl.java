/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaGateWay.domain.dto.QaTreeDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaTreeService;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

/**
 * @author huangyue
 * @date 2018年5月31日 下午9:07:03
 * @ClassName QaTreeServiceImpl
 */
@Service(value="qaTreeService")
@Transactional
public class QaTreeServiceImpl extends QaBaseServiceImpl implements QaTreeService {

	@Override
	public QaBaseTransfer findRoot() throws QaTreeException {
		String url = URL_CORE+"/tree/getRoot?token={token}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		//get json数据
		JSONObject json = restTemplate.getForEntity(url, JSONObject.class,uriVariables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		return dto;
	}

	@Override
	public QaBaseTransfer findByParentId(Integer parentId) throws QaTreeException {
		String url = URL_CORE+"/tree/getByParentId/{parentId}?token={token}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("parentId", parentId.toString());
		//get json数据
		JSONObject json = restTemplate.getForEntity(url, JSONObject.class,uriVariables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		return dto;
	}

	@Override
	public QaBaseTransfer findById(Integer id) throws QaTreeException {
		String url = URL_CORE+"/tree/getById/{Id}?token={token}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("Id", id.toString());
		//get json数据
		JSONObject json = restTemplate.getForEntity(url, JSONObject.class,uriVariables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		return dto;
	}

	@Override
	public QaBaseTransfer findByTitle(String title) throws QaTreeException {
		String url = URL_CORE+"/tree/getByTitle?token={token}&title={title}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("title", title);
		//get json数据
		JSONObject json = restTemplate.getForEntity(url, JSONObject.class,uriVariables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		return dto;
	}

	@Override
	public QaGenericPagedTransfer<List<QaTreeSimpleDto>> findPagedByTitleLike(String title, Integer page, Integer size) throws QaTreeException {
		String url = URL_CORE+"/tree/getPagedByTitleLike?token={token}&title={title}&page={page}&size={size}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("title", title);
		uriVariables.put("page", page.toString());
		uriVariables.put("size", size.toString());
		//get json数据
		@SuppressWarnings("unchecked")
		QaGenericPagedTransfer<List<QaTreeSimpleDto>> dto = restTemplate.getForEntity(url, QaGenericPagedTransfer.class,uriVariables).getBody();
		return dto;
	}

	@Override
	public QaBaseTransfer save(String node) throws QaTreeException {
		String url = URL_CORE+"/tree/save";
		//注意，post只能用MultiValueMap传递表单
		MultiValueMap<String,String> uriVariables= this.getMultiValueMap("CORE");
		uriVariables.add("node", node);
      	//发起rest请求
		JSONObject json = restTemplate.postForEntity(url, uriVariables, JSONObject.class).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		return dto;
	}

	@Override
	public QaBaseTransfer update(String node) throws QaTreeException {
		String url = URL_CORE+"/tree/update?token="+TOKEN_CORE;
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("node", node);		
		JSONObject json = this.exchange(url, HttpMethod.PUT, JSONObject.class,uriVariables);
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		return dto;
	}

	@Override
	public QaBaseTransfer delete(Integer id) throws QaTreeException {
		String url = URL_CORE+"/tree/delete/"+id+"?token="+TOKEN_CORE;
		Map<String,String> uriVariables= this.getParamMap("CORE");
		JSONObject json = this.exchange(url, HttpMethod.DELETE, JSONObject.class,uriVariables);
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		return dto;
	}

	@Override
	public QaGenericBaseTransfer<QaTreeDto> findChildrenByTitle(String title,Boolean isNeedRecord) throws QaTreeException {
		String url = URL_CORE+"/tree/findChildrenByTitle?token={token}&title={title}&isNeedRecord={isNeedRecord}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("title", title);
		uriVariables.put("isNeedRecord", isNeedRecord.toString());
		//get json数据
		@SuppressWarnings("unchecked")
		QaGenericBaseTransfer<QaTreeDto> dto = restTemplate.getForEntity(url, QaGenericBaseTransfer.class,uriVariables).getBody();
		return dto;
	}

	@Override
	public QaGenericPagedTransfer<List<QaTreeSimpleDto>> findTopRank(Integer size) throws QaTreeException{
		String url = URL_CORE+"/tree/findTopRank?token={token}&size={size}";
		//获取参数集合
		Map<String,String> uriVariables= this.getParamMap("CORE");
		uriVariables.put("size", size.toString());
		//get json数据
		@SuppressWarnings("unchecked")
		QaGenericPagedTransfer<List<QaTreeSimpleDto>> dto = restTemplate.getForEntity(url, QaGenericPagedTransfer.class,uriVariables).getBody();
		return dto;
	}

	@Override
	@Async
	public void searchRecord(String title) throws QaTreeException {
		String url = URL_CORE+"/tree/searchRecord";
		//注意，post只能用MultiValueMap传递表单
		MultiValueMap<String,String> uriVariables= this.getMultiValueMap("CORE");
		uriVariables.add("title", title);
      	//发起rest请求
		restTemplate.postForEntity(url, uriVariables, JSONObject.class).getBody();
	}
}
