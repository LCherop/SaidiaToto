package com.laura.saidiatoto;

public class UserHelper {
    String fname,email,number,password;

    public UserHelper() {

    }

    public UserHelper(String fname, String email, String number, String password) {
        this.fname = fname;
        this.email = email;
        this.number = number;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
