package com.ekart.springbootjetty.sample.apis.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@ApiModel(value = "success_message", description = "Success message when there is success after processing the request")
@ParametersAreNonnullByDefault
public class SuccessMessage {


    @ApiModelProperty(name = "message", value = "A success message mentioning what the request has done",
            required = true)
    private final String message;

    public SuccessMessage(){
        this.message = "Function successful";
    }

    @JsonCreator
    public SuccessMessage(@JsonProperty("message") String message) {

        this.message = message;
    }

    public String getMessage() {

        return message;
    }

    @Override
    public String toString() {

        return "SuccessMessage [message=" + message + "]";
    }

}
