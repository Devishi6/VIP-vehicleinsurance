package com.vehicleinsurancesystem.main.policycontrollayer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vehicleinsurancesystem.main.User;
import com.vehicleinsurancesystem.main.PolicyServicelayer.UserService;
import com.vehicleinsurancesystem.main.policyRepolayer.UserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");
        return userService.login(email, password);
    }

    @PutMapping("/reset-password-simple")
    public ResponseEntity<String> resetPasswordSimple(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String newPassword = payload.get("newPassword");
        return userService.resetPasswordSimple(email, newPassword);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            return ResponseEntity.ok("User registered successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed. Email already exists.");
        }
    }
    
}
