package com.mobiauto.opportunityservice.utils.hateoas;

import com.mobiauto.opportunityservice.business.dtos.OpportunityServiceResponse;
import com.mobiauto.opportunityservice.controllers.OpportunityServiceController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class OpportunityServiceResponseHypermediaAssembler {

    public static void addLinks(OpportunityServiceResponse opportunityServiceResponseResponse) {
        opportunityServiceResponseResponse.add(
                linkTo(
                        methodOn(OpportunityServiceController.class)
                                .getOpportunityServiceByOpportunityId(opportunityServiceResponseResponse.getOpportunityId())
                ).withSelfRel()
        );
    }
}
