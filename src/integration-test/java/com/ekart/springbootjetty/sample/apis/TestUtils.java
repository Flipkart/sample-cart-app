package com.ekart.springbootjetty.sample.apis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ekart.springbootjetty.sample.apis.dtos.ErrorMessage;

import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * @author vijay.daniel
 *
 */
public final class TestUtils {

   public static final String urlShowUserData = "/api/showUserData";
   public static final String urlAddToCart = "/api/addToCart";
   public static final String urlListCart = "/api/getCart";
   public static final String urlDeleteFromCart = "/api/deleteFromCart";

   private TestUtils() {
   }

   public static void assertBadRequest(ResponseEntity<ErrorMessage> responseEntity) {

      assertThat(responseEntity.getStatusCode().value(), is(HttpStatus.BAD_REQUEST.value()));
   }

   public static <T> void assertResponse(ResponseEntity<T> actualResponse, HttpStatus expectedStatus, T expectedBody) {

      assertThat(actualResponse.getStatusCode(), is(expectedStatus));
      assertThat(actualResponse.getBody(), is(expectedBody));
   }


   public static String getRandomName(){
      return "testUser" + ThreadLocalRandom.current().nextInt(1, 1000000);
   }

   public static String genRandomContactNo(){
      return "testContact" + ThreadLocalRandom.current().nextInt(1, 1000000);
   }


}
