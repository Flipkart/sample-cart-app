package com.ekart.springbootjetty.sample.service.exceptions;

import com.ekart.springbootjetty.sample.common.enums.ErrorCodes;
import com.ekart.springbootjetty.sample.common.exceptions.ValidationException;

/**
 * Created by nikhil.vavs on 11/01/17.
 */
public class ProductNotInCartException extends ValidationException {

    public ProductNotInCartException(String message){
        super(message, ErrorCodes.PRODUCT_NOT_IN_CART);
    }

}
