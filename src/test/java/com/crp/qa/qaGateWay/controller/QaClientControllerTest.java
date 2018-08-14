package com.crp.qa.qaGateWay.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringRunner.class)
@SpringBootTest
public class QaClientControllerTest {
	
	private static final String token = "a8ce1be607bc4a1b94ab241527498f94";
	
	private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。  
	
	@Autowired  
    private WebApplicationContext wac; // 注入WebApplicationContext  

	@Before // 在测试开始前初始化工作  
    public void setup() {  
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
    } 

	@Test
	public void findTopRank() throws Exception{
		MvcResult result = mockMvc.perform(
    			get("/client/findTopRank")
    			.param("token", token).param("size", "10").param("domain", "SRM,PAY")
    		)
    		.andExpect(status().isOk())// 模拟向testRest发送get请求  
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
            .andExpect(jsonPath("$.content.size()").value(10))
            .andReturn();// 返回执行请求的结果  
	    System.out.println("---------------findAll-----------------");    
	    System.out.println(result.getResponse().getContentAsString());  
	}

	@Test
	public void evaluate() throws Exception{
		MvcResult result = mockMvc.perform(
    			post("/client/evaluate")
    			.param("token", token).param("id", "1").param("isLike", "true")
    		)
    		.andExpect(status().isOk())// 模拟向testRest发送get请求  
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8 
            .andReturn();// 返回执行请求的结果  
	    System.out.println("---------------findAll-----------------");    
	    System.out.println(result.getResponse().getContentAsString());  
	}
}
