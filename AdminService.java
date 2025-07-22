package com.vehicleinsurancesystem.main.PolicyServicelayer;


import com.vehicleinsurancesystem.main.Admin;
import com.vehicleinsurancesystem.main.policyRepolayer.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean validateLogin(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        return admin != null && admin.getPassword().equals(password);
    }
}
