package com.mobiauto.opportunityservice.business.dtos.oppportunity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpportunityResponse {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("status")
        private String status;
        @JsonUnwrapped
        private ClientResponse client;
        @JsonUnwrapped
        private VehicleResponse vehicle;
        @JsonProperty("resaleId")
        private Integer resaleId;
}
