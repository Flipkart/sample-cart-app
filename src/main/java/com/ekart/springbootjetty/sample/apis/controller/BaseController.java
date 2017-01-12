/**
 * 
 */
package com.ekart.springbootjetty.sample.apis.controller;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import com.ekart.springbootjetty.sample.service.exceptions.InsufficientQuantityException;
import com.ekart.springbootjetty.sample.service.exceptions.ProductNotFoundException;
import com.ekart.springbootjetty.sample.service.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekart.springbootjetty.sample.apis.dtos.ErrorMessage;
import com.ekart.springbootjetty.sample.apis.util.ClassValueLookupMap;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;

/**
 * @author vijay.daniel
 *
 */
public class BaseController {

   private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

   private static final int EXCEPTION_CACHE_SIZE = 256;

   protected static final int HTTP_OK = 200;
   protected static final int HTTP_CREATED = 201;
   protected static final int HTTP_INTERNAL_SERVER_ERROR = 500;
   protected static final int HTTP_BAD_REQUEST = 400;
   protected static final int HTTP_CONFLICT = 409;

   /**
    * Because almost all the exceptions will be thrown by your business logic,
    * it doesn't make sense to decorate the business logic exceptions with
    * translation annotations. Add any translations you want to this central map
    */
   private static final ClassValueLookupMap<Exception, HttpStatus> EXCEPTION_MAP =
         new ClassValueLookupMap<>(ImmutableMap.<Class<? extends Exception>, HttpStatus>builder()
               .put(ValidationException.class, HttpStatus.BAD_REQUEST)
               .put(UserNotFoundException.class, HttpStatus.BAD_REQUEST)
               .put(InsufficientQuantityException.class, HttpStatus.NOT_ACCEPTABLE)
               .put(ProductNotFoundException.class, HttpStatus.BAD_REQUEST)
               .put(MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST)
               .put(HttpMessageConversionException.class, HttpStatus.BAD_REQUEST)
               .put(ArithmeticException.class, HttpStatus.BAD_REQUEST).build());

   private static final LoadingCache<Class<? extends Exception>, HttpStatus> EXCEPTION_MAP_CACHE =
         CacheBuilder.newBuilder().maximumSize(EXCEPTION_CACHE_SIZE)
               .build(new CacheLoader<Class<? extends Exception>, HttpStatus>() {

                  @Override
                  public HttpStatus load(Class<? extends Exception> exceptionClass) {

                     return EXCEPTION_MAP.getOrDefault(exceptionClass, HttpStatus.INTERNAL_SERVER_ERROR);
                  }
               });

   @ExceptionHandler(Exception.class)
   @ResponseBody
   public ErrorMessage handleException(Exception e, HttpServletResponse response) {

      LOGGER.error("Error while processing request", e);
      response.setStatus(EXCEPTION_MAP_CACHE.getUnchecked(e.getClass()).value());

      String message;
      if (e instanceof MethodArgumentNotValidException) {

         MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
         StringBuilder sb = new StringBuilder();
         for (ObjectError error : me.getBindingResult().getAllErrors()) {

            String errorMessagePrefix = error.getObjectName();
            if (!StringUtils.isEmpty(error.getObjectName()) && error.getCodes() != null
                  && error.getCode().length() > 0) {

               Optional<String> matchingCode = Arrays.asList(error.getCodes()).stream()
                     .filter(c -> !StringUtils.isEmpty(c) && c.contains(error.getObjectName())).findFirst();
               if (matchingCode.isPresent()) { // NOSONAR

                  errorMessagePrefix = matchingCode.get();
               }
            }

            sb.append("[").append(errorMessagePrefix).append(": ").append(error.getDefaultMessage()).append("]");
         }
         message = sb.toString();

      } else {

         message = e.getMessage();
      }

      return new ErrorMessage(message);
   }

   protected static final ResponseEntity<ErrorMessage> badRequest(String errorMessage) {

      return new ResponseEntity<>(new ErrorMessage(errorMessage), HttpStatus.BAD_REQUEST);
   }

   protected static final <T> ResponseEntity<T> ok(T value) {

      return new ResponseEntity<>(value, HttpStatus.OK);
   }

   protected static final <T> ResponseEntity<T> created(T value) {

      return new ResponseEntity<>(value, HttpStatus.CREATED);
   }
}
