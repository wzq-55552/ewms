package com.soft.one.ewms.security.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.soft.one.ewms.domain.mappers")
public class SecurityOauthJarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityOauthJarApplication.class,args);
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
