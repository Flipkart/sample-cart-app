package com.ekart.springbootjetty.sample.apis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by nikhil.vavs on 04/01/17.
 */

@ApiModel(description = "Create User Request Response DTO")
public class CreateRequestResponse {

    @ApiModelProperty(name = "new_user_id", value = "Id of the new user created", required = true)
    @JsonProperty(value = "new_user_id")
    private String newUserID;

    public CreateRequestResponse() {

    }

    public CreateRequestResponse(String newUserID) {
        this.newUserID = newUserID;
    }

    public String getNewUserID() {
        return newUserID;
    }

    public void setNewUserID(String newUserID) {
        this.newUserID = newUserID;
    }

    @Override
    public String toString() {
        return "Create User Response{ new user ID: " + newUserID + "}";
    }

}
