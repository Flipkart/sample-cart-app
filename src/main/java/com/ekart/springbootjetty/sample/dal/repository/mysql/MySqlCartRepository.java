package com.ekart.springbootjetty.sample.dal.repository.mysql;

import com.ekart.springbootjetty.sample.dal.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nikhil.vavs on 05/01/17.
 */
public interface MySqlCartRepository extends JpaRepository<Cart, Integer> {

    void insert(Cart cart);

}
