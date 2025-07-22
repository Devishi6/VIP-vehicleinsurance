package com.vehicleinsurancesystem.main;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

@Entity
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Min(value = 18, message = "Age must be 18 or above")
    @Column(nullable = false)
    private int age;

    @NotBlank(message = "Gender is required")
    @Column(nullable = false)
    private String gender;

    @NotBlank(message = "Address is required")
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "State is required")
    @Column(nullable = false)
    private String state;

    @Pattern(regexp = "\\d{6}", message = "Pincode must be 6 digits")
    @Column(nullable = false)
    private String pincode;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = ".*@.*", message = "Email must contain '@'")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Column(nullable = false)
    private String phone;

    @NotBlank(message = "Aadhar number is required")
    @Column(nullable = false)
    private String aadhar;

    @NotBlank(message = "PAN number is required")
    @Column(nullable = false)
    private String pan;

    @NotBlank(message = "Vehicle number is required")
    @Column(nullable = false)
    private String vehicleNumber;

    @NotBlank(message = "Vehicle type is required")
    @Column(nullable = false)
    private String vehicleType;

    @Min(value = 1900, message = "Enter a valid vehicle purchase year")
    @Column(nullable = false)
    private int vehiclePurchaseYear;

    @Min(value = 1, message = "Month must be between 1 and 12")
    @Max(value = 12, message = "Month must be between 1 and 12")
    @Column(nullable = false)
    private int vehiclePurchaseMonth;

    @NotNull(message = "Date of birth is required")
    @Column(nullable = false)
    private LocalDate dob;

    @NotNull(message = "Policy ID is required")
    @Column(nullable = false)
    private String policyId;

    @Column(nullable = false)
    private String status = "Pending";
    
	public Customer() {
		super();
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getVehiclePurchaseYear() {
		return vehiclePurchaseYear;
	}

	public void setVehiclePurchaseYear(int vehiclePurchaseYear) {
		this.vehiclePurchaseYear = vehiclePurchaseYear;
	}

	public int getVehiclePurchaseMonth() {
		return vehiclePurchaseMonth;
	}

	public void setVehiclePurchaseMonth(int vehiclePurchaseMonth) {
		this.vehiclePurchaseMonth = vehiclePurchaseMonth;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	 
}