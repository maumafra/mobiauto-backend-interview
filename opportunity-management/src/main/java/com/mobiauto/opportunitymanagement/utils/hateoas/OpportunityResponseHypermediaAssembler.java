package com.mobiauto.opportunitymanagement.utils.hateoas;

import com.mobiauto.opportunitymanagement.business.dtos.OpportunityResponseDTO;
import com.mobiauto.opportunitymanagement.controllers.OpportunityManagementController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class OpportunityResponseHypermediaAssembler {

    public static void addLinks(OpportunityResponseDTO opportunityResponseDTO) {
        opportunityResponseDTO.add(
                linkTo(
                        methodOn(OpportunityManagementController.class)
                                .getOpportunity(opportunityResponseDTO.getOpportunityId())
                ).withSelfRel()
        );
    }
}
