package com.mobiauto.opportunityservice.business.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mobiauto.opportunityservice.utils.Constants;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ConcludeOpporunityRequest(
        @JsonProperty("reasonForConclusion")
        @NotBlank(message = "reasonForConclusion"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        String reasonForConclusion
) {
}
