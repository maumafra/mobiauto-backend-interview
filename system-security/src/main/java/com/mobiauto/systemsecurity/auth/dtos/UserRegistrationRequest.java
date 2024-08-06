package com.mobiauto.systemsecurity.auth.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mobiauto.systemsecurity.utils.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRegistrationRequest(

        @JsonProperty("username")
        @NotBlank(message = "username"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        String username,

        @JsonProperty("fullName")
        @NotBlank(message = "fullName"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        String fullName,

        @JsonProperty("email")
        @NotBlank(message = "email"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        @Email
        String email,

        @JsonProperty("password")
        @NotBlank(message = "password"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
                message = "invalid password: minimum eight characters, at least one letter, one number and one special character."
        )
        String password,

        @JsonProperty("resaleId")
        Integer resaleId
) {
}
