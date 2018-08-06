/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.service.impl;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.crp.qa.qaGateWay.service.inte.QaBaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author huangyue
 * @date 2018年5月31日 下午5:58:03
 * @ClassName QaBaseServiceImpl
 */
public class QaBaseServiceImpl implements QaBaseService{
	
	final Logger LOGGER = LoggerFactory.getLogger(QaBaseServiceImpl.class);
	
	@Autowired
	protected RestTemplate restTemplate;
	
	//token
	@Value("${TOKEN.CORE}")
	protected String TOKEN_CORE;
	
	@Value("${TOKEN.AUTHORIZATION}")
	protected String TOKEN_AUTHORIZATION;
	
	//远程服务地址
	@Value("${SERVICES.CORE}")
	protected String URL_CORE;
	
	@Value("${SERVICES.AUTHORIZATION}")
	protected String URL_AUTHORIZATION;
	
	/**
	 * 获取包含token的get传参map
	 * @author huangyue
	 * @date 2018年5月29日 上午11:03:42
	 * @return
	 */
	protected Map<String,String> getParamMap(String service){
		Map<String, String> requestEntity = new HashMap<String, String>();
		String token = service.equals("CORE")?TOKEN_CORE:TOKEN_AUTHORIZATION;
        requestEntity.put("token", token);
        return requestEntity;
	}
	
	/**
	 * 获取包含token的post传参map
	 * post传参只能用MultiValueMap
	 * @author huangyue
	 * @date 2018年5月31日 下午3:12:09
	 * @return
	 */
	protected MultiValueMap<String,String> getMultiValueMap(String service){
		MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
		String token = service.equals("CORE")?TOKEN_CORE:TOKEN_AUTHORIZATION;
        requestEntity.add("token", token);
        return requestEntity;
	}
	
	/**
	 * 发送restful请求
	 * @param url 服务路径
	 * @param method 请求方法
	 * @param bodyType 返回的数据类型
	 * @param bodyVariables  放在body里的数据
	 * @param uriVariables 放在uri里的数据
	 * @return 
	 * @throws JsonProcessingException
	 * @throws RestClientException
	 * @Date 2018年7月18日
	 * @author huangyue
	 */
	public <T> T exchange(String url, HttpMethod method, @SuppressWarnings("rawtypes") ParameterizedTypeReference bodyType,Map<String,?> bodyVariables,Map<String,String> uriVariables ) 
			throws JsonProcessingException,RestClientException{
        
		HttpEntity<String> entity = getHttpEntity(bodyVariables);
              
		@SuppressWarnings("unchecked")
		ResponseEntity<T> resultEntity = restTemplate.exchange(url, method, entity, bodyType,uriVariables);
        return resultEntity.getBody();
    }
	
	/**
	 * 发送restful请求
	 * @param url 服务路径
	 * @param method 请求方法
	 * @param bodyType 返回的数据类型
	 * @param bodyVariables 放在body里的数据
	 * @param uriVariables 放在uri里的数据
	 * @return
	 * @throws JsonProcessingException
	 * @throws RestClientException
	 * @Date 2018年7月18日
	 * @author huangyue
	 */
	public <T> T exchange(String url, HttpMethod method, Class<T> bodyType,Map<String,String> bodyVariables,Map<String,String> uriVariables ) 
				throws JsonProcessingException,RestClientException{
		
		HttpEntity<String> entity = getHttpEntity(bodyVariables);
		
		ResponseEntity<T> resultEntity = restTemplate.exchange(url, method, entity, bodyType,uriVariables);
        return resultEntity.getBody();
	}
	
	/**
	 * 包装数组为HttpEntity
	 * @param bodyVariables Map类型数组
	 * @return
	 * @throws JsonProcessingException
	 * @Date 2018年7月18日
	 * @author huangyue
	 */
	private HttpEntity<String> getHttpEntity(Map<String,?> bodyVariables) throws JsonProcessingException {
		// 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        headers.setContentType(mediaType);
        
        //提供json转化功能
        ObjectMapper mapper = new ObjectMapper();
        String str = null;
        if (bodyVariables!=null && !bodyVariables.isEmpty()) {
            str = mapper.writeValueAsString(bodyVariables);
        }
        // 请求体
        HttpEntity<String> entity = new HttpEntity<>(str, headers);
        return entity;
	}
	
	/**
	 * 检查对象是否为null或空
	 * @param o
	 * @param message
	 * @throws NullPointerException
	 * @Date 2018年7月21日
	 * @author huangyue
	 */
	public void checkNull(Object o,String message) throws NullPointerException {
		if( o == null 
				|| ( o instanceof String && "".equals( (String)o ) ) 
				|| ( o instanceof List && ((List<?>)o).size()==0) ){					 
			throw new NullPointerException(message);
		}
	}

}
