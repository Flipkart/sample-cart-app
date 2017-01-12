package com.ekart.springbootjetty.sample.dal.repository.mysql;

import com.ekart.springbootjetty.sample.dal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Iterator;

/**
 * Created by nikhil.vavs on 05/01/17.
 */
public interface MySqlUserRepository extends JpaRepository<User, Integer>{

    User insert(User user);

}
