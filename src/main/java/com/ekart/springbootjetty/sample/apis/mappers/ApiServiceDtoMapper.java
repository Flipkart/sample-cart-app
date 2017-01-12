package com.ekart.springbootjetty.sample.apis.mappers;

import com.ekart.springbootjetty.sample.apis.dtos.*;
import com.ekart.springbootjetty.sample.apis.dtos.AddToCartRequest;
import com.ekart.springbootjetty.sample.apis.dtos.Cart;
import com.ekart.springbootjetty.sample.apis.dtos.CreateRequestResponse;
import com.ekart.springbootjetty.sample.apis.dtos.CreateUserRequest;
import com.ekart.springbootjetty.sample.apis.dtos.DeleteFromCartRequest;
import com.ekart.springbootjetty.sample.apis.dtos.User;
import com.ekart.springbootjetty.sample.service.dtos.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@Component
@Mapper(componentModel = "spring")
public abstract class ApiServiceDtoMapper {

    public abstract CreateRequestResponse serviceToApiCreateRequestResponse(
            com.ekart.springbootjetty.sample.service.dtos.CreateRequestResponse createRequestResponse);

    public abstract com.ekart.springbootjetty.sample.service.dtos.CreateUserRequest apiToServiceCreateUserRequest(
            CreateUserRequest createUserRequest);

    public abstract com.ekart.springbootjetty.sample.service.dtos.User apiToServiceUser(User user);

    public abstract User serviceToApiUser(com.ekart.springbootjetty.sample.service.dtos.User user);

    public abstract com.ekart.springbootjetty.sample.service.dtos.Cart apiToServiceCart(Cart cart);

    public abstract Cart serviceToApiCart(com.ekart.springbootjetty.sample.service.dtos.Cart cart);

    public abstract com.ekart.springbootjetty.sample.service.dtos.AddToCartRequest apiToServiceAddToCartRequest(
            AddToCartRequest addToCartRequest
    );

    public abstract com.ekart.springbootjetty.sample.service.dtos.DeleteFromCartRequest
    apiToServiceDeleteFromCartRequest(DeleteFromCartRequest deleteFromCartRequest);

}
