package com.ekart.springbootjetty.sample.dal.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by nikhil.vavs on 04/01/17.
 */

@Entity
@Table(name = "user_info_table")
public class User {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @Column(name = "contact_no")
    private String contactNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }


    public User() {

    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, String contactNo) {
        this.userName = userName;
        this.contactNo = contactNo;
    }

}
