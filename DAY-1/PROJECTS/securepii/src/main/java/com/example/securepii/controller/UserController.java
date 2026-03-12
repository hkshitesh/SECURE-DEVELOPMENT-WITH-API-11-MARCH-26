package com.example.securepii.controller;

import com.example.securepii.model.User;
import com.example.securepii.util.EncryptionUtil;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) throws Exception {

        user.setEmail(EncryptionUtil.encrypt(user.getEmail()));
        user.setPhone(EncryptionUtil.encrypt(user.getPhone()));

        return user;
    }

    @PostMapping("/getUser")
    public User getUser(@RequestBody User user) throws Exception {

        user.setEmail(EncryptionUtil.decrypt(user.getEmail()));
        user.setPhone(EncryptionUtil.decrypt(user.getPhone()));

        return user;
    }
}
