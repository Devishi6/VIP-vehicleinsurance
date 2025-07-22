package com.vehicleinsurancesystem.main.policyRepolayer;



import com.vehicleinsurancesystem.main.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
