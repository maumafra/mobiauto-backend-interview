package com.mobiauto.opportunitymanagement.business.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleResponseDTO(
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
