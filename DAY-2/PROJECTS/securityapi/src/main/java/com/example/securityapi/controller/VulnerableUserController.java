package com.example.securityapi.controller;

import com.example.securityapi.model.User;
import com.example.securityapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1")
public class VulnerableUserController {

    private final UserService service;

    public VulnerableUserController(UserService service) {
        this.service = service;
    }

    // API1 – Broken Object Level Authorization (BOLA)
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {

        return service.find(id);

    }

    // API3 – Mass Assignment
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        return service.save(user);

    }

    // API2 – Broken Authentication
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User storedUser = service.find(user.getId());

        if (storedUser != null &&
                storedUser.getPassword().equals(user.getPassword())) {

            return "Login Successful!";
        }

        return "Invalid Login!";
    }

    // API5 – Broken Function Level Authorization
    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        service.delete(id);

        return "User deleted!";

    }

    // API8 – Excessive Data Exposure
    @GetMapping("/debug/info")
    public Collection<User> debug() {

        return service.findAll();

    }
}
