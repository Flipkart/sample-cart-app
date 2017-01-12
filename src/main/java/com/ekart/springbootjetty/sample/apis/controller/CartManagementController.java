package com.ekart.springbootjetty.sample.apis.controller;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.ekart.springbootjetty.sample.apis.dtos.*;
import com.ekart.springbootjetty.sample.apis.mappers.ApiServiceDtoMapper;
import com.ekart.springbootjetty.sample.apis.util.ApiUtil;
import com.ekart.springbootjetty.sample.common.util.ResponseUtil;
import com.ekart.springbootjetty.sample.service.CartService;
import com.ekart.springbootjetty.sample.service.exceptions.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@Api(protocols = "http")
@ThreadSafe
@RestController
@RequestMapping("/api")
@ParametersAreNonnullByDefault
public class CartManagementController extends BaseController{

    private final ApiServiceDtoMapper mapper;
    private final CartService cartService;

    public CartManagementController(CartService cartService, ApiServiceDtoMapper mapper){
        this.cartService = cartService;
        this.mapper = mapper;
    }


    // just a header test - Do not use this
//    @ApiOperation(nickname = "create-receive-request", value = "Creates New User",
//            notes = "Creates a new user and stores the details")
//    @ApiResponses({
//            @ApiResponse(code = HttpStatus.CREATED_201, message = "New user has been created successfully",
//                    response = CreateRequestResponse.class),
//            @ApiResponse(code = HttpStatus.BAD_REQUEST_400,
//                    message = "Invalid request", response = ErrorMessage.class),
//            @ApiResponse(code = HttpStatus.FORBIDDEN_403,
//                    message = "Client is not authorised to make this call.",
//                    response = ErrorMessage.class),
//            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "An internal server error occurred",
//                    response = ErrorMessage.class) })
//    @Timed
//    @ExceptionMetered
//    @RequestMapping(path = "/createUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
//    @Transactional
//    public ResponseEntity<CreateRequestResponse> createUser(
//            @NotNull @RequestHeader(value = ApiUtil.userName) String userName ,
//            @RequestHeader(ApiUtil.contactNo) String contactNo) {
//        if (userName == null)  badRequest("Bad request");
//        return created(
//                mapper.serviceToApiCreateRequestResponse(cartService.createUser(userName, contactNo)));
//    }



    @ApiOperation(nickname = "create-receive-request", value = "Creates New User",
            notes = "Creates a new user and stores the details")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.CREATED_201, message = "New user has been created successfully",
                    response = CreateRequestResponse.class),
            @ApiResponse(code = HttpStatus.BAD_REQUEST_400,
                    message = "Invalid request", response = ErrorMessage.class),
            @ApiResponse(code = HttpStatus.FORBIDDEN_403,
                    message = "Client is not authorised to make this call.",
                    response = ErrorMessage.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "An internal server error occurred",
                    response = ErrorMessage.class) })
    @Timed
    @ExceptionMetered
    @RequestMapping(path = "/createUserFromBody", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<CreateRequestResponse> createUserFromBody(
            @NotNull @Valid @RequestBody CreateUserRequest createUserRequest
    ) {

        return created(mapper.serviceToApiCreateRequestResponse(
                cartService.createUser(
                        mapper.apiToServiceCreateUserRequest(createUserRequest)
                )));
    }



    @ApiOperation(nickname = "show-user-data", value = "Shows User Info ",
            notes = "Shows user info")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.OK_200, message = "Fetched User Data Successfully",
                    response = ShowUserDataResponse.class),
            @ApiResponse(code = HttpStatus.BAD_REQUEST_400,
                    message = "Invalid request", response = ErrorMessage.class),
            @ApiResponse(code = HttpStatus.FORBIDDEN_403,
                    message = "Client is not authorised to make this call.",
                    response = ErrorMessage.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "An internal server error occurred",
                    response = ErrorMessage.class) })
    @Timed
    @ExceptionMetered
    @RequestMapping(path = "/showUserData/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<ShowUserDataResponse> showUserData(
            @NotNull @RequestParam(ApiUtil.userID) int userID){
            return ok(new ShowUserDataResponse(mapper.serviceToApiUser(cartService.getUserData(userID))));
    }



    @ApiOperation(nickname = "show-cart", value = "Shows cart contents of a user",
            notes = "Fetches the cart data of the given user")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.OK_200, message = "Fetched Cart Data Successfully",
                    response = ShowCartResponse.class),
            @ApiResponse(code = HttpStatus.BAD_REQUEST_400,
                    message = "Invalid request", response = ErrorMessage.class),
            @ApiResponse(code = HttpStatus.FORBIDDEN_403,
                    message = "Client is not authorised to make this call.",
                    response = ErrorMessage.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "An internal server error occurred",
                    response = ErrorMessage.class) })
    @Timed
    @ExceptionMetered
    @RequestMapping(path = "/showCart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<ShowCartResponse> showCart(
            @NotNull @RequestParam(ApiUtil.userID) int userID) {
        return ok( new ShowCartResponse(mapper.serviceToApiCart(cartService.showCart(userID))));
    }


    @ApiOperation(nickname = "add-product-to-cart", value = "Add product to cart",
            notes = "Adds product with the given quanity to the cart of the given user. " +
                    "If the same product is already present the quantity is updated")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.OK_200, message = "Product added to cart successfully",
                    response = SuccessMessage.class),
            @ApiResponse(code = HttpStatus.BAD_REQUEST_400,
                    message = "Invalid request", response = ErrorMessage.class),
            @ApiResponse(code = HttpStatus.FORBIDDEN_403,
                    message = "Client is not authorised to make this call.",
                    response = ErrorMessage.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "An internal server error occurred",
                    response = ErrorMessage.class) })
    @Timed
    @ExceptionMetered
    @RequestMapping(path = "/addToCart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<SuccessMessage> addToCart(
            @NotNull @Valid @RequestBody AddToCartRequest addToCartRequest) {
            cartService.addToCart(mapper.apiToServiceAddToCartRequest(addToCartRequest));
        return ok(new SuccessMessage());
    }



    @ApiOperation(nickname = "delete-product-from-cart", value = "Deletes product from cart",
            notes = "Deletes the product from the cart of the given user")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.OK_200, message = "Product deleted from cart successfully",
                    response = SuccessMessage.class),
            @ApiResponse(code = HttpStatus.BAD_REQUEST_400,
                    message = "Invalid request", response = ErrorMessage.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "An internal server error occurred",
                    response = ErrorMessage.class) })
    @Timed
    @ExceptionMetered
    @RequestMapping(path = "/deleteFromCart", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<SuccessMessage> deleteFromCart(
            @NotNull @Valid @RequestBody DeleteFromCartRequest deleteFromCartRequest) {
        cartService.deleteFromCart(mapper.apiToServiceDeleteFromCartRequest(deleteFromCartRequest));
        return ok(new SuccessMessage());
    }

    @ApiOperation(nickname = "show-cart-value", value = "Shows Cart Value",
            notes = "Shows the total value of the cart")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.OK_200, message = "Fetched Cart Value Successfully",
                    response = SuccessMessage.class),
            @ApiResponse(code = HttpStatus.BAD_REQUEST_400,
                    message = "Invalid request", response = ErrorMessage.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "An internal server error occurred",
                    response = ErrorMessage.class) })
    @Timed
    @ExceptionMetered
    @RequestMapping(path = "/showCartValue", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<SuccessMessage> showCartValue(
            @NotNull @RequestParam(ApiUtil.userID) int userID) {
        return ok(new SuccessMessage(String.valueOf(cartService.showCartValue(userID))));
    }


}
