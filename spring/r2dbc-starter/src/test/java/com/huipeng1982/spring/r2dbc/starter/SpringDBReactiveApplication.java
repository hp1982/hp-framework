package com.huipeng1982.spring.r2dbc.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.huipeng1982.spring.r2dbc.starter")
public class SpringDBReactiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDBReactiveApplication.class, args);
    }
}
