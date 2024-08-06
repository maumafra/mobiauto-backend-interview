package com.mobiauto.systemsecurity.auth.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mobiauto.systemsecurity.utils.Constants;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserAuthenticationRequest(
        @JsonProperty("username")
        @NotBlank(message = "username"+ Constants.BLANK_FIELD_ERROR_MESSAGE)
        String username,

        @JsonProperty("password")
        @NotBlank(message = "password"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        String password
) {
}
