package com.mobiauto.opportunitymanagement.controllers;


import com.mobiauto.opportunitymanagement.business.dtos.OpportunityRegistrationDTO;
import com.mobiauto.opportunitymanagement.business.services.OpportunityManagementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/opportunity-management")
@AllArgsConstructor
public class OpportunityManagementController {

    @Autowired
    private final OpportunityManagementService opportunityManagementService;

    @GetMapping(
            path = "/{opportunityId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getOpportunity(final @PathVariable("opportunityId") Integer opportunityId) {
        return opportunityManagementService.getOpportunityById(opportunityId);
    }

    @PostMapping(
            path = "/register-opportunity",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> registerOpportunity(final @RequestBody @Valid OpportunityRegistrationDTO opportunityRegistrationDTO){
        return opportunityManagementService.registerOpportunity(opportunityRegistrationDTO);
    }
}
