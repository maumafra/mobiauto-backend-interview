package com.mobiauto.opportunityservice.business.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        @JsonProperty("id")
        Integer id,
        @JsonProperty("username")
        String username,
        @JsonProperty("fullName")
        String fullName,
        @JsonProperty("email")
        String email,
        @JsonProperty("role")
        String role,
        @JsonProperty("resaleId")
        Integer resaleId
) {
}
