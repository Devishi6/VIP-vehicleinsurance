package com.vehicleinsurancesystem.main.policycontrollayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vehicleinsurancesystem.main.ClaimRequest;
import com.vehicleinsurancesystem.main.PolicyServicelayer.ClaimService;


@RestController
@RequestMapping("/claims")
public class ClaimController {

    @Autowired
    private ClaimService cs;

    // User submits claim -> Email sent with Claim ID
    @PostMapping("/submit")
    public String submitClaim(@RequestBody ClaimRequest claim) {
        cs.submitClaim(claim);
        return "Claim submitted successfully! Claim ID has been sent to your email.";
    }

    // Admin/User fetches all claims
    @GetMapping("/getclaims")
    public List<ClaimRequest> getAllClaims() {
        return cs.getAllClaims();
    }
    
    @GetMapping("/status/{claimId}")
    public ResponseEntity<ClaimRequest> getClaimStatus(@PathVariable Long claimId) {
        ClaimRequest claim = cs.getClaimStatus(claimId);
        if (claim != null) {
            return ResponseEntity.ok(claim);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    //Admin updates status and remarks -> Email sent automatically
    @PutMapping("/update/{claimId}")
    public ResponseEntity<?> updateClaim(@PathVariable Long claimId, @RequestBody ClaimRequest updatedClaim) {
        ClaimRequest claim = cs.updateClaim(claimId, updatedClaim);
        if (claim == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No claim with id " + claimId + " found");
        }
        return ResponseEntity.ok(claim);
    }

}
