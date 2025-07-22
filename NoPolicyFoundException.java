package com.vehicleinsurancesystem.main.ExceptionHandling;

public class NoPolicyFoundException extends RuntimeException {
	private String policyId;
	
	public NoPolicyFoundException(String policyId) {
		super();
		this.policyId = policyId;
	}
	
	public NoPolicyFoundException() {
		super();
	}

	@Override
    public String getMessage() {
        return "No policy found";
    }
	
	public String getMessageWithId(String policyId) {
		return "No policy with id " + policyId + "found";
	}
}
