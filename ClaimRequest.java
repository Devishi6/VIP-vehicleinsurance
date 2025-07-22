package com.vehicleinsurancesystem.main;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "claims")
public class ClaimRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claim_id;
    
    @Column(nullable=false)
    private String policyId;
    
    private Long userId;

    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false)
    private String email;
    
    @Column(nullable=false)
    private String vehicleType;
    
    @Column(nullable=false)
    private String vehicleNo;

    @Column(nullable=false)
    private String aadhar;
    
    @Column(nullable=false)
    private String pan;
    
    @Column(nullable=false)
    private String reasonToClaim;
    
    @Column(nullable=false)
    private LocalDate incidentDate;

    @Column(length = 1000, nullable=false)
    private String description;
    
    @Column(nullable=false)
    private String status = "pending"; // default when created

    @Column(length = 1000)
    private String remarks; // New field for admin remarks

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
	public Long getclaimId() {
		return claim_id;
	}

	public void setclaimId(Long claim_id) {
		this.claim_id = claim_id;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getReasonToClaim() {
		return reasonToClaim;
	}

	public void setReasonToClaim(String reasonToClaim) {
		this.reasonToClaim = reasonToClaim;
	}

	public LocalDate getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(LocalDate incidentDate) {
		this.incidentDate = incidentDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ClaimRequest() {
		super();
	}
    
    

}
