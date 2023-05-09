package com.example.newlistbook.config;

import com.example.newlistbook.interceptor.LoginCheckinterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 李圣
 * @Date:2023/4/24 18:56
 * @Version: 1.0
 */

//Configuration:当前类为配置类
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckinterceptor loginCheckinterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckinterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
