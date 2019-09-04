package com.tieshan.disintegrate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/3 13:54
 * @version: 1.0
 * @modified By:
 */
@Configuration
public class CustomConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/", "/auth/login");
    }
}