/**
 * 
 */
package com.ekart.springbootjetty.sample.apis.dtos;

import javax.annotation.ParametersAreNonnullByDefault;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author vijay.daniel
 *
 */
@ApiModel(value = "error_message", description = "Error message when there is a failure to process the request")
@ParametersAreNonnullByDefault
public class ErrorMessage {

   @ApiModelProperty(name = "message", value = "An error message containing the reason for the failure",
         required = true)
   private final String message;

   @JsonCreator
   public ErrorMessage(@JsonProperty("message") String message) {

      this.message = message;
   }

   public String getMessage() {

      return message;
   }

   @Override
   public String toString() {

      return "ErrorMessage [message=" + message + "]";
   }
}
