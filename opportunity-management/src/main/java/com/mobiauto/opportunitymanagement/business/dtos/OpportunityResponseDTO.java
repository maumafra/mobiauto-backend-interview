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
        "id", "status", "reasonForConclusion"
})
public class OpportunityResponseDTO extends RepresentationModel<OpportunityResponseDTO> {
        @JsonProperty("id")
        private Integer opportunityId;
        @JsonProperty("status")
        private String status;
        @JsonUnwrapped
        private ClientResponseDTO client;
        @JsonUnwrapped
        private VehicleResponseDTO vehicle;
        @JsonProperty("reasonForConclusion")
        private String reasonForConclusion;
}
