package com.mobiauto.opportunitymanagement.utils.hateoas;

import com.mobiauto.opportunitymanagement.business.dtos.OpportunityResponse;
import com.mobiauto.opportunitymanagement.controllers.OpportunityManagementController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class OpportunityResponseHypermediaAssembler {

    public static void addLinks(OpportunityResponse opportunityResponse) {
        opportunityResponse.add(
                linkTo(
                        methodOn(OpportunityManagementController.class)
                                .getOpportunity(opportunityResponse.getOpportunityId())
                ).withSelfRel()
        );
    }

    public static void addLinks(List<OpportunityResponse> opportunityResponseList) {
        opportunityResponseList
                .forEach(
                        OpportunityResponseHypermediaAssembler::addLinks
                );
    }
}
