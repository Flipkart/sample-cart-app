package com.ekart.springbootjetty.sample.service.exceptions;

import com.ekart.springbootjetty.sample.common.enums.ErrorCodes;
import com.ekart.springbootjetty.sample.common.exceptions.ValidationException;


/**
 * Created by nikhil.vavs on 10/01/17.
 */
public class UserNotFoundException extends ValidationException {

    private static final long serialVersionUID = 4602239189657707605L;

    public UserNotFoundException(String message, Throwable e) {

        super(message, ErrorCodes.NO_USER_FOUND_ERROR, e.getCause());
    }



}
