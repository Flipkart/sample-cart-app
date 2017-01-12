package com.ekart.springbootjetty.sample.apis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by nikhil.vavs on 04/01/17.
 */

@ApiModel(description = "Create User Request DTO")
public class CreateUserRequest {

    @NotNull(message = "{userRequest.userName.notnull}")
    @ApiModelProperty(name = "user_name", value = "user name")
    @JsonProperty(value = "user_name", required = true)
    private String userName;

    @ApiModelProperty(name = "contact_no", value = "contact number")
    @JsonProperty(value = "contact_no", required = false)
    private String contactNo;

    public CreateUserRequest() {

    }

    public CreateUserRequest(String userName, String contactNo) {
        this.userName = userName;
        this.contactNo = contactNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "Create User Request{ userName: " + userName + " contact no: " + contactNo + "}";
    }

}