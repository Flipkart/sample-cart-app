package com.ekart.springbootjetty.sample.apis.controller;

import static org.unitils.reflectionassert.ReflectionAssert.*;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.ekart.springbootjetty.sample.apis.dtos.ErrorMessage;
import com.google.common.collect.Lists;

import static org.mockito.Mockito.*;;

/**
 * @author vijay.daniel
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseControllerTest {

   private BaseController controller;

   @Mock
   private HttpServletResponse response;

   @Mock
   private BindingResult bindingResult;

   private MethodParameter parameter;

   private MethodArgumentNotValidException exception;

   @Before
   public void setUp() throws Exception {

      controller = new BaseController();
      parameter = new MethodParameter(BaseControllerTest.class.getMethod("testMethod", int.class), 0, 0);
      exception = new MethodArgumentNotValidException(parameter, bindingResult);
   }

   @Test
   public void shouldSetResponseStatusValueIfExceptionFoundInExceptionMap() {

      ErrorMessage message = controller.handleException(new ValidationException("Exception message"), response);

      assertReflectionEquals(new ErrorMessage("Exception message"), message);
      verify(response).setStatus(BaseController.HTTP_BAD_REQUEST);
   }

   @Test
   public void shouldSetDefaultResponseStatusIfExceptionNotFoundInExceptionMap() {

      ErrorMessage message = controller.handleException(new IllegalStateException("Exception message"), response);

      assertReflectionEquals(new ErrorMessage("Exception message"), message);
      verify(response).setStatus(BaseController.HTTP_INTERNAL_SERVER_ERROR);
   }

   @Test
   public void shouldReturnErrorMessageForMethodArgumentNotValidExceptionWithNoMatchingCode() throws Exception {

      List<ObjectError> errors = Lists.newArrayList(new ObjectError("obj1", "obj1 is null"));

      when(bindingResult.getAllErrors()).thenReturn(errors);

      ErrorMessage message = controller.handleException(exception, response);

      assertReflectionEquals(new ErrorMessage("[obj1: obj1 is null]"), message);
      verify(response).setStatus(BaseController.HTTP_BAD_REQUEST);
   }

   @Test
   public void shouldReturnModifiedErrorMessageForMethodArgumentNotValidException() throws Exception {

      List<ObjectError> errors = Lists.newArrayList(
            new ObjectError("obj1", new String[] { null, "", "something about obj1 is wrong" }, null, "obj1 is null"));

      when(bindingResult.getAllErrors()).thenReturn(errors);

      ErrorMessage message = controller.handleException(exception, response);

      assertReflectionEquals(new ErrorMessage("[something about obj1 is wrong: obj1 is null]"), message);
      verify(response).setStatus(BaseController.HTTP_BAD_REQUEST);
   }

   @Test
   public void shouldReturnMessageForMethodArgumentNotValidExceptionButNoMatchingCode() throws Exception {

      List<ObjectError> errors = Lists.newArrayList(
            new ObjectError("obj1", new String[] { null, "", "something about is wrong" }, null, "obj1 is null"));

      when(bindingResult.getAllErrors()).thenReturn(errors);

      ErrorMessage message = controller.handleException(exception, response);

      assertReflectionEquals(new ErrorMessage("[obj1: obj1 is null]"), message);
      verify(response).setStatus(BaseController.HTTP_BAD_REQUEST);
   }

   @Test
   public void shouldReturnErrorMessageForMethodArgumentNotValidExceptionWithEmptyObjectName() throws Exception {

      List<ObjectError> errors = Lists.newArrayList(new ObjectError("", "obj1 is null"));

      when(bindingResult.getAllErrors()).thenReturn(errors);

      ErrorMessage message = controller.handleException(exception, response);

      assertReflectionEquals(new ErrorMessage("[: obj1 is null]"), message);
      verify(response).setStatus(BaseController.HTTP_BAD_REQUEST);
   }

   public void testMethod(int param) {

      // Test method for Method parameter in MethodArgumentNotValidException
   }
}
