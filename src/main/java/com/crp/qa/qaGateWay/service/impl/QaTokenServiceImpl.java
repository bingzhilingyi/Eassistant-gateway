/**
 * huangyue
 * 2018年5月22日
 */
package com.crp.qa.qaGateWay.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaGateWay.service.inte.QaTokenService;

/**
 * @author huangyue
 * @date 2018年5月22日 上午9:02:24
 * @ClassName QaTokenServiceImpl
 */
@Service(value="qaTokenService")
public class QaTokenServiceImpl implements QaTokenService {
	
	@Autowired
    StringRedisTemplate stringRedisTemplate;
	
	@Override
	public String generateToken() {
		return UUID.randomUUID().toString().replace("-", "");  
	}
	
	@Override
	public void setToken(String token,Object value) {
		//object to string
		String valueString = JSONObject.toJSONString(value);
		//save token
		stringRedisTemplate.boundValueOps(token).set(valueString);
		//set expire time to 60 minutes
		stringRedisTemplate.expire(token, 60, TimeUnit.MINUTES);
	}
	
	@Override
	public String getByToken(String token) {
		return stringRedisTemplate.boundValueOps(token).get();
	}
	
	@Override
	public boolean isExists(String token) {
		return stringRedisTemplate.hasKey(token);
	}
	
	@Override
	public void deleteByToken(String token) {
		stringRedisTemplate.delete(token);
	}
}
