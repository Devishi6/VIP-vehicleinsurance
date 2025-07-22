package com.vehicleinsurancesystem.main.policyRepolayer;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicleinsurancesystem.main.ClaimRequest;

public interface ClaimRepository extends JpaRepository<ClaimRequest, Long> {
    // You can add custom queries if needed
}

