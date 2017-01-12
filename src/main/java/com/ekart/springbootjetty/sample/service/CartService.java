package com.ekart.springbootjetty.sample.service;

import com.beust.jcommander.internal.Maps;
import com.ekart.springbootjetty.sample.service.dtos.*;
import com.ekart.springbootjetty.sample.dal.repository.CartServiceRepository;
import com.ekart.springbootjetty.sample.service.exceptions.InsufficientQuantityException;
import com.ekart.springbootjetty.sample.service.exceptions.ProductNotFoundException;
import com.ekart.springbootjetty.sample.service.exceptions.ProductNotInCartException;
import com.ekart.springbootjetty.sample.service.exceptions.UserNotFoundException;
import com.ekart.springbootjetty.sample.service.mappers.ServiceDalDtoMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by nikhil.vavs on 05/01/17.
 */

@Service
public class CartService {

    private final CartServiceRepository cartServiceRepository;
    private final ServiceDalDtoMapper serviceDalDtoMapper;

    public CartService(CartServiceRepository cartServiceRepository, ServiceDalDtoMapper serviceDalDtoMapper){
        this.cartServiceRepository = checkNotNull(cartServiceRepository);
        this.serviceDalDtoMapper = checkNotNull(serviceDalDtoMapper);
    }

    public CreateRequestResponse createUser(CreateUserRequest createUserRequest){
        int newUserID = cartServiceRepository.createUser(
                serviceDalDtoMapper.serviceToDalCreateUserRequest(createUserRequest));
        cartServiceRepository.createCart(newUserID);
        return new CreateRequestResponse(String.valueOf(newUserID));
    }


    public User getUserData(int userID){
        try{
            return serviceDalDtoMapper.dalToServiceUser(cartServiceRepository.showUserData(userID));
        } catch (NullPointerException e){
            throw new UserNotFoundException("No user for the given id was found", e);
        }

    }

    public Cart showCart(int userID){
        try{
            return serviceDalDtoMapper.dalToServiceCart(cartServiceRepository.getCart(userID));
        } catch (NullPointerException e){
            throw new UserNotFoundException("No user for the given id was found", e);
        }
    }

    public void addToCart(AddToCartRequest addToCartRequest){
        Cart oldCart;
        Product product;
        try{
            oldCart = serviceDalDtoMapper.dalToServiceCart(
                    cartServiceRepository.getCart(addToCartRequest.getUserID()));
        } catch (NullPointerException e){
            throw new UserNotFoundException("No user found. Cannot add to cart", e);
        }
        try{
            product = serviceDalDtoMapper.dalToServiceProduct(
                    cartServiceRepository.getProduct(addToCartRequest.getProductID()));
        } catch (NullPointerException e){
            throw new ProductNotFoundException("No product found. Cannot add to cart", e);
        }
        if (product.getAvailableQuantity() < addToCartRequest.getReqQuantity())
            throw new InsufficientQuantityException("Chosen quantity is more than the available quantity");

        // TODO add check for negative number

        Map<String, String> oldCartMap = oldCart.getCartHash();
        Map<String, String> newCartMap = Maps.newHashMap();
        oldCartMap.forEach(newCartMap::putIfAbsent);
        newCartMap.put(
                String.valueOf(addToCartRequest.getProductID()), String.valueOf(addToCartRequest.getReqQuantity()));
        Cart newCart = new Cart(addToCartRequest.getUserID(), newCartMap);
        cartServiceRepository.saveNewCart(serviceDalDtoMapper.serviceToDalCart(newCart));
    }

    public void deleteFromCart(DeleteFromCartRequest deleteFromCartRequest){
        Cart oldCart;
        try{
            oldCart = serviceDalDtoMapper.dalToServiceCart(
                    cartServiceRepository.getCart(deleteFromCartRequest.getUserID()));
        } catch (NullPointerException e){
            throw new UserNotFoundException("No user found. Cannot delete from cart", e);
        }

        Map<String, String> oldCartMap = oldCart.getCartHash();
        if (!oldCartMap.containsKey(String.valueOf(deleteFromCartRequest.getProductID())))
            throw new ProductNotInCartException("Product not in cart. Cannot delete from cart ");
        Map<String, String> newCartMap = Maps.newHashMap();
        oldCartMap.forEach(newCartMap::putIfAbsent);
        newCartMap.remove(String.valueOf(deleteFromCartRequest.getProductID()));
        Cart newCart = new Cart(deleteFromCartRequest.getUserID(), newCartMap);
        cartServiceRepository.saveNewCart(serviceDalDtoMapper.serviceToDalCart(newCart));
    }

    public int showCartValue(int userID){
        Cart cart;
        try{
          cart = serviceDalDtoMapper.dalToServiceCart(cartServiceRepository.getCart(userID));
        } catch (NullPointerException e){
            throw new UserNotFoundException("User not found. Cannot show cart value", e);
        }
        Map<String, String> cartHash = cart.getCartHash();
        int cartValue = 0;
        try{
            for (Map.Entry<String, String> entry: cart.getCartHash().entrySet()){
                int productPrice = cartServiceRepository.getProduct(Integer.parseInt(entry.getKey())).getProductPrice();
                cartValue += productPrice * Integer.parseInt(entry.getValue());
            }
        } catch (NullPointerException e){
            throw new ProductNotFoundException("no mapping for product in cart. Cannot calculate value");
        }
        return cartValue;

    }

}
