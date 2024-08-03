package com.mobiauto.resalemanagement.utils.exceptions;

public class ResaleNotFoundException extends RuntimeException {

    private final String message;

    public ResaleNotFoundException(Integer resaleId) {
        this.message = "resale not found: "+resaleId;
    }

    public String getMessage() {
        return message;
    }
}
