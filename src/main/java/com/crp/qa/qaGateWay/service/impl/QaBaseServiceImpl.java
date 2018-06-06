/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.service.impl;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
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
	
	@SuppressWarnings("unused")
	private final Logger LOGGER = LoggerFactory.getLogger(QaBaseServiceImpl.class);
	
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
	 * exchange方法可以执行任何操作（get,post,delete,put），用它来执行delete命令，可以获取返回值
	 * @author huangyue
	 * @date 2018年5月31日 下午3:12:51
	 * @param url
	 * @param method
	 * @param bodyType
	 * @param params
	 * @return
	 */
	public <T> T exchange(String url, HttpMethod method, Class<T> bodyType,Map<String,String> params) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        // 请求体
        headers.setContentType(mediaType);
        //提供json转化功能
        ObjectMapper mapper = new ObjectMapper();
        String str = null;
        try {
            if (!params.isEmpty()) {
                str = mapper.writeValueAsString(params);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 发送请求
        HttpEntity<String> entity = new HttpEntity<>(str, headers);
        ResponseEntity<T> resultEntity = restTemplate.exchange(url, method, entity, bodyType);
        return resultEntity.getBody();
    }

}
