package com.example.mfaapi.controller;

import org.springframework.web.bind.annotation.*;

import com.example.mfaapi.model.User;
import com.example.mfaapi.service.AuthService;
import com.example.mfaapi.service.OtpService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final OtpService otpService;

    public AuthController(AuthService authService,
                          OtpService otpService){

        this.authService = authService;
        this.otpService = otpService;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){

        boolean validUser =
                authService.validateUser(
                        user.getUsername(),
                        user.getPassword());

        if(!validUser){
            return "Invalid credentials";
        }

        String otp =
                otpService.generateOtp(user.getUsername());

        return "OTP Generated: " + otp;
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody User user){

        boolean validOtp =
                otpService.validateOtp(
                        user.getUsername(),
                        user.getOtp());

        if(validOtp){
            return "MFA Authentication Successful";
        }

        return "Invalid OTP";
    }
}
