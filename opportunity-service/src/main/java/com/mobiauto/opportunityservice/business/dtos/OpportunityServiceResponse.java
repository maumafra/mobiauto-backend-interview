package com.mobiauto.opportunityservice.business.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Builder
@Getter
@JsonPropertyOrder({
        "opportunity_id", "user_in_service", "assignment_date", "conclusion_date", "reason_for_conclusion"
})
public class OpportunityServiceResponse extends RepresentationModel<OpportunityServiceResponse> {
    @JsonProperty("user_in_service")
    private String userInService;

    @JsonProperty("opportunity_id")
    private Integer opportunityId;

    @JsonProperty("assignment_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime assignmentDate;

    @JsonProperty("conclusion_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime conclusionDate;

    @JsonProperty("reason_for_conclusion")
    private String reasonForConclusion;
}
