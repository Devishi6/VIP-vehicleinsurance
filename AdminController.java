package com.vehicleinsurancesystem.main.policycontrollayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vehicleinsurancesystem.main.Admin;
import com.vehicleinsurancesystem.main.Policies;
import com.vehicleinsurancesystem.main.User;
import com.vehicleinsurancesystem.main.PolicyServicelayer.AdminService;
import com.vehicleinsurancesystem.main.policyRepolayer.PolicyRepository;
import com.vehicleinsurancesystem.main.policyRepolayer.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PolicyRepository policyRepository;


    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {
        return adminService.validateLogin(admin.getEmail(), admin.getPassword())
            ? "Login successful" : "Invalid email or password";
    }



//    
}
