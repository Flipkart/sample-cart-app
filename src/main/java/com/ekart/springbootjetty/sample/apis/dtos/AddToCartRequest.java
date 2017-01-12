package com.ekart.springbootjetty.sample.apis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by nikhil.vavs on 09/01/17.
 */
@ApiModel(description = "Add a product to cart request DTO")
public class AddToCartRequest {

    @NotNull(message = "{addToCartRequest.userID.notnull}")
    @ApiModelProperty(name = "user_id", value = "user id")
    @JsonProperty(value = "user_id", required = true)
    private int userID;

    @ApiModelProperty(name = "product_id", value = "product id")
    @JsonProperty(value = "product_id", required = true)
    private int productID;

    @ApiModelProperty(name = "req_quantity", value = "req quantity")
    @JsonProperty(value = "req_quantity", required = true)
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
