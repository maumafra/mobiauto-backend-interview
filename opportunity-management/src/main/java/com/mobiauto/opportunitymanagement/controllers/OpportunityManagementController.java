package com.mobiauto.opportunitymanagement.controllers;


import com.mobiauto.opportunitymanagement.business.dtos.OpportunityRegistration;
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
    public ResponseEntity<?> registerOpportunity(final @RequestBody @Valid OpportunityRegistration opportunityRegistration){
        return opportunityManagementService.registerOpportunity(opportunityRegistration);
    }

    @PutMapping(
            path = "/opportunity-in-service/{opportunityId}"
    )
    public ResponseEntity<?> opportunityInService(final @PathVariable("opportunityId") Integer opportunityId) {
        return opportunityManagementService.opportunityInService(opportunityId);
    }

    @PutMapping(
            path = "/opportunity-concluded/{opportunityId}"
    )
    public ResponseEntity<?> opportunityConcluded(final @PathVariable("opportunityId") Integer opportunityId) {
        return opportunityManagementService.opportunityConcluded(opportunityId);
    }

    @PutMapping(
            path = "/opportunity-new/{opportunityId}"
    )
    public ResponseEntity<?> opportunityNew(final @PathVariable("opportunityId") Integer opportunityId) {
        return opportunityManagementService.opportunityNew(opportunityId);
    }

    @DeleteMapping(path = "/delete-opportunity/{opportunityId}")
    public ResponseEntity<?> deleteOpportunity(final @PathVariable("opportunityId") Integer opportunityId) {
        return opportunityManagementService.deleteOpportunity(opportunityId);
    }
}
