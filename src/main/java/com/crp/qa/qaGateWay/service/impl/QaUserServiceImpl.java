/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaGateWay.service.inte.QaUserService;
import com.crp.qa.qaGateWay.util.exception.QaUserException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaPagedTransfer;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 有关用户的服务
 * @author huangyue
 * @date 2018年5月31日 下午6:07:38
 * @ClassName QaAuthorizationServiceImpl
 */
@Service(value="qaUserService")
@Transactional
public class QaUserServiceImpl extends QaBaseServiceImpl implements QaUserService {

	@Override
	public QaBaseTransfer findAll() throws QaUserException {
		//服务地址
		String url = URL_AUTHORIZATION+"/user/findAll?token={token}";
		//获取参数集合
		Map<String, String> variables= this.getParamMap("AUTHORIZATION");
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.getForEntity(url,JSONObject.class, variables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		//返回
		return dto;
	}

	@Override
	public QaPagedTransfer findPagedAll(Integer page, Integer size) throws QaUserException {
		//初始化参数
		page = page==null?1:page;
		size = size==null?20:size;
		//服务地址
		String url = URL_AUTHORIZATION+"/user/findPagedAll?token={token}&page={page}&size={size}";
		//获取参数集合
		Map<String, String> variables= this.getParamMap("AUTHORIZATION");
		variables.put("page", page.toString());
		variables.put("size", size.toString());
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.getForEntity(url,JSONObject.class, variables).getBody();
		QaPagedTransfer dto = json.toJavaObject(QaPagedTransfer.class);
		//返回
		return dto;
	}

	@Override
	public QaBaseTransfer findById(Integer id) throws QaUserException {
		//如果id为null，抛出错误
		if(id==null) {
			throw new QaUserException("传入的用户id为空!");
		}
		//服务地址
		String url = URL_AUTHORIZATION+"/user/get/{id}?token={token}";
		//获取参数集合
		Map<String, String> variables= this.getParamMap("AUTHORIZATION");
		variables.put("id", id.toString());
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.getForEntity(url,JSONObject.class, variables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		//返回
		return dto;
	}

	@Override
	public QaBaseTransfer findByAccountEqual(String account) throws QaUserException {
		return null;
	}

	@Override
	public QaBaseTransfer findByAccountOrName(String account) throws QaUserException {
		//如果传入account为空，直接查全部		
		if(account==null||account.equals("")) {
			return this.findAll();
		}
		//服务地址
		String url = URL_AUTHORIZATION+"/user/findByAccountOrName?token={token}&account={account}";
		//获取参数集合
		Map<String, String> variables= this.getParamMap("AUTHORIZATION");
		variables.put("account", account);
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.getForEntity(url,JSONObject.class, variables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		//返回
		return dto;
	}

	@Override
	public QaPagedTransfer findPagedByAccountOrName(String account, Integer page, Integer size, String searchType) throws QaUserException {
		//如果传入account为空，直接查全部		
		if(account==null||account.equals("")) {
			return this.findPagedAll(page, size);
		}
		//初始化参数
		page = page==null?1:page;
		size = size==null?20:size;
		//服务地址
		String url = URL_AUTHORIZATION+"/user/findPagedByAccountOrName?token={token}&account={account}&page={page}&size={size}&searchType={searchType}";
		//获取参数集合
		Map<String, String> variables= this.getParamMap("AUTHORIZATION");
		variables.put("account", account);
		variables.put("page", page.toString());
		variables.put("size", size.toString());
		variables.put("searchType", searchType);
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.getForEntity(url,JSONObject.class, variables).getBody();
		QaPagedTransfer dto = json.toJavaObject(QaPagedTransfer.class);
		//返回
		return dto;
	}

	@Override
	public QaBaseTransfer save(String user) throws QaUserException {
		if(user==null||user.equals("")) {
			throw new QaUserException("传入对象为空，保存失败！");
		}
		//服务地址
		String url = URL_AUTHORIZATION+"/user/save";
		//获取参数集合
		MultiValueMap<String, String> variables= this.getMultiValueMap("AUTHORIZATION");
		variables.add("user", user);
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.postForEntity(url, variables,JSONObject.class).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		//返回
		return dto;
	}

	@Override
	public QaBaseTransfer deleteById(Integer id) throws QaUserException {
		if(id==null) {
			throw new QaUserException("传入对象无主键，删除失败！");
		}
		String url = URL_AUTHORIZATION+"/user/delete?token={token}";
		Map<String,String> uriVariables= this.getParamMap("AUTHORIZATION");
		uriVariables.put("id", id.toString());
		JSONObject json;
		try {
			json = this.exchange(url, HttpMethod.DELETE, JSONObject.class,null,uriVariables);
		} catch (JsonProcessingException e) {
			throw new QaUserException("创建请求体 HttpEntity 失败！");
		} catch(RestClientException e){
			throw new QaUserException(new StringBuilder("调用服务失败：").append(url).toString());
		}
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		return dto;
	}

	@Override
	public QaBaseTransfer update(String user) throws QaUserException {
		if(user==null) {
			throw new QaUserException("传入对象为空，更新失败！");
		}
		String url = URL_AUTHORIZATION+"/user/update?token={token}";
		Map<String,String> uriVariables= this.getParamMap("AUTHORIZATION");
		Map<String,String> bodyVariables= new HashMap<String,String>();
		bodyVariables.put("user", user);
		JSONObject json;
		try {
			json = this.exchange(url, HttpMethod.PUT, JSONObject.class,bodyVariables,uriVariables);
		} catch (JsonProcessingException e) {
			throw new QaUserException("创建请求体 HttpEntity 失败！");
		} catch(RestClientException e){
			throw new QaUserException(new StringBuilder("调用服务失败：").append(url).toString());
		}
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		return dto;
	}

}
