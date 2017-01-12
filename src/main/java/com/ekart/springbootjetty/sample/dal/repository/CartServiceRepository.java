package com.ekart.springbootjetty.sample.dal.repository;

import com.ekart.springbootjetty.sample.dal.dtos.CreateUserRequest;
import com.ekart.springbootjetty.sample.dal.models.Cart;
import com.ekart.springbootjetty.sample.dal.models.User;
import com.ekart.springbootjetty.sample.dal.models.Product;

/**
 * Created by nikhil.vavs on 04/01/17.
 */
public interface CartServiceRepository {

    int createUser(CreateUserRequest createUserRequest);
    void createCart(int userID);
    User showUserData(int userID);
    void saveNewCart(Cart cart);
//    String deleteFromCart(int userID, int productID);
    Cart getCart(int userID);
//    String showCartValue(int userID);
    Product getProduct(int productID);
}
