package com.ekart.springbootjetty.sample.service.exceptions;

import com.ekart.springbootjetty.sample.common.enums.ErrorCodes;
import com.ekart.springbootjetty.sample.common.exceptions.ValidationException;

/**
 * Created by nikhil.vavs on 11/01/17.
 */
public class InsufficientQuantityException extends ValidationException {

    private static final long serialVersionUID = 4630681574927144036L;

    public InsufficientQuantityException(String message, Throwable e) {

        super(message, ErrorCodes.INSUFFICIENT_QUANTITY, e.getCause());
    }

    public InsufficientQuantityException(String message) {

        super(message, ErrorCodes.INSUFFICIENT_QUANTITY);
    }


}

