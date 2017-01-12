package com.ekart.springbootjetty.sample.apis.dtos;

import com.ekart.springbootjetty.sample.apis.dtos.Cart;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@ApiModel(description = "Show Cart Data Response DTO")
public class ShowCartResponse {

    @ApiModelProperty(name = "user_id", value = "Id of the user", required = true)
    @JsonProperty(value = "user_id")
    private int userID;

    @ApiModelProperty(name = "cart_hash", value = "cart description", required = true)
    @JsonProperty(value = "cart_hash")
    private Map<String, String> cartHash;

    @ApiModelProperty(name = "response_message", value = "response message", required = false)
    @JsonProperty(value = "response_message")
    private String message;

    public ShowCartResponse(){

    }


    public ShowCartResponse(Cart cart){
        this.message = "Cart fetched successfully";
        if (cart == null){
            this.message = "The given user does not exist";
            this.userID = -1;
            this.cartHash = null;
            return;
        }
        this.userID = cart.getUserID();
        this.cartHash = cart.getCartHash();
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Map<String, String> getCartHash() {
        return cartHash;
    }

    public void setCartHash(Map<String, String> cartHash) {
        this.cartHash = cartHash;
    }
}
