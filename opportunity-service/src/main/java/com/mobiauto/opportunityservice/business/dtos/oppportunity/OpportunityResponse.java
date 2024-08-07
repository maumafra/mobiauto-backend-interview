package com.mobiauto.opportunityservice.business.dtos.oppportunity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpportunityResponse(
        Integer id,
        String status,
        @JsonUnwrapped
        ClientResponse client,
        @JsonUnwrapped
        VehicleResponse vehicle,
        Integer resaleId
) {
}
