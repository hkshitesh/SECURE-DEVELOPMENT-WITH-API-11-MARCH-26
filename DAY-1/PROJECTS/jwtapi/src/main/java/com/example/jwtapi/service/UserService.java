package com.example.jwtapi.service;

public class UserService {

    public boolean validateUser(String username, String password) {

        if(username.equals("admin") && password.equals("1234")) {
            return true;
        }

        return false;
    }
}
