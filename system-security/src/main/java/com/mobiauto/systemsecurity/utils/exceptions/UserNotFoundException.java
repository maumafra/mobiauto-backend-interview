package com.mobiauto.systemsecurity.utils.exceptions;

public class UserNotFoundException extends RuntimeException {

    private final String message;

    public UserNotFoundException(Integer userId) {
        this.message = "user not found: "+userId;
    }

    public UserNotFoundException(String username) {
        this.message = "user not found: "+username;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
