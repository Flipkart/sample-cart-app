package com.ekart.springbootjetty.sample.common.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by nikhil.vavs on 04/01/17.
 */
@ApiModel(value = "ErrorCodes", description = "Error codes returned in ErrorMessage")
public enum ErrorCodes {

    @ApiModelProperty("An unknown error occurred while processing this request")
    UNKNOWN_ERROR,

    @ApiModelProperty("The validations for the request failed")
    VALIDATION_ERROR,

    @ApiModelProperty("No such user found")
    NO_USER_FOUND_ERROR,

    @ApiModelProperty("No product found")
    NO_PRODUCT_FOUND,

    @ApiModelProperty("Insufficient quantity")
    INSUFFICIENT_QUANTITY,

    @ApiModelProperty("Product not in cart")
    PRODUCT_NOT_IN_CART

}