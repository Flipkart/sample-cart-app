package com.ekart.springbootjetty.sample.dal.models;

import com.beust.jcommander.internal.Maps;
import com.ekart.springbootjetty.sample.service.util.AttributeMapConvertor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@Entity
@Table(name = "cart_table")
public class Cart {

    @Id
    @NotNull
    @Column(name = "user_id")
    private int userID;

    @NotNull
    @Column(name = "cart_hash")
    @Convert(converter = AttributeMapConvertor.class)
    private Map<String, String> cartHash;

    public Cart(){

    }

    public Cart(int userID) {
        this.userID = userID;
        this.cartHash = Maps.newHashMap();
    }

    public Cart(int userID, Map<String, String> cartHash) {
        this.userID = userID;
        this.cartHash = cartHash;
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

    @Override
    public String toString(){
        return "CartItem { userID: " + userID + " cartHash: " + cartHash + "}";
    }

}
