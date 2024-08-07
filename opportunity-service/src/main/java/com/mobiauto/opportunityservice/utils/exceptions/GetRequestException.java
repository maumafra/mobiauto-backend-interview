package com.mobiauto.opportunityservice.utils.exceptions;

import org.springframework.http.HttpStatusCode;

public class GetRequestException extends RuntimeException {
    private final String message;
    private final HttpStatusCode statusCode;

    public GetRequestException(String message, HttpStatusCode statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }
}
