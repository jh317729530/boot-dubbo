package com.wenyao.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenyao.combiz.service.JacksonService;
import com.wenyao.interceptor.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {

    @Resource
    private PageInterceptor pageInterceptor;

    @Resource
    private JacksonService jacksonService;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //分页
        registry.addInterceptor(pageInterceptor).addPathPatterns("/**");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(objectMapper());
        jsonConverter.setSupportedMediaTypes(supportedMediaTypes());
        converters.add(jsonConverter);
        super.configureMessageConverters(converters);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return jacksonService.getObjectMapper(true);
    }

    @Bean
    public List<MediaType> supportedMediaTypes() {
        List<MediaType> supportedMediaTypeList = new ArrayList<>();
        supportedMediaTypeList.add(MediaType.valueOf("text/json;charset=UTF-8"));
        supportedMediaTypeList.add(MediaType.valueOf("text/plain;charset=UTF-8"));
        supportedMediaTypeList.add(MediaType.valueOf("application/json; charset=UTF-8"));
        supportedMediaTypeList.add(MediaType.valueOf("application/x-www-form-urlencoded; charset=UTF-8"));
        return supportedMediaTypeList;
    }
}
