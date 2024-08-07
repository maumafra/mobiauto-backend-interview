package com.mobiauto.opportunityservice.business.dtos.oppportunity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleResponse(
        String vehicleCompany,
        String vehicleModel,
        String vehicleYear,
        String vehicleVersion
) {
}
