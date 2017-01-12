package com.ekart.springbootjetty.sample.common.util;

/**
 * Created by nikhil.vavs on 05/01/17.
 */
public class ResponseUtil {
    public static final String noUser = "No user exists with the given id";
    public static final String addToCartSuccessFul = "Product added to cart successfully";
    public static final String deleteFromCartSuccessFul = "Product deleted from cart successfully";
    public static final String noProductWithIDInCart = "The product with the given id is not present in the cart";

    public static final String cartValueResponseForNonExistantCart = "No user exists with the given id";
    public static final String cartValueResponseForCartWithNonExistantProducts= "Unable to calculate the value " +
            "of cart. Missing Product Data";
    public static final String cartValueResponsePrefix = "The value of the cart for the user with userID: ";
    public static final String cartValueResponseMiddle = " is ";

    public static final int productValueForNonExistantProduct = -1;

    public static final String productNotAvailableWithGivenID = "No product exists in the catalog with the given id";
    public static final String insufficientQuantity = "Not enough quantity is present. Please choose lesser quantity";

}
