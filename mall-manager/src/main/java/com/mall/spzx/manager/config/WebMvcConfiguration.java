package com.mall.spzx.manager.config;

import com.mall.spzx.manager.interceptor.LoginAuthInterceptor;
import com.mall.spzx.manager.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    LoginAuthInterceptor loginAuthInterceptor;

    @Autowired
    UserProperties userProperties;

    // config cors
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //添加路由规则
                .allowCredentials(true) //允许在跨域时传递cookie
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*"); //允许所有请求头
    }

    // add interceptor
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(loginAuthInterceptor)
////                .excludePathPatterns(userProperties.getNoAuthUrls())
////                .addPathPatterns("/**");
//    }
}
