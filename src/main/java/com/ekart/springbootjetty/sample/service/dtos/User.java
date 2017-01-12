package com.ekart.springbootjetty.sample.service.dtos;

/**
 * Created by nikhil.vavs on 05/01/17.
 */
public class User {

    private int id;
    private String userName;
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

    public User(){}

    public User(int id, String userName, String contactNo) {
        this.id = id;
        this.userName = userName;
        this.contactNo = contactNo;
    }
}
