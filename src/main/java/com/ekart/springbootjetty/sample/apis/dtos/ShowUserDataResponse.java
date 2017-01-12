package com.ekart.springbootjetty.sample.apis.dtos;

import com.ekart.springbootjetty.sample.apis.dtos.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@ApiModel(description = "Show User Data Response DTO")
public class ShowUserDataResponse {

    @ApiModelProperty(name = "user_id", value = "Id of the user", required = true)
    @JsonProperty(value = "user_id")
    private String userID;

    @ApiModelProperty(name = "user_name", value = "name of the user", required = true)
    @JsonProperty(value = "user_name")
    private String userName;

    @ApiModelProperty(name = "contact_no", value = "user contact number", required = false)
    @JsonProperty(value = "contact_no")
    private String contactNo;

    public ShowUserDataResponse(User user){
        if (user == null) return;
        this.userID = String.valueOf(user.getId());
        this.userName = user.getUserName();
        this.contactNo = "NA";
        if (user.getContactNo()!=null){
            this.contactNo = user.getContactNo();
        }
    }

    public ShowUserDataResponse() {
    }

    public ShowUserDataResponse(String userID, String userName, String contactNo) {
        this.userID = userID;
        this.userName = userName;
        this.contactNo = contactNo;
    }

    public ShowUserDataResponse(String userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
    public String toString(){
        return "UserData: {" + "id: " + userID + ",\n" +
                "name: " + userName + ",\n"
                +"contactNo: " + contactNo  + "}";
    }

}
