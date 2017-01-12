package com.ekart.springbootjetty.sample.service.dtos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

public class Product {

    private int productID;
    private String productName;
    private int productPrice;
    private int availableQuantity;

    public Product(){

    }

    public Product(String productName, int productPrice, int availableQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.availableQuantity = availableQuantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
