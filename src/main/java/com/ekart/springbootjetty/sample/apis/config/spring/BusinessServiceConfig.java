package com.ekart.springbootjetty.sample.apis.config.spring;

import com.ekart.springbootjetty.sample.service.CartService;
import com.ekart.springbootjetty.sample.service.mappers.ServiceDalDtoMapper;
import com.ekart.springbootjetty.sample.service.mappers.ServiceDalDtoMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@Configuration
@Import(RepositoryConfig.class)
public class BusinessServiceConfig {

    @Inject
    private RepositoryConfig repositoryConfig;

    @Bean
    public ServiceDalDtoMapper serviceDalDtoMapper() {
        return new ServiceDalDtoMapperImpl();
    }

    @Bean
    public CartService cartService(){
        return new CartService(repositoryConfig.receiveRequestRepository(), serviceDalDtoMapper());
    }

}
