package com.vehicleinsurancesystem.main.PolicyServicelayer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.vehicleinsurancesystem.main.ClaimRequest;
import com.vehicleinsurancesystem.main.policyRepolayer.ClaimRepository;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository cr;

    @Autowired
    private JavaMailSender mailSender;

    // User submits claim -> Save and Send Email with Claim ID
    public ClaimRequest submitClaim(ClaimRequest claim) {
        ClaimRequest savedClaim = cr.save(claim);
        sendClaimSubmissionEmail(savedClaim);
        return savedClaim;
    }

    public List<ClaimRequest> getAllClaims() {
        return cr.findAll();
    }

    // Admin updates status or remarks -> Save and Send Email update
    public ClaimRequest updateClaim(Long claimId, ClaimRequest updatedClaim) {
        Optional<ClaimRequest> existingClaimOpt = cr.findById(claimId);

        if (existingClaimOpt.isPresent()) {
            ClaimRequest existingClaim = existingClaimOpt.get();

            if (updatedClaim.getPolicyId() != null) {
                existingClaim.setPolicyId(updatedClaim.getPolicyId());
            }
            if (updatedClaim.getUserId() != null) {
                existingClaim.setUserId(updatedClaim.getUserId());
            }
            if (updatedClaim.getName() != null) {
                existingClaim.setName(updatedClaim.getName());
            }
            if (updatedClaim.getEmail() != null) {
                existingClaim.setEmail(updatedClaim.getEmail());
            }
            if (updatedClaim.getVehicleType() != null) {
                existingClaim.setVehicleType(updatedClaim.getVehicleType());
            }
            if (updatedClaim.getVehicleNo() != null) {
                existingClaim.setVehicleNo(updatedClaim.getVehicleNo());
            }
            if (updatedClaim.getAadhar() != null) {
                existingClaim.setAadhar(updatedClaim.getAadhar());
            }
            if (updatedClaim.getPan() != null) {
                existingClaim.setPan(updatedClaim.getPan());
            }
            if (updatedClaim.getReasonToClaim() != null) {
                existingClaim.setReasonToClaim(updatedClaim.getReasonToClaim());
            }
            if (updatedClaim.getIncidentDate() != null) {
                existingClaim.setIncidentDate(updatedClaim.getIncidentDate());
            }
            if (updatedClaim.getDescription() != null) {
                existingClaim.setDescription(updatedClaim.getDescription());
            }
            if (updatedClaim.getStatus() != null) {
                existingClaim.setStatus(updatedClaim.getStatus());
            }
            if (updatedClaim.getRemarks() != null) {
                existingClaim.setRemarks(updatedClaim.getRemarks());
            }

            ClaimRequest savedClaim = cr.save(existingClaim);

            // Call your email method here
            sendStatusUpdateEmail(savedClaim);

            return savedClaim;
        } else {
            return null;
        }
    }
    
    public ClaimRequest getClaimStatus(Long claimId) {
        Optional<ClaimRequest> claimOpt = cr.findById(claimId);
        return claimOpt.orElse(null);
    }


    // Email for Claim Submission
    private void sendClaimSubmissionEmail(ClaimRequest claimRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(claimRequest.getEmail());
        message.setSubject("Your Claim Request Submitted Successfully - Claim ID: " + claimRequest.getclaimId());
        message.setText("Dear " + claimRequest.getName() + ",\n\n"
                + "Your vehicle insurance claim request has been submitted successfully.\n"
                + "Your Claim ID is: " + claimRequest.getclaimId() + "\n"
                + "You can use this Claim ID to check your claim status.\n\n"
                + "Thank you.");
        mailSender.send(message);
    }

    //Email for Admin Update (status / remarks)
    private void sendStatusUpdateEmail(ClaimRequest claimRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(claimRequest.getEmail());
        message.setSubject("Update on Your Claim ID: " + claimRequest.getclaimId());
        message.setText("Dear " + claimRequest.getName() + ",\n\n"
                + "Your claim status has been updated.\n\n"
                + "Claim ID: " + claimRequest.getclaimId() + "\n"
                + "New Status: " + claimRequest.getStatus() + "\n"
                + "Remarks from Admin: " + (claimRequest.getRemarks() != null ? claimRequest.getRemarks() : "None") + "\n\n"
                + "Thank you.");
        mailSender.send(message);
    }
}
