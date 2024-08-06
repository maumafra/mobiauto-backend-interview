package com.mobiauto.systemsecurity.user.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateResaleIdRequest(
        @JsonProperty("userId")
        Integer userId,
        @JsonProperty("newResaleId")
        Integer newResaleId
) {
}
