package com.example.securityapi.controller;

import com.example.securityapi.model.User;
import com.example.securityapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secure")
public class SecureUserController {

    private final UserService service;

    public SecureUserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUser(@PathVariable Long id) {

        User user = service.find(id);

        if (user == null) {

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        user.setRole("user");
        user.setId(null);

        return ResponseEntity.ok(service.save(user));

    }


    @DeleteMapping("/admin/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long id) {

        service.delete(id);

        return "User deleted successfully";

    }
}
