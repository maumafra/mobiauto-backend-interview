package com.mobiauto.opportunitymanagement.business.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@JsonPropertyOrder({
        "id", "status", "resaleId"
})
public class OpportunityResponse extends RepresentationModel<OpportunityResponse> {
        @JsonProperty("id")
        private Integer opportunityId;
        @JsonProperty("status")
        private String status;
        @JsonUnwrapped
        private ClientResponse client;
        @JsonUnwrapped
        private VehicleResponse vehicle;
        @JsonProperty("resaleId")
        private Integer resaleId;
}
