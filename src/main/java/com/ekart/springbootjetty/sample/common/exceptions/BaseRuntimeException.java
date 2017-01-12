package com.ekart.springbootjetty.sample.common.exceptions;


import com.ekart.springbootjetty.sample.common.enums.ErrorCodes;

public abstract class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -3974175123412894944L;
    private final ErrorCodes errorCode;
    private final Object response;

    protected BaseRuntimeException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.response = null;
    }

    protected BaseRuntimeException(String message, ErrorCodes errorCode, Object response) {
        super(message);
        this.errorCode = errorCode;
        this.response = response;
    }

    protected BaseRuntimeException(String message, ErrorCodes errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.response = null;
    }

    protected BaseRuntimeException(String message, ErrorCodes errorCode, Object response, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.response = response;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public Object getResponse() {
        return response;
    }
}
