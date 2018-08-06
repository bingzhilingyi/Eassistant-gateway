package com.crp.qa.qaGateWay.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.crp.qa.qaGateWay.domain.dto.QaSysUserDto;
import com.crp.qa.qaGateWay.domain.dto.QaSysUserDto.UserRights;
import com.crp.qa.qaGateWay.service.inte.QaTokenService;
import com.alibaba.fastjson.JSONObject;

@Component
@Order(2)
@WebFilter(urlPatterns = "/*",filterName = "rightsFilter")
public class RightsFilter implements Filter{

	private final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);
	
	@Resource(name="qaTokenService")
	private QaTokenService qaTokenService;
	@Value("${TOKEN.CLIENT}")
	private String CLIENT_TOKEN;
	@Value("${TOKEN.INTERNAL}")
	private String INTERNAL_TOKEN;
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
	
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		// 获得在下面代码中要用的request,response
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		try{
			// 获得用户请求的URI
			String path = servletRequest.getRequestURI();
			String basepath = servletRequest.getContextPath();						
			LOGGER.info("excute basepath:{},URI:{}",new Object[]{basepath,path});
			
			//取token
			String token = (String)servletRequest.getParameter("token");
			//如果token为空或为特定token，不需要验证权限
			if(token==null || token.equals(CLIENT_TOKEN) || token.equals(INTERNAL_TOKEN)) {
				//过滤结束
		    	filterChain.doFilter(servletRequest, servletResponse);
		    	return;
			}
			
			//通过token取到用户信息
			String userStr = qaTokenService.getByToken(token);
			QaSysUserDto user = JSONObject.parseObject(userStr, QaSysUserDto.class);
			//从用户信息里取到权限信息
			Set<QaSysUserDto.UserRights> rights = user.getRights();
			Map<String,QaSysUserDto.UserRights> rightsMap = new HashMap<String,QaSysUserDto.UserRights>();
			for(QaSysUserDto.UserRights right:rights) {
				rightsMap.put(right.getRightsCode(), right);
			}
			//会被拦截的请求URI以及对应的权限编码
			//不在这些请求里的url将不会被拦截
			String[] uris = {"/user/","/group/","/tree/"}; //url
			String[] rightsCodes = {"userList","groupList","tree"}; //url对应的权限编码
			//遍历所有可能的请求，找到对应的
			for(int i=0;i<uris.length;i++) {
				if(path.indexOf(uris[i])>-1) {
					boolean isPass = false;
					UserRights nowRight = rightsMap.get(rightsCodes[i]);
					if(nowRight == null) {
						
					}else if(path.indexOf("/find")>-1) {
						
						isPass = nowRight.getRightsSearch().equals("Y");
						
					}else if(path.indexOf("/save")>-1) {
						
						isPass = nowRight.getRightsCreate().equals("Y");
						
					}else if(path.indexOf("/update")>-1) {
						
						isPass = nowRight.getRightsUpdate().equals("Y");
						
					}else if(path.indexOf("/delete")>-1) {
						
						isPass = nowRight.getRightsDelete().equals("Y");
						
					}else {
						isPass = true;
					}
					if(isPass) {
						filterChain.doFilter(servletRequest, servletResponse);
				    	return;
					}
					//无权限时报错
					servletResponse.sendRedirect(basepath + "/error/noRight");
					return;
				}
			}
			
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	@Override
    public void destroy() {

    }
}
