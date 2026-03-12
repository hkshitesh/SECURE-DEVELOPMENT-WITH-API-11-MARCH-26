package com.example.securityapi.service;

import com.example.securityapi.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final Map<Long, User> store = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(3);

    public UserService() {

        store.put(1L,
                new User(
                        1L,
                        "Admin",
                        "admin@test.com",
                        "admin",
                        "admin123"
                ));

        store.put(2L,
                new User(
                        2L,
                        "User",
                        "user@test.com",
                        "user",
                        "user123"
                ));
    }

    public User find(Long id) {
        return store.get(id);
    }

    public Collection<User> findAll() {
        return store.values();
    }

    public User save(User user) {

        if (user.getId() == null) {
            user.setId(counter.getAndIncrement());
        }

        store.put(user.getId(), user);

        return user;
    }

    public  boolean delete(Long id) {

        return store.remove(id) != null;

    }
}
