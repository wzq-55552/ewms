package com.soft.one.ewms.business;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan(basePackages = {"com.soft.one.ewms.business.account.mapper","com.soft.one.ewms.business.permission.mapper"})
@ServletComponentScan
@SpringBootApplication
public class BusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }
}
