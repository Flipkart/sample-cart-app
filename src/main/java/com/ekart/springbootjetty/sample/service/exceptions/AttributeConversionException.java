package com.ekart.springbootjetty.sample.service.exceptions;

import com.ekart.springbootjetty.sample.common.enums.ErrorCodes;
import com.ekart.springbootjetty.sample.common.exceptions.ValidationException;

/**
 * Created by nikhil.vavs on 04/01/17.
 */
public class AttributeConversionException extends ValidationException {

    private static final long serialVersionUID = -4023311221006745779L;

    public AttributeConversionException(String message, Throwable e) {

        super(message, ErrorCodes.VALIDATION_ERROR, e.getCause());
    }

}
