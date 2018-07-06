package com.crp.qa.qaGateWay.service.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaGateWay.service.inte.QaGroupService;
import com.crp.qa.qaGateWay.util.exception.QaGroupException;
import com.crp.qa.qaGateWay.util.exception.QaUserException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaPagedTransfer;

@Service(value="qaGroupService")
@Transactional
public class QaGroupServiceImpl extends QaBaseServiceImpl implements QaGroupService {

	@Override
	public QaBaseTransfer findAll() throws QaGroupException {
		//服务地址
		String url = URL_AUTHORIZATION+"/group/findAll?token={token}";
		//获取参数集合
		Map<String, String> variables= this.getParamMap("AUTHORIZATION");
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.getForEntity(url,JSONObject.class, variables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		//返回
		return dto;
	}
	
	@Override
	public QaBaseTransfer findById(Integer id) throws QaGroupException{
		//服务地址
		String url = URL_AUTHORIZATION+"/group/findById/{groupId}?token={token}";
		//获取参数集合
		Map<String, String> variables= this.getParamMap("AUTHORIZATION");
		variables.put("groupId", id.toString());
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.getForEntity(url,JSONObject.class, variables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		//返回
		return dto;
	}

	@Override
	public QaBaseTransfer findByGroupName(String groupName) throws QaGroupException {
		//服务地址
		String url = URL_AUTHORIZATION+"/group/findByGroupName?token={token}&groupName={groupName}";
		//获取参数集合
		Map<String, String> variables= this.getParamMap("AUTHORIZATION");
		variables.put("groupName", groupName);
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.getForEntity(url,JSONObject.class, variables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		//返回
		return dto;
	}

	@Override
	public QaPagedTransfer findByGroupNameLike(String groupName,Integer page,Integer size) throws QaGroupException {
		//服务地址
		String url = URL_AUTHORIZATION+"/group/findByGroupNameLike?token={token}&groupName={groupName}&page={page}&size={size}";
		//获取参数集合
		Map<String, String> variables= this.getParamMap("AUTHORIZATION");
		variables.put("groupName", groupName);
		variables.put("page", page.toString());
		variables.put("size", size.toString());
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.getForEntity(url,JSONObject.class, variables).getBody();
		QaPagedTransfer dto = json.toJavaObject(QaPagedTransfer.class);
		//返回
		return dto;
	}

	@Override
	public QaBaseTransfer save(String group) throws QaGroupException {
		if(group==null||group.equals("")) {
			throw new QaGroupException("传入对象为空，保存失败！");
		}
		//服务地址
		String url = URL_AUTHORIZATION+"/group/save";
		//获取参数集合
		MultiValueMap<String, String> variables= this.getMultiValueMap("AUTHORIZATION");
		variables.add("group", group);
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.postForEntity(url, variables,JSONObject.class).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		//返回
		return dto;
	}

	@Override
	public QaBaseTransfer delete(Integer id) throws QaGroupException {
		if(id==null) {
			throw new QaGroupException("传入对象无主键，删除失败！");
		}
		String url = URL_AUTHORIZATION+"/group/delete?token="+TOKEN_AUTHORIZATION;
		Map<String,String> variables= this.getParamMap("AUTHORIZATION");
		variables.put("id", id.toString());
		JSONObject json = this.exchange(url, HttpMethod.DELETE, JSONObject.class,variables);
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		return dto;
	}

	@Override
	public QaBaseTransfer update(String group) throws QaGroupException {
		if(group==null) {
			throw new QaGroupException("传入对象为空，更新失败！");
		}
		String url = URL_AUTHORIZATION+"/group/update?token="+TOKEN_AUTHORIZATION;
		Map<String,String> variables= this.getParamMap("AUTHORIZATION");
		variables.put("group", group);
		JSONObject json = this.exchange(url, HttpMethod.PUT, JSONObject.class,variables);
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		return dto;
	}

}
