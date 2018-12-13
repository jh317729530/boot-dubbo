package com.wenyao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.wenyao.mapper")
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ProviderApplication.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .run(args);
    }
}
