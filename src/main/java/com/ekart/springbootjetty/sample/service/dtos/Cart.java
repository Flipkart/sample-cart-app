package com.ekart.springbootjetty.sample.service.dtos;

import java.util.Map;

/**
 * Created by nikhil.vavs on 05/01/17.
 */
public class Cart {

    private int userID;

    private Map<String, String> cartHash;


    public Cart(int userID, Map<String, String> cartHash) {
        this.userID = userID;
        this.cartHash = cartHash;
    }

    public Cart(){

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
