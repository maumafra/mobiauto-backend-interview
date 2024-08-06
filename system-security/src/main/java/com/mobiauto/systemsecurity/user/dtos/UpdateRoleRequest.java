package com.mobiauto.systemsecurity.user.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateRoleRequest(
        @JsonProperty("userId")
        Integer userId,
        @JsonProperty("newRole")
        String newRole
) {
}
