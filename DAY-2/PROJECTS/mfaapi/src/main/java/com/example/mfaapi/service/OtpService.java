package com.example.mfaapi.service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private ConcurrentHashMap<String, String> otpStorage =
            new ConcurrentHashMap<>();

    public String generateOtp(String username){

        Random random = new Random();

        int otp = 100000 + random.nextInt(900000);

        String otpValue = String.valueOf(otp);

        otpStorage.put(username, otpValue);

        return otpValue;
    }

    public boolean validateOtp(String username, String otp){

        String storedOtp = otpStorage.get(username);

        if(storedOtp != null && storedOtp.equals(otp)){
            otpStorage.remove(username);
            return true;
        }

        return false;
    }

}
