package com.vehicleinsurancesystem.main.policycontrollayer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vehicleinsurancesystem.main.Customer;
import com.vehicleinsurancesystem.main.Policies;
import com.vehicleinsurancesystem.main.ExceptionHandling.NoCustomerFoundException;
import com.vehicleinsurancesystem.main.PolicyServicelayer.CustomerService;
import com.vehicleinsurancesystem.main.PolicyServicelayer.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService cs;
	
	@PostMapping("/register")
	public String registerCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
	    if (result.hasErrors()) {
	        return result.getFieldError().getDefaultMessage();
	    }
	    return cs.registerCustomer(customer);
	}
	
	@GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return cs.getAllCustomers();
    }
	
	 //Get customer by ID (for update form)
	@GetMapping("update/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
	    Optional<Customer> customerOpt = cs.getCustomerById(id);
	    if (customerOpt.isPresent()) {
	        return customerOpt.get();
	    } else {
	        throw new NoCustomerFoundException(id);
	    }
	}
	
	
	@PutMapping("/update/{id}")
	public String updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
	    try {
	        return cs.updateCustomer(id, customer);
	    } catch (NoCustomerFoundException ex) {
	        return ex.getMessageWithId(id);
	    }
	}

	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        String result = cs.deleteCustomerById(id);
        if (result.contains("not found")) {
            return ResponseEntity.status(404).body(result);
        }
        return ResponseEntity.ok(result);
    }
	
	@GetMapping("getbyemail/{email}")
	public ResponseEntity<List<Customer>> getCustomerByEmail(@PathVariable String email) {
	    List<Customer> customers = cs.MyPolicies(email);
	    if (customers == null || customers.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(customers);
	}


}
