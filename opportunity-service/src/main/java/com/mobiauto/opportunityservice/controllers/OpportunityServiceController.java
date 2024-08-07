package com.mobiauto.opportunityservice.controllers;

import com.mobiauto.opportunityservice.business.dtos.ConcludeOpporunityRequest;
import com.mobiauto.opportunityservice.business.dtos.OpportunityServiceRequest;
import com.mobiauto.opportunityservice.business.services.OpportunityServiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/opportunity-service")
@AllArgsConstructor
public class OpportunityServiceController {

    @Autowired
    private final OpportunityServiceService opportunityServiceService;

    @PostMapping(
            path = "/assign/{opportunityId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> assignOpportunity(final @PathVariable("opportunityId") Integer opportunityId) {
        return opportunityServiceService.assignOpportunity(opportunityId);
    }

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> registerOpportunityService(final @RequestBody @Valid OpportunityServiceRequest request) {
        return opportunityServiceService.registerOpportunityService(request);
    }

    @GetMapping(
            path = "/{opportunityId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getOpportunityServiceByOpportunityId(final @PathVariable("opportunityId") Integer opportunityId) {
        return opportunityServiceService.getOpportunityServiceByOpportunityId(opportunityId);
    }

    @PutMapping("/change-user/{opportunityId}/{username}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR', 'OWNER', 'MANAGER')")
    public ResponseEntity<?> changeOpportunityServiceUser(
            final @PathVariable("opportunityId") Integer opportunityId,
            final @PathVariable("userId") String username
    ) {
        return opportunityServiceService.changeOpportunityServiceUser(opportunityId, username);
    }

    @PutMapping(
            path = "/conclude/{opportunityId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> concludeOpportunity (
            final @PathVariable("opportunityId") Integer opportunityId,
            final @RequestBody ConcludeOpporunityRequest concludeOpporunityRequest
    ) {
        return opportunityServiceService.concludeOpportunity(opportunityId, concludeOpporunityRequest);
    }

    @DeleteMapping(path = "/delete/{opportunityId}")
    public ResponseEntity<?> deleteOpportunityService (final @PathVariable("opportunityId") Integer opportunityId) {
        return opportunityServiceService.deleteOpportunityService(opportunityId);
    }
}
