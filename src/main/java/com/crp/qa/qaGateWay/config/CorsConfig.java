package com.crp.qa.qaGateWay.config;  

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * 跨站请求设置
 * 也可以在filter里设置
 * @configuration相当于spring里的@beans
 * @author huangyue
 * @date 2018年5月14日 下午8:45:38
 * @ClassName CorsConfig
 */
@Configuration  
public class CorsConfig implements WebMvcConfigurer {  

    @Override  
    public void addCorsMappings(CorsRegistry registry) {  
        registry.addMapping("/**")   //跨站请求匹配的路径
                .allowedOrigins("*")   //可以请求的ip
                .allowCredentials(true)   //是否允许客户端发送凭证
                .allowedMethods("GET", "POST", "DELETE", "PUT")  
                .maxAge(3600);  //返回值默认缓存时间
    }  

} 