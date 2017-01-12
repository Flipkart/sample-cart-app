package com.ekart.springbootjetty.sample.apis.config.spring;

import com.ekart.springbootjetty.sample.apis.mappers.ApiServiceDtoMapper;
import com.ekart.springbootjetty.sample.apis.mappers.ApiServiceDtoMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiMapperConfig {

   @Bean
   public ApiServiceDtoMapper apiServiceDtoMapper() {

      return new ApiServiceDtoMapperImpl();
   }
}
