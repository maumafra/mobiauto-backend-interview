package com.mobiauto.opportunityservice.business.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mobiauto.opportunityservice.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpportunityServiceRequest(
        @JsonProperty("userInService")
        @NotBlank(message = "userInService"+ Constants.BLANK_FIELD_ERROR_MESSAGE)
        String userInService,

        @JsonProperty("opportunityId")
        @NotBlank(message = "opportunityId"+ Constants.BLANK_FIELD_ERROR_MESSAGE)
        Integer opportunityId,

        @JsonProperty("assignmentDate")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDateTime assignmentDate,

        @JsonProperty("conclusionDate")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDateTime conclusionDate,

        @JsonProperty("reasonForConclusion")
        String reasonForConclusion
) {
}
