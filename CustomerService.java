package com.vehicleinsurancesystem.main.PolicyServicelayer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vehicleinsurancesystem.main.Customer;
import com.vehicleinsurancesystem.main.Policies;
import com.vehicleinsurancesystem.main.User;
import com.vehicleinsurancesystem.main.ExceptionHandling.NoCustomerFoundException;
import com.vehicleinsurancesystem.main.policyRepolayer.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository cr;
    
    public String registerCustomer(Customer customer) {

//        // check if email already exists
//        boolean emailExists = cr.existsByEmail(customer.getEmail());
//
//        if (emailExists) {
//            return "This email is already used. Please try another.";
//        }

        // set status to Pending
        customer.setStatus("Pending");
        // save the customer in DB
        cr.save(customer);

        return "Customer registered successfully.";
    }
    
    public List<Customer> getAllCustomers() {
        return cr.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return cr.findById(id);
    }
    
    
    public String updateCustomer(Long id, Customer customerDetails) {
        Customer existingCustomer = cr.findById(id)
                .orElseThrow(() -> new NoCustomerFoundException(id));

        if (customerDetails.getName() != null && !customerDetails.getName().isEmpty()) {
            existingCustomer.setName(customerDetails.getName());
        }

        // For primitives like int, you can decide a value to mean "not updated".
        // For example, if 0 means no update:
        if (customerDetails.getAge() != 0) {
            existingCustomer.setAge(customerDetails.getAge());
        }

        if (customerDetails.getGender() != null && !customerDetails.getGender().isEmpty()) {
            existingCustomer.setGender(customerDetails.getGender());
        }

        if (customerDetails.getAddress() != null && !customerDetails.getAddress().isEmpty()) {
            existingCustomer.setAddress(customerDetails.getAddress());
        }

        if (customerDetails.getState() != null && !customerDetails.getState().isEmpty()) {
            existingCustomer.setState(customerDetails.getState());
        }

        if (customerDetails.getPincode() != null && !customerDetails.getPincode().isEmpty()) {
            existingCustomer.setPincode(customerDetails.getPincode());
        }

        if (customerDetails.getEmail() != null && !customerDetails.getEmail().isEmpty()) {
            existingCustomer.setEmail(customerDetails.getEmail());
        }

        if (customerDetails.getPhone() != null && !customerDetails.getPhone().isEmpty()) {
            existingCustomer.setPhone(customerDetails.getPhone());
        }

        if (customerDetails.getAadhar() != null && !customerDetails.getAadhar().isEmpty()) {
            existingCustomer.setAadhar(customerDetails.getAadhar());
        }

        if (customerDetails.getPan() != null && !customerDetails.getPan().isEmpty()) {
            existingCustomer.setPan(customerDetails.getPan());
        }

        if (customerDetails.getVehicleNumber() != null && !customerDetails.getVehicleNumber().isEmpty()) {
            existingCustomer.setVehicleNumber(customerDetails.getVehicleNumber());
        }

        if (customerDetails.getVehicleType() != null && !customerDetails.getVehicleType().isEmpty()) {
            existingCustomer.setVehicleType(customerDetails.getVehicleType());
        }

        if (customerDetails.getVehiclePurchaseYear() != 0) {
            existingCustomer.setVehiclePurchaseYear(customerDetails.getVehiclePurchaseYear());
        }

        if (customerDetails.getVehiclePurchaseMonth() != 0) {
            existingCustomer.setVehiclePurchaseMonth(customerDetails.getVehiclePurchaseMonth());
        }

        if (customerDetails.getDob() != null) {
            existingCustomer.setDob(customerDetails.getDob());
        }

        if (customerDetails.getPolicyId() != null && !customerDetails.getPolicyId().isEmpty()) {
            existingCustomer.setPolicyId(customerDetails.getPolicyId());
        }

        if (customerDetails.getStatus() != null && !customerDetails.getStatus().isEmpty()) {
            existingCustomer.setStatus(customerDetails.getStatus());
        }

        cr.save(existingCustomer);
        return "Customer updated successfully";
    }

    public String deleteCustomerById(Long id) {
        if (cr.existsById(id)) {
            cr.deleteById(id);
            return "Customer deleted successfully";
        }
        return "Customer with ID " + id + " not found.";
    }
    
    
    public List<Customer> MyPolicies(String email) {
        return cr.findByEmail(email);
    }

}
