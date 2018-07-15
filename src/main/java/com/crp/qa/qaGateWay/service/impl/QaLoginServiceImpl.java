/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaGateWay.service.inte.QaLoginService;
import com.crp.qa.qaGateWay.service.inte.QaTokenService;
import com.crp.qa.qaGateWay.util.exception.QaLoginException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;

/**
 * 登录服务
 * @author huangyue
 * @date 2018年5月31日 下午6:10:55
 * @ClassName QaLoginServiceImpl
 */
@Service(value="qaLoginService")
@Transactional
public class QaLoginServiceImpl extends QaBaseServiceImpl implements QaLoginService {
	
	@Resource(name="qaTokenService")
	private QaTokenService qaTokenService;

	@Override
	public QaBaseTransfer login(String account,String password) throws QaLoginException{
		if(StringUtils.isEmpty(account)||StringUtils.isEmpty(password)) {
			throw new QaLoginException("用户名或密码为空！");
		}
		
		//服务地址
		String url = URL_AUTHORIZATION+"/Login/login";
		
		//获取参数集合
		MultiValueMap<String,String> variables= this.getMultiValueMap("AUTHORIZATION");
		variables.add("account", account);
		variables.add("password", password);
		
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.postForEntity(url, variables,JSONObject.class).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		
		//如果登录成功，取到token存在自己的redis里，以后就通过自己的redis判断有无登录
		if(dto.getStatus().equals("success")) {
			String token = dto.getToken(); //返回的token
			Object content = dto.getContent(); //返回的具体信息
			//如果token不为空，就放入redis里
			if(token!=null&&!token.equals("")) {
				qaTokenService.setToken(token+"gateway",content);
			}
		}
		//返回
		return dto;
	}

	@Override
	public boolean isLogin(String logingToken) throws QaLoginException {
		if(StringUtils.isEmpty(logingToken)) {
			throw new QaLoginException("token为空！");
		}
		//服务地址
		String url = URL_AUTHORIZATION+"/Login/isLogin?token={token}&logingToken={logingToken}";
		//获取参数集合
		Map<String,String> variables= this.getParamMap("AUTHORIZATION");
		variables.put("logingToken", logingToken);
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.getForEntity(url,JSONObject.class, variables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		//如果返回success，则说明已经登录
		return dto.getStatus().equals("success");
	}

	@Override
	public QaBaseTransfer findByToken(String logingToken) throws QaLoginException {
		if(StringUtils.isEmpty(logingToken)) {
			throw new QaLoginException("token为空！");
		}
		//服务地址
		String url = URL_AUTHORIZATION+"/Login/findByToken?token={token}&logingToken={logingToken}";
		//获取参数集合
		Map<String,String> variables= this.getParamMap("AUTHORIZATION");
		variables.put("logingToken", logingToken);
		//post数据去登录，会返回一个带状态的对象
		JSONObject json = restTemplate.getForEntity(url,JSONObject.class, variables).getBody();
		QaBaseTransfer dto = json.toJavaObject(QaBaseTransfer.class);
		//如果返回success，则说明已经登录
		return dto;
	}

}
