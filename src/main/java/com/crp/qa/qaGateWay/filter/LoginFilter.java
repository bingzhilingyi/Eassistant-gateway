/**
 * huangyue
 * 2018年5月14日
 */
package com.crp.qa.qaGateWay.filter;

import java.io.IOException;

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
import org.springframework.stereotype.Component;

import com.crp.qa.qaGateWay.service.inte.QaTokenService;

/**
 * @author huangyue
 * @date 2018年5月14日 下午7:03:37
 * @ClassName LoginFilter
 */
@Component
@WebFilter(urlPatterns = "/*",filterName = "loginFilter")
public class LoginFilter implements Filter{
	private final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);
	
	@Value("${TOKEN.CLIENT}")
	private String CLIENT_TOKEN;
	
	@Resource(name="qaTokenService")
	private QaTokenService qaTokenService;
	
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    	// 获得在下面代码中要用的request,response
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		//跨站请求设置，在@config文件里已经写了，这里再写是因为重定向的需要，否则重定向会报错
		servletResponse.setHeader("Access-Control-Allow-Origin", servletRequest.getHeader("Origin"));
		servletResponse.setHeader("Access-Control-Allow-Headers", "accept,content-type"); 
		servletResponse.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
		servletResponse.setHeader("Access-Control-Allow-Credentials", "true");//设置为true，可以跨域带上cookie
		servletResponse.setHeader("Access-Control-Max-Age", "3600"); //缓存过期时间
		//获取session，如果session不存在，则会创建
		//HttpSession session = servletRequest.getSession(true);
		LOGGER.info("excute start time...");
		try{
			// 获得用户请求的URI
			String path = servletRequest.getRequestURI();
			String basepath = servletRequest.getContextPath();						
			LOGGER.info("excute basepath:{},URI:{}",new Object[]{basepath,path});
			//判断token是否存在，不存在就报错
			String token = (String)servletRequest.getParameter("token");
			//以下页面不需要token即可访问
			if (path.indexOf("/Login") > -1 || path.indexOf("/error") > -1) {
				//过滤结束
		    	filterChain.doFilter(servletRequest, servletResponse);
		    	return;
			}else if(path.indexOf("/client") > -1){
				//如果是client访问，判断token是否正确，正确就通过,否则跳转到错误页面
				if(this.CLIENT_TOKEN.equals(token)) {
					//过滤结束
			    	filterChain.doFilter(servletRequest, servletResponse);
			    	return;
				} else {
					//跳到错误页
					servletResponse.sendRedirect(basepath + "/error/notoken");
					return;
				}
			}
			//如果没有token，token不匹配，则返回错误
			if(token==null||!qaTokenService.isExists(token)) {
				servletResponse.sendRedirect(basepath + "/error/notoken");
				return;
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
