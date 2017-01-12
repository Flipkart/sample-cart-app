package com.ekart.springbootjetty.sample.common.exceptions;

import com.ekart.springbootjetty.sample.common.enums.ErrorCodes;

/**
 * Created by sonikumari.b on 24/10/16.
 */
public class ResourceNotFoundException extends BaseRuntimeException {

   private static final long serialVersionUID = -2776532146412894944L;

   public ResourceNotFoundException(String message, ErrorCodes errorCode, Object response) {
      super(message, errorCode, response);
   }

   public ResourceNotFoundException(String message, ErrorCodes errorCode) {
      super(message, errorCode);
   }

   public ResourceNotFoundException(String message, ErrorCodes errorCode, Throwable cause) {
      super(message, errorCode, cause);
   }

   public ResourceNotFoundException(String message, ErrorCodes errorCode, Object response, Throwable cause) {
      super(message, errorCode, response, cause);
   }
}
