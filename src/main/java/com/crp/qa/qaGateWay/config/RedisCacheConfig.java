package com.crp.qa.qaGateWay.config;
///**
// * huangyue
// * 2018年5月18日
// */
//package com.crp.qa.qaAuthorization.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//
//
///**
// * @author huangyue
// * @date 2018年5月18日 上午11:45:38
// * @ClassName RedisCacheConfig
// */
//@Configuration
//public class RedisCacheConfig extends CachingConfigurerSupport {
//	Logger logger = LoggerFactory.getLogger(RedisCacheConfig.class);
//	
//	@Bean
//    public CacheManager cacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
//        //RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//		RedisCacheManager cacheManager = new RedisCacheManager(cacheWriter, defaultCacheConfiguration)
//
//        //默认超时时间,单位秒
//        cacheManager.setDefaultExpiration(3000);
//		cacheManager.
//        //根据缓存名称设置超时时间,0为不超时
//        Map<String,Long> expires = new ConcurrentHashMap<>();
//        cacheManager.setExpires(expires);
//
//        return cacheManager;
//    }
//
//}
