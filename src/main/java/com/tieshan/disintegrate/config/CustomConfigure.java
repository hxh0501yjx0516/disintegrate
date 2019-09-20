package com.tieshan.disintegrate.config;

import com.tieshan.disintegrate.resolver.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/3 13:54
 * @version: 1.0
 * @modified By: ren lei
 *              customInterceptor改为让spring管理
 */
@Configuration
public class CustomConfigure implements WebMvcConfigurer {

    @Autowired
    private CustomInterceptor customInterceptor;
    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/auth/login");
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }
}