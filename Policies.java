package com.vehicleinsurancesystem.main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Policies {
	 @Id
	 @Column(nullable = false, unique = true, length = 50)
	 private String policyId;
	 
	 @Column(nullable=false)
	 private String policyname;
	 
	 @Column(nullable = false)
	 private String year;
	 
	 @Column(nullable=false)
	 private String month;
	 
	 @Column(nullable=false)
	 private String vehicleType;
	 
	 @Column(nullable=false)
	 private String status = "Available";
	 
	@Column(nullable=false)
	 private double premiumamount;
	
	 @Column(nullable = false)
	 private Integer tenureInMonths;
	 
	 @Column(nullable = false, length = 5000)
	 private String description; // Brief terms and conditions
	 
	 private String policyDocumentPath; // File path or URL for uploaded PDF

	 
	public Policies() {
		super();
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyname() {
		return policyname;
	}

	public void setPolicyname(String policyname) {
		this.policyname = policyname;
	}

	public double getPremiumamount() {
		return premiumamount;
	}

	public void setPremiumamount(double premiumamount) {
		this.premiumamount = premiumamount;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getTenureInMonths() {
		return tenureInMonths;
	}

	public void setTenureInMonths(Integer tenureInMonths) {
		this.tenureInMonths = tenureInMonths;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPolicyDocumentPath() {
		return policyDocumentPath;
	}

	public void setPolicyDocumentPath(String policyDocumentPath) {
		this.policyDocumentPath = policyDocumentPath;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getStatus() {
	    return status;
	}

	public void setStatus(String status) {
	    this.status = status;
	}

}
