package com.example.stocktracker.service;

import com.example.stocktracker.model.User;
import com.example.stocktracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUsersByEnabled(boolean enabled) {
        return userRepository.findByEnabled(enabled);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User replacementUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(replacementUser.getUsername());
            user.setPassword(replacementUser.getPassword());
            user.setEnabled(replacementUser.isEnabled());
            user.setRole(replacementUser.getRole());
            user.setWatchlist(replacementUser.getWatchlist());
            return userRepository.save(user);
        }
        else return null;
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        else return false;
    }
}
