package com.ekart.springbootjetty.sample.service.mappers;

import com.ekart.springbootjetty.sample.service.dtos.Cart;
import com.ekart.springbootjetty.sample.service.dtos.CreateUserRequest;
import com.ekart.springbootjetty.sample.service.dtos.Product;
import com.ekart.springbootjetty.sample.service.dtos.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@Component
@Mapper(componentModel = "spring")
public abstract class ServiceDalDtoMapper {

    public abstract com.ekart.springbootjetty.sample.dal.models.User serviceToDalUser(User user);

    public abstract User dalToServiceUser(com.ekart.springbootjetty.sample.dal.models.User user);

    public abstract com.ekart.springbootjetty.sample.dal.models.Cart serviceToDalCart(Cart cart);

    public abstract Cart dalToServiceCart(com.ekart.springbootjetty.sample.dal.models.Cart cart);

    public abstract com.ekart.springbootjetty.sample.dal.dtos.CreateUserRequest
                serviceToDalCreateUserRequest(CreateUserRequest createUserRequest);

    public abstract Product dalToServiceProduct(com.ekart.springbootjetty.sample.dal.models.Product product);

}
