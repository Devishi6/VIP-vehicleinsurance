package com.vehicleinsurancesystem.main.PolicyServicelayer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vehicleinsurancesystem.main.Policies;
import com.vehicleinsurancesystem.main.policyRepolayer.PolicyRepository;

@Service
public class PolicyService {
	@Autowired
	private PolicyRepository pr;
	
	public boolean hasPolicies() {
        return pr.count() > 0; // Returns true if the total count is greater than 0
    }

    // Fetch paginated policies
    public Page<Policies> getAll(Pageable pageable) {
        return pr.findAll(pageable);
    }
    
    public Policies createPolicy(Policies policy) {
    	String type = policy.getVehicleType();
    	String year = policy.getYear();
    	String month = policy.getMonth();
    	String prefix = type + " - " + year + " - " + month + " - ";
    	List<Policies> existingPolicies = pr.findByVehicleTypeAndYearAndMonth(type, year, month);
    	int newnum = existingPolicies.size()+1;
    	String numberformatted = String.format("%04d",newnum);
    	String generatedPolicyid = prefix + numberformatted;
    	policy.setPolicyId(generatedPolicyid);
    	return pr.save(policy);
    }
    
    public Optional<Policies> getById(String policyId){
    	return pr.findById(policyId);
    }
    
    public boolean deletePolicy(String policyId) {
        Optional<Policies> policyOpt = pr.findById(policyId);

        if (policyOpt.isPresent()) {
            pr.deleteById(policyId);
            return true;
        }

        return false; // Let controller throw the exception
    }
    
    public Policies updatePolicy(String policyId, Policies updatedPolicy) {
        Optional<Policies> existingPolicyOpt = pr.findById(policyId);

        if (existingPolicyOpt.isPresent()) {
            Policies existingPolicy = existingPolicyOpt.get();

            if (updatedPolicy.getPolicyname() != null) {
                existingPolicy.setPolicyname(updatedPolicy.getPolicyname());
            }
            if (updatedPolicy.getVehicleType() != null) {
                existingPolicy.setVehicleType(updatedPolicy.getVehicleType());
            }
            if (updatedPolicy.getYear() != null) {
                existingPolicy.setYear(updatedPolicy.getYear());
            }
            if (updatedPolicy.getMonth() != null) {
                existingPolicy.setMonth(updatedPolicy.getMonth());
            }
            if (updatedPolicy.getTenureInMonths() != null) {
                existingPolicy.setTenureInMonths(updatedPolicy.getTenureInMonths());
            }
            if (updatedPolicy.getDescription() != null) {
                existingPolicy.setDescription(updatedPolicy.getDescription());
            }
            if (updatedPolicy.getPolicyDocumentPath() != null) {
                existingPolicy.setPolicyDocumentPath(updatedPolicy.getPolicyDocumentPath());
            }
            if (updatedPolicy.getStatus() != null) {
                existingPolicy.setStatus(updatedPolicy.getStatus());
            }
            if (updatedPolicy.getPremiumamount() != 0.0) {
                existingPolicy.setPremiumamount(updatedPolicy.getPremiumamount());
            }

            return pr.save(existingPolicy); // Save the updated entity to DB
        } else {
            return null; // Controller layer will throw exception
        }
    }

}
