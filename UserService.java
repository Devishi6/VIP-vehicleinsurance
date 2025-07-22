package com.vehicleinsurancesystem.main.PolicyServicelayer;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vehicleinsurancesystem.main.Customer;
import com.vehicleinsurancesystem.main.User;
import com.vehicleinsurancesystem.main.policyRepolayer.CustomerRepository;
import com.vehicleinsurancesystem.main.policyRepolayer.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository ur;

    public String login(String email, String password) {
        try {
            Optional<User> optionalUser = ur.findByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (user.getPassword().equals(password)) {
                    return "Login successful";
                } else {
                    return "Invalid credentials";
                }
            } else {
                return "User not found. Please register first.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred during login. Please try again.";
        }
    }
    
    public ResponseEntity<String> resetPasswordSimple(String email, String newPassword) {
        try {
            Optional<User> optionalUser = ur.findByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPassword(newPassword);
                ur.save(user);
                return ResponseEntity.ok("Password updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the password.");
        }
    }

    public boolean registerUser(User user) {
        try {
            if (ur.existsByEmail(user.getEmail())) {
                return false;
            }
            // TODO: Hash password before saving for security
            ur.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
}
