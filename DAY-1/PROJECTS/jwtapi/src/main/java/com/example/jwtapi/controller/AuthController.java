package com.example.jwtapi.controller;

import com.example.jwtapi.model.User;
import com.example.jwtapi.service.UserService;
import com.example.jwtapi.util.JwtUtil;

import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    UserService service = new UserService();

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        if(service.validateUser(user.getUsername(), user.getPassword())) {

            return JwtUtil.generateToken(user.getUsername());
        }

        return "Invalid credentials";
    }
}
