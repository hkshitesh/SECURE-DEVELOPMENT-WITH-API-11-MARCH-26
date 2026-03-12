package com.example.jwtapi.controller;

import com.example.jwtapi.util.JwtUtil;

import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer ")) {
            return "Token missing";
        }

        String token = header.substring(7);

        if(!JwtUtil.validateToken(token)) {
            return "Invalid token";
        }

        return "Welcome to the protected profile API";
    }
}
