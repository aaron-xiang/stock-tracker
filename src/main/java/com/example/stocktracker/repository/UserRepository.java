package com.example.stocktracker.repository;

import com.example.stocktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    User findByUsername(String username);

    List<User> findByEnabled(boolean enabled);
}
