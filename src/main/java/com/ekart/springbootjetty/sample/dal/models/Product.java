package com.ekart.springbootjetty.sample.dal.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@Entity
@Table(name = "product_catalog_table")
public class Product {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "product_id")
    private int productID;

    @NotNull
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @Column(name = "product_price")
    private int productPrice;

    @NotNull
    @Column(name = "available_quantity")
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
