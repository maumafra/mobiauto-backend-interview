package com.mobiauto.opportunityservice.utils.exceptions;

public class OpportunityAlreadyAssignedException extends RuntimeException{

    private final String message;

    public OpportunityAlreadyAssignedException(final Integer opportunityId) {
        this.message = "opportunity is already assigned: "+opportunityId;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
