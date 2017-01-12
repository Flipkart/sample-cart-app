package com.ekart.springbootjetty.sample.common.exceptions;


import com.ekart.springbootjetty.sample.common.enums.ErrorCodes;

public abstract class ValidationException extends BaseRuntimeException {
    private static final long serialVersionUID = -3974175123412894944L;

    protected ValidationException(String message, ErrorCodes errorCode, Object response) {
        super(message, errorCode, response);
    }

    protected ValidationException(String message, ErrorCodes errorCode) {
        super(message, errorCode);
    }

    protected ValidationException(String message, ErrorCodes errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }

    protected ValidationException(String message, ErrorCodes errorCode, Object response, Throwable cause) {
        super(message, errorCode, response, cause);
    }
}
