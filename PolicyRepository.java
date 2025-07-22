package com.vehicleinsurancesystem.main.policyRepolayer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicleinsurancesystem.main.Policies;

public interface PolicyRepository extends JpaRepository<Policies, String>{
	
	// to find the policies based on vehicletype year and month
	List<Policies> findByVehicleTypeAndYearAndMonth(String vehicleType, String year, String month);
	List<Policies> findByStatus(String status);


}
