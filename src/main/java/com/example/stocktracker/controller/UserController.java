package com.example.stocktracker.controller;

import com.example.stocktracker.model.User;
import com.example.stocktracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        boolean deletedUser = userService.deleteUser(id);
        if (deletedUser) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
