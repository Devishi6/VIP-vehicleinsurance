package com.vehicleinsurancesystem.main.policycontrollayer;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;


import com.vehicleinsurancesystem.main.Customer;
import com.vehicleinsurancesystem.main.Policies;
import com.vehicleinsurancesystem.main.User;
import com.vehicleinsurancesystem.main.PolicyServicelayer.PolicyService;
import com.vehicleinsurancesystem.main.policyRepolayer.CustomerRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/policies")
public class PolicyController {

    @Autowired
    private PolicyService ps;

    @PostMapping("/policycreation")
    public ResponseEntity<Policies> createPolicy(@RequestBody Policies policy) {
        Policies createdPolicy = ps.createPolicy(policy);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicy);
    }

    @GetMapping("/policies")
    public ResponseEntity<?> getAllPolicies(Pageable pageable) {
        boolean hasPolicies = ps.hasPolicies();
        if (!hasPolicies) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No policies found.");
        }
        Page<Policies> policies = ps.getAll(pageable);
        return ResponseEntity.ok(policies);
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<?> getPolicyById(@PathVariable String policyId) {
        Optional<Policies> policyOpt = ps.getById(policyId);
        if (policyOpt.isPresent()) {
            return ResponseEntity.ok(policyOpt.get());
        } else {
            // Clear error message with NOT FOUND status
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No policy with id " + policyId + " found");
        }
    }

    @PutMapping("/{policyId}")
    public ResponseEntity<?> updatePolicy(@PathVariable String policyId, @RequestBody Policies updatedPolicy) {
        Policies policy = ps.updatePolicy(policyId, updatedPolicy);
        if (policy == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No policy with id " + policyId + " found");
        }
        return ResponseEntity.ok(policy);
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<?> deletePolicyById(@PathVariable String policyId) {
        boolean deleted = ps.deletePolicy(policyId);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No policy with id " + policyId + " found");
        }
        return ResponseEntity.ok("Policy with ID " + policyId + " deleted successfully.");
    }
    
}
