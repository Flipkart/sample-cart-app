package com.ekart.springbootjetty.sample.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by nikhil.vavs on 09/01/17.
 */
public class AddToCartRequest {

    private int userID;
    private int productID;
    private int reqQuantity;

    public AddToCartRequest() {

    }

    public AddToCartRequest(int userID, int productID, int reqQuantity) {
        this.userID = userID;
        this.productID = productID;
        this.reqQuantity = reqQuantity;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getReqQuantity() {
        return reqQuantity;
    }

    public void setReqQuantity(int reqQuantity) {
        this.reqQuantity = reqQuantity;
    }
}
