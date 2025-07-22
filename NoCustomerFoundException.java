package com.vehicleinsurancesystem.main.ExceptionHandling;

import com.vehicleinsurancesystem.main.Customer;

public class NoCustomerFoundException extends RuntimeException {
    private Long customerId;

    public NoCustomerFoundException(Long customerId) {
        super();
        this.customerId = customerId;
    }

    @Override
    public String getMessage() {
        return "No customer found";
    }

    public String getMessageWithId(Long customerId) {
        return "No customer with id " + customerId + " found";
    }
}
