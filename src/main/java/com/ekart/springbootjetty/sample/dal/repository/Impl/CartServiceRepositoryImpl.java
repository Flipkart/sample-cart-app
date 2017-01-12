package com.ekart.springbootjetty.sample.dal.repository.Impl;

import com.beust.jcommander.internal.Maps;
import com.ekart.springbootjetty.sample.common.util.ResponseUtil;
import com.ekart.springbootjetty.sample.dal.dtos.CreateUserRequest;
import com.ekart.springbootjetty.sample.dal.models.Cart;
import com.ekart.springbootjetty.sample.dal.models.Product;
import com.ekart.springbootjetty.sample.dal.models.User;
import com.ekart.springbootjetty.sample.dal.repository.CartServiceRepository;
import com.ekart.springbootjetty.sample.dal.repository.mysql.MySqlCartRepository;
import com.ekart.springbootjetty.sample.dal.repository.mysql.MySqlUserRepository;
import com.ekart.springbootjetty.sample.dal.repository.mysql.MySqlProductRepository;
import com.ekart.springbootjetty.sample.service.exceptions.UserNotFoundException;

import java.util.Map;

/**
 * Created by nikhil.vavs on 05/01/17.
 */
public class CartServiceRepositoryImpl implements CartServiceRepository {

    private final MySqlUserRepository mySqlUserRepository;
    private final MySqlCartRepository mySqlCartRepository;
    private final MySqlProductRepository mySqlProductRepository;

    public CartServiceRepositoryImpl(MySqlUserRepository mySqlUserRepository,
                                     MySqlCartRepository mySqlCartRepository,
                                     MySqlProductRepository mySqlProductRepository
    ){
        this.mySqlUserRepository = mySqlUserRepository;
        this.mySqlCartRepository = mySqlCartRepository;
        this.mySqlProductRepository = mySqlProductRepository;
    }

    @Override
    public int createUser(CreateUserRequest createUserRequest) {
        User user  = new User(createUserRequest.getUserName(), createUserRequest.getContactNo());
        mySqlUserRepository.insert(user);
        return user.getId();
    }

    @Override
    public void createCart(int userID) {
        mySqlCartRepository.insert(new Cart(userID));
    }


    @Override
    public User showUserData(int userID) {
        User user = mySqlUserRepository.findOne(userID);
        if (user == null) throw new NullPointerException("user not found");
        return user;
    }

    @Override
    public Product getProduct(int productID) {
        Product product = mySqlProductRepository.findOne(productID);
        if (product == null) throw new NullPointerException("No product found");
        return product;
    }

    @Override
    public void saveNewCart(Cart newCart) {
        mySqlCartRepository.save(newCart);
    }

//    @Override
//    public String deleteFromCart(int userID, int productID) {
//        Cart currentCart = mySqlCartRepository.findOne(userID);
//        if (currentCart==null) return ResponseUtil.noUser;
//
//        // TODO change this extra copying thing
//        Map<String, String> oldCartMap = currentCart.getCartHash();
//        if (!oldCartMap.containsKey(String.valueOf(productID))) return ResponseUtil.noProductWithIDInCart;
//        Map<String, String> newCartMap = Maps.newHashMap();
//        oldCartMap.forEach(newCartMap::putIfAbsent);
//        System.out.println(newCartMap);
//        newCartMap.remove(String.valueOf(productID));
//        System.out.println(newCartMap);
//        Cart newCart = new Cart(userID, newCartMap);
//        mySqlCartRepository.delete(currentCart);
//        System.out.println(newCartMap);
//        mySqlCartRepository.save(newCart);
//
//
//        return ResponseUtil.deleteFromCartSuccessFul;
//    }

    @Override
    public Cart getCart(int userID) {
        Cart cart = mySqlCartRepository.findOne(userID);
        if (cart == null) throw new NullPointerException("No user found");
        return cart;
    }

//    @Override
//    public String showCartValue(int userID){
//        Cart cart = mySqlCartRepository.findOne(userID);
//        if (cart == null) return ResponseUtil.cartValueResponseForNonExistantCart;
//        int cartValue = 0;
//        for (Map.Entry<String, String> entry: cart.getCartHash().entrySet()){
//            int productPrice = getProductPrice(entry.getKey());
//            if(productPrice == ResponseUtil.productValueForNonExistantProduct)
//                return ResponseUtil.cartValueResponseForCartWithNonExistantProducts;
//            cartValue += productPrice * Integer.parseInt(entry.getValue());
//        }
//        return ResponseUtil.cartValueResponsePrefix + userID + ResponseUtil.cartValueResponseMiddle + cartValue;
//    }

//    private int getProductPrice(int productID){
//        Product p = mySqlProductRepository.findOne(productID);
//        if (p == null) return ResponseUtil.productValueForNonExistantProduct;
//        return p.getProductPrice();
//    }

//    private int getProductPrice(String productID){
//        return getProductPrice(Integer.parseInt(productID));
//    }


}
