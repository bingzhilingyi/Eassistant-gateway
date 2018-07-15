package com.crp.qa.qaGateWay.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QaGroupControllerTest {
	
	private static final String token = "a8ce1be607bc4a1b94ab241527498f94";
	
	private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。  
	
	@Autowired  
    private WebApplicationContext wac; // 注入WebApplicationContext  

	@Before // 在测试开始前初始化工作  
    public void setup() {  
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
    } 

	@Test  
    public void findAll() throws Exception {  
    	Map<String, Object> map = new HashMap<>();
    	
        //MvcResult result = mockMvc.perform(post("/group/findAll").content(JSONObject.toJSONString(map)))
        MvcResult result = mockMvc.perform(
        			get("/group/findAll")
        			.param("token", token)
        		)
        		.andExpect(status().isOk())// 模拟向testRest发送get请求  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
                .andReturn();// 返回执行请求的结果  
        System.out.println("---------------findAll-----------------");    
        System.out.println(result.getResponse().getContentAsString());  
    }

	@Test  
    public void findById() throws Exception {  
        MvcResult result = mockMvc.perform(
        			get("/group/findById/1")
        			.param("token", token)
        		)
        		.andExpect(status().isOk())// 模拟向testRest发送get请求  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
                .andReturn();// 返回执行请求的结果  
        System.out.println("---------------findById-----------------");   
        System.out.println(result.getResponse().getContentAsString());  
    }
	
	@Test  
    public void findByGroupName() throws Exception {  
        MvcResult result = mockMvc.perform(
        			get("/group/findByGroupName")
        			.param("token", token)
        			.param("groupName", "系统管理员1")
        		)
        		.andExpect(status().isOk())// 模拟向testRest发送get请求  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
                .andReturn();// 返回执行请求的结果 
        MvcResult result2 = mockMvc.perform(
    			get("/group/findByGroupName")
    			.param("token", token)
    			.param("groupName", "系统管理员")
    		)
    		.andExpect(status().isOk())// 模拟向testRest发送get请求  
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
            .andReturn();// 返回执行请求的结果 
        System.out.println("---------------findByGroupName-----------------");   
        System.out.println(result.getResponse().getContentAsString());  
        System.out.println(result2.getResponse().getContentAsString());  
    }
	
	@Test  
    public void findByGroupNameLike() throws Exception {  
        MvcResult result = mockMvc.perform(
        			get("/group/findById/1")
        			.param("token", token)
        			.param("groupName", "系统")
        			.param("page", "0")
        			.param("size", "10")
        		)
        		.andExpect(status().isOk())// 模拟向testRest发送get请求  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
                .andReturn();// 返回执行请求的结果  
        System.out.println("---------------findByGroupNameLike-----------------");   
        System.out.println(result.getResponse().getContentAsString());  
    }
}
