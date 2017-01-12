package com.ekart.springbootjetty.sample.apis.controller;

import com.ekart.springbootjetty.sample.apis.BaseIntegrationTest;
import com.ekart.springbootjetty.sample.apis.TestUtils;
import com.ekart.springbootjetty.sample.apis.dtos.*;
import com.ekart.springbootjetty.sample.apis.util.ApiUtil;
import com.ekart.springbootjetty.sample.common.util.ResponseUtil;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by nikhil.vavs on 05/01/17.
 */


public class CartManagementControllerIntegrationTest extends BaseIntegrationTest{

    private CreateUserRequest createUserRequest;


    @Before
    public void setUp(){
        createUserRequest = new CreateUserRequest(TestUtils.getRandomName(), TestUtils.genRandomContactNo());
    }

    @Test
    public void shouldReturn400WithInsufficientHeaders(){
        ResponseEntity<CreateRequestResponse> response =
                API.createRequest(CreateRequestResponse.class, API.apiHeaders());
        // error to be fixed in spring - returns 500 instead of 400. Someone raised a ticket
        assertThat(response.getStatusCode().value(), is(HttpStatus.INTERNAL_SERVER_ERROR_500));

    }

    @Test
    public void shouldReturnCreatedOkWithHeaders(){
        ResponseEntity<CreateRequestResponse> response =
        API.createRequest(CreateRequestResponse.class, API.apiHeadersNameContact());
        assertThat(response.getStatusCode().value(), is(HttpStatus.CREATED_201));
    }

    @Test
    public void shouldReturn400WithInsufficientBodyForUserWithHeaders(){
        ResponseEntity<CreateRequestResponse> response =
                API.createRequestWithBody(null, CreateRequestResponse.class, API.apiHeadersNameContact());
        assertThat(response.getStatusCode().value(), is(HttpStatus.BAD_REQUEST_400));
    }

    @Test
    public void shouldReturn400WithInsufficientBodyForUserWithoutHeaders(){
        ResponseEntity<CreateRequestResponse> response =
                API.createRequestWithBody(null, CreateRequestResponse.class, API.apiHeaders());
        assertThat(response.getStatusCode().value(), is(HttpStatus.BAD_REQUEST_400));
    }


    @Test
    public void shouldCreateUserAndReadUserDataProperlyFromHeaders(){
        createUserRequest = new CreateUserRequest();
        HttpHeaders headers = API.apiHeadersNameContact();
        ResponseEntity<CreateRequestResponse> responseFromHeader =
                API.createRequest(CreateRequestResponse.class, headers);
        assertThat(responseFromHeader.getStatusCode().value(), is(HttpStatus.CREATED_201));

        ResponseEntity<ShowUserDataResponse> userDataResponse = API.genericRequest(TestUtils.urlShowUserData,
                HttpMethod.GET, ShowUserDataResponse.class,
                API.apiHeadersUserId(responseFromHeader.getBody().getNewUserID()));
//check with headers
        assertThat(userDataResponse.getStatusCode().value(), is(HttpStatus.OK_200));
        assertEquals(userDataResponse.getBody().getUserName(), headers.get(ApiUtil.userName).get(0));
        assertEquals(userDataResponse.getBody().getContactNo(), headers.get(ApiUtil.contactNo).get(0));
    }

    @Test
    public void shouldCreateUserAndReadUserDataProperlyFromBody(){
        ResponseEntity<CreateRequestResponse> responseFromBody =
                API.createRequestWithBody(createUserRequest, CreateRequestResponse.class, API.apiHeaders());

        assertThat(responseFromBody.getStatusCode().value(), is(HttpStatus.CREATED_201));

        ResponseEntity<ShowUserDataResponse> userDataResponse = API.genericRequest(TestUtils.urlShowUserData,
                HttpMethod.GET, ShowUserDataResponse.class,
                API.apiHeadersUserId(responseFromBody.getBody().getNewUserID()));
//check with headers
        assertThat(userDataResponse.getStatusCode().value(), is(HttpStatus.OK_200));
        assertEquals(userDataResponse.getBody().getUserName(), createUserRequest.getUserName());
        assertEquals(userDataResponse.getBody().getContactNo(), createUserRequest.getContactNo());
    }

    @Test
    public void shouldReturnUnableToAddForTooHighQuantity(){
        String newUser = createUserAndReturnID();

        // Make sure that the product ID is present with the required quantity
        // TODO Add sql file to run before running these tests
        ResponseEntity<SuccessMessage> responseFromAddingToCart =
                API.genericRequest(TestUtils.urlAddToCart, HttpMethod.POST, SuccessMessage.class,
                        API.apiHeadersUserIDProductQuantity(newUser, 1, 100000));

        assertThat(responseFromAddingToCart.getStatusCode().value(), is(HttpStatus.OK_200));
        assertEquals(responseFromAddingToCart.getBody().getMessage(), ResponseUtil.insufficientQuantity);
    }


    @Test
    public void shouldAddToCartAndBeListedProperly(){
        String newUser = createUserAndReturnID();

        // Make sure that the product ID is present with the required quantity

        int productID = 1, reqQuantity = 1;

        // TODO Add sql file to run before running these tests
        ResponseEntity<SuccessMessage> responseFromAddingToCart =
                API.genericRequest(TestUtils.urlAddToCart, HttpMethod.POST, SuccessMessage.class,
                        API.apiHeadersUserIDProductQuantity(newUser, productID, reqQuantity));

        assertThat(responseFromAddingToCart.getStatusCode().value(), is(HttpStatus.OK_200));
        assertEquals(responseFromAddingToCart.getBody().getMessage(), ResponseUtil.addToCartSuccessFul);


        // test for listing cart
        ResponseEntity<ShowCartResponse> responseFromShowCart =
                API.genericRequest(TestUtils.urlListCart, HttpMethod.GET, ShowCartResponse.class,
                API.apiHeadersUserId(newUser));

        assertThat(responseFromShowCart.getStatusCode().value(), is(HttpStatus.OK_200));
        assertTrue(responseFromShowCart.getBody().getCartHash().containsKey(String.valueOf(productID)));
        assertEquals(responseFromShowCart.getBody().getCartHash().get(String.valueOf(productID)),
                String.valueOf(reqQuantity));

        // test for deleting from cart
        ResponseEntity<SuccessMessage> responseFromDeleteFromCart =
                API.genericRequest(TestUtils.urlDeleteFromCart, HttpMethod.DELETE, SuccessMessage.class,
                    API.apiHeadersUserIdProduct(newUser, productID));

        assertThat(responseFromDeleteFromCart.getStatusCode().value(), is(HttpStatus.OK_200));
        assertEquals(responseFromDeleteFromCart.getBody().getMessage(), ResponseUtil.deleteFromCartSuccessFul);

        // test list after deleting from cart
        ResponseEntity<ShowCartResponse> responseFromShowCartAfterDeletion =
                API.genericRequest(TestUtils.urlListCart, HttpMethod.GET, ShowCartResponse.class,
                        API.apiHeadersUserId(newUser));

        assertThat(responseFromShowCartAfterDeletion.getStatusCode().value(), is(HttpStatus.OK_200));
        assertTrue(!responseFromShowCartAfterDeletion.getBody().getCartHash().containsKey(String.valueOf(productID)));


    }

    private String createUserAndReturnID(){
        ResponseEntity<CreateRequestResponse> responseFromBody =
                API.createRequestWithBody(createUserRequest, CreateRequestResponse.class, API.apiHeaders());
        return responseFromBody.getBody().getNewUserID();

    }

}
