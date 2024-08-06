package com.mobiauto.opportunitymanagement.controllers;


import com.mobiauto.opportunitymanagement.business.dtos.OpportunityRegistrationDTO;
import com.mobiauto.opportunitymanagement.business.services.OpportunityManagementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(
            path = "/opportunities",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllOpportunities() {
        return opportunityManagementService.getAllOpportunities();
    }

    @PostMapping(
            path = "/register-opportunity",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> registerOpportunity(final @RequestBody @Valid OpportunityRegistrationDTO opportunityRegistrationDTO){
        return opportunityManagementService.registerOpportunity(opportunityRegistrationDTO);
    }

    @DeleteMapping(path = "/delete-opportunity/{opportunityId}")
    public ResponseEntity<?> deleteOpportunity(final @PathVariable("opportunityId") Integer opportunityId) {
        return opportunityManagementService.deleteOpportunity(opportunityId);
    }
}
