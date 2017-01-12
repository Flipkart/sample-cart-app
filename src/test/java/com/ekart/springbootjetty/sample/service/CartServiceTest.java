package com.ekart.springbootjetty.sample.service;

import com.ekart.springbootjetty.sample.dal.repository.CartServiceRepository;
import com.ekart.springbootjetty.sample.service.dtos.User;
import com.ekart.springbootjetty.sample.service.mappers.ServiceDalDtoMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private ServiceDalDtoMapper serviceDalDtoMapper;

    @Mock
    private CartServiceRepository cartServiceRepository;

    private CartService service;

    @Before
    public void setUp(){
        service = new CartService(cartServiceRepository, serviceDalDtoMapper);
    }

    @Test(expected = NullPointerException.class)
    public void shouldRaiseExceptionIfReceiveRequestRepositoryIsNull(){
        new CartService(null, serviceDalDtoMapper);
    }

    @Test(expected = NullPointerException.class)
    public void shouldRaiseExceptionIfServiceDalDtoMappperIsNull(){
        new CartService(cartServiceRepository, null);
    }

//    @Test
//    public void shouldReturnInsufficientQuanityIfTriedToAddMoreThanAvailable(){
//        int userID = 8;
//        int productID = 1;
//        int reqQuantity = 1000;
//        when(handleRequest.addToCart(userID, productID, reqQuantity)).
//                thenReturn(ResponseUtil.insufficientQuantity);
//
//        String response = service.addToCart(userID, productID, reqQuantity);
//        assertTrue("insufficient quantity", response.equals(ResponseUtil.insufficientQuantity));
//        verify(handleRequest).addToCart(userID, productID, reqQuantity);
//    }

//    @Test
//    public void createUserTest(){
//        String userName = "t1", contactNo = "n1", responseMessage = "adfa";
//        when(handleRequest.createUser(userName, contactNo)).thenReturn(responseMessage);
//        CreateRequestResponse mockResponse = new CreateRequestResponse(
//                handleRequest.createUser(userName, contactNo));
//        CreateRequestResponse actualResponse = service.createUser(userName, contactNo);
//        Assert.assertEquals(actualResponse, mockResponse);
//    }

    @Test
    public void getUserDataTest(){
        int userID = 1;
        String userName = "userName";
        String number = "number";
        com.ekart.springbootjetty.sample.dal.models.User dalUser =
                new com.ekart.springbootjetty.sample.dal.models.User(userName, number);
        User user = new User(dalUser.getId(), userName, number);

        when (cartServiceRepository.showUserData(userID)).thenReturn(dalUser);
        when(serviceDalDtoMapper.dalToServiceUser(dalUser)).thenReturn(user);

        User mockUser = service.getUserData(userID);
        Assert.assertTrue(mockUser.getContactNo() == user.getContactNo());
        Assert.assertTrue(mockUser.getUserName() == user.getUserName());
        Assert.assertTrue(mockUser.getId() == user.getId());
    }

}
