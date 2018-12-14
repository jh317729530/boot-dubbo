package com.wenyao.configuration;

import com.wenyao.interceptor.PageInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {

    @Resource
    private PageInterceptor pageInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //分页
        registry.addInterceptor(pageInterceptor).addPathPatterns("/**");
    }
}
