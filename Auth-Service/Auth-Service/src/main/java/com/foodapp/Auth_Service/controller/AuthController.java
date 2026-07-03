package com.foodapp.Auth_Service.controller;

import com.foodapp.Auth_Service.entity.User;
import com.foodapp.Auth_Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Inject the password encoder

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username is already taken!"));
        }

        // Encrypt the plain-text password before saving it to PostgreSQL
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Optional<User> existingUser = userRepository.findByUsername(username);

        // Use passwordEncoder.matches to compare the plain text with the hashed database string
        if (existingUser.isPresent() && passwordEncoder.matches(password, existingUser.get().getPassword())) {
            return ResponseEntity.ok(Map.of("message", "Authentication verified"));
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Incorrect username or password"));
        }
    }
}
