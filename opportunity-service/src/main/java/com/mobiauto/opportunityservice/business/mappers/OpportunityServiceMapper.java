package com.mobiauto.opportunityservice.business.mappers;

import com.mobiauto.opportunityservice.business.dtos.OpportunityServiceRequest;
import com.mobiauto.opportunityservice.business.dtos.OpportunityServiceResponse;
import com.mobiauto.opportunityservice.entities.OpportunityServiceEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OpportunityServiceMapper {

    public OpportunityServiceEntity map(Integer opportunityId, String username) {
        return OpportunityServiceEntity.builder()
                .opportunityId(opportunityId)
                .userInService(username)
                .assignmentDate(LocalDateTime.now())
                .build();
    }

    public OpportunityServiceResponse map(OpportunityServiceEntity opportunityService) {
        return OpportunityServiceResponse.builder()
                .opportunityId(opportunityService.getOpportunityId())
                .userInService(opportunityService.getUserInService())
                .assignmentDate(opportunityService.getAssignmentDate())
                .conclusionDate(opportunityService.getConclusionDate())
                .reasonForConclusion(opportunityService.getReasonForConclusion())
                .build();
    }

    public OpportunityServiceEntity map(OpportunityServiceRequest request) {
        return OpportunityServiceEntity.builder()
                .opportunityId(request.opportunityId())
                .userInService(request.userInService())
                .assignmentDate(request.assignmentDate())
                .conclusionDate(request.conclusionDate())
                .reasonForConclusion(request.reasonForConclusion())
                .build();
    }
}
