package com.mobiauto.opportunityservice.business.dtos.oppportunity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleResponse(
        @JsonProperty("vehicleCompany")
        String vehicleCompany,
        @JsonProperty("vehicleModel")
        String vehicleModel,
        @JsonProperty("vehicleYear")
        String vehicleYear,
        @JsonProperty("vehicleVersion")
        String vehicleVersion
) {
}
