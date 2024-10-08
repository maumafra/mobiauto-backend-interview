package com.mobiauto.opportunitymanagement.business.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record ClientResponse(
    @JsonProperty("clientName")
    String clientName,
    @JsonProperty("clientEmail")
    String clientEmail,
    @JsonProperty("clientPhone")
    String clientPhone
) {
}
