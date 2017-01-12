package com.ekart.springbootjetty.sample.apis.config.spring;

import com.ekart.springbootjetty.sample.dal.repository.Impl.CartServiceRepositoryImpl;
import com.ekart.springbootjetty.sample.dal.repository.CartServiceRepository;
import com.ekart.springbootjetty.sample.dal.repository.mysql.MySqlCartRepository;
import com.ekart.springbootjetty.sample.dal.repository.mysql.MySqlUserRepository;
import com.ekart.springbootjetty.sample.dal.repository.mysql.MySqlProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@Configuration
@Import({MySqlDatabaseConfig.class, EnvironmentConfig.class})
public class RepositoryConfig {

    @Inject
    private MySqlUserRepository mySqlUserRepository;

    @Inject
    private MySqlCartRepository mySqlCartRepository;

    @Inject
    private MySqlProductRepository mySqlProductRepository;

    @Bean
    public CartServiceRepository receiveRequestRepository(){
        return new CartServiceRepositoryImpl(mySqlUserRepository, mySqlCartRepository, mySqlProductRepository);
    }

}
