package com.ekart.springbootjetty.sample.apis.config.spring;

import com.ekart.springbootjetty.sample.apis.controller.CartManagementController;
import com.ekart.springbootjetty.sample.dal.repository.mysql.MySqlUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;


@Configuration
@ComponentScan({ "com.ekart.springbootjetty.sample.apis" })
@Import({ EnvironmentConfig.class, BusinessServiceConfig.class, ApiMapperConfig.class})
public class ApplicationConfig {

   // Reference:
   // http://docs.spring.io/spring-javaconfig/docs/1.0.0.M4/reference/html/ch04s02.html
   //Add your configs here

    @Inject
    private BusinessServiceConfig businessServiceConfig;

    @Inject
    private ApiMapperConfig apiMapperConfig;

    @Bean
    public CartManagementController cartManagementController(){
        return new CartManagementController(businessServiceConfig.cartService(), apiMapperConfig.apiServiceDtoMapper());
    }
}
