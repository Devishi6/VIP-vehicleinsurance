package com.vehicleinsurancesystem.main.policyRepolayer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicleinsurancesystem.main.Customer;
import com.vehicleinsurancesystem.main.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
//	boolean existsByEmail(String email);
	List<Customer> findByEmail(String email);
}

