package com.mobiauto.opportunitymanagement.utils.exceptions;

public class OpportunityNotFoundException extends RuntimeException {

    private final String message;

    public OpportunityNotFoundException(Integer resaleId) {
        this.message = "opportunity not found: "+resaleId;
    }

    public String getMessage() {
        return message;
    }

}
