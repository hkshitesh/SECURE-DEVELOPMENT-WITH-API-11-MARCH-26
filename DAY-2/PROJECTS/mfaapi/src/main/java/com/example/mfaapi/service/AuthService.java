package com.example.mfaapi.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean validateUser(String username, String password){

        if(username.equals("admin") &&
           password.equals("password")){

            return true;
        }

        return false;
    }
}
