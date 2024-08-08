package com.mobiauto.systemsecurity.auth.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDetailsResponse(
        Integer id,
        String username,
        String fullname,
        String email,
        String password,
        String role,
        LocalDateTime lastOpportunityReceived,
        Integer qttOpportunitiesAttended,
        Integer resaleId
) {
}
