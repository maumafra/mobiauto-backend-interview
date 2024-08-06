package com.mobiauto.opportunitymanagement.utils.hateoas;

import com.mobiauto.opportunitymanagement.business.dtos.OpportunityResponseDTO;
import com.mobiauto.opportunitymanagement.controllers.OpportunityManagementController;

import java.util.List;

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

    public static void addLinks(List<OpportunityResponseDTO> opportunityResponseDTOList) {
        opportunityResponseDTOList
                .forEach(
                        OpportunityResponseHypermediaAssembler::addLinks
                );
    }
}
