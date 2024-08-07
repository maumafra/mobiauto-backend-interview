package com.mobiauto.opportunityservice.utils.exceptions;

public class OpportunityServiceNotFoundException extends RuntimeException{

    private final String message;

    public OpportunityServiceNotFoundException(Integer opportunityId) {
        this.message = "no opportunity service was found with opportunity: "+opportunityId;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
