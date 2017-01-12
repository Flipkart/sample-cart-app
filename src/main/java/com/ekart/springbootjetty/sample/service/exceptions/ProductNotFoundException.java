package com.ekart.springbootjetty.sample.service.exceptions;

import com.ekart.springbootjetty.sample.common.enums.ErrorCodes;
import com.ekart.springbootjetty.sample.common.exceptions.ValidationException;

/**
 * Created by nikhil.vavs on 11/01/17.
 */
public class ProductNotFoundException extends ValidationException {

    private static final long serialVersionUID = 2711447370800006792L;

    public ProductNotFoundException(String message, Throwable e){
        super(message, ErrorCodes.NO_PRODUCT_FOUND, e.getCause());
    }

    public ProductNotFoundException(String message){
        super(message, ErrorCodes.NO_PRODUCT_FOUND);
    }


}
