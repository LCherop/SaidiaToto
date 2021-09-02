package com.laura.saidiatoto;

public class UserHelper {
    private String fname,email,number,username,key;

    public UserHelper() {

    }

    public UserHelper(String fname, String username, String email, String number) {
        this.fname = fname;
        this.username = username;
        this.email = email;
        this.number = number;

        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
