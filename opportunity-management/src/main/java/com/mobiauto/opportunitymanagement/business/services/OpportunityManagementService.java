package com.mobiauto.opportunitymanagement.business.services;

import com.mobiauto.opportunitymanagement.business.dtos.OpportunityRegistrationDTO;
import com.mobiauto.opportunitymanagement.business.dtos.OpportunityResponseDTO;
import com.mobiauto.opportunitymanagement.business.mappers.OpportunityMapper;
import com.mobiauto.opportunitymanagement.entities.Opportunity;
import com.mobiauto.opportunitymanagement.repositories.OpportunityRepository;
import com.mobiauto.opportunitymanagement.utils.exceptions.OpportunityNotFoundException;
import com.mobiauto.opportunitymanagement.utils.hateoas.OpportunityResponseHypermediaAssembler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OpportunityManagementService {

    @Autowired
    private final OpportunityRepository opportunityRepository;
    @Autowired
    private final OpportunityMapper opportunityMapper;


    public ResponseEntity<?> registerOpportunity(OpportunityRegistrationDTO opportunityRegistrationDTO) {
        try {
            Opportunity opportunity = opportunityMapper.map(opportunityRegistrationDTO);
            opportunityRepository.saveAndFlush(opportunity);
            OpportunityResponseDTO opportunityResponseDTO = opportunityMapper.map(opportunity);
            OpportunityResponseHypermediaAssembler.addLinks(opportunityResponseDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(opportunityResponseDTO);
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

    public ResponseEntity<?> getOpportunityById(Integer opportunityId) {
        try {
            Opportunity opportunity = opportunityRepository.findById(opportunityId)
                    .orElseThrow(() -> new OpportunityNotFoundException(opportunityId));
            OpportunityResponseDTO opportunityResponseDTO = opportunityMapper.map(opportunity);
            OpportunityResponseHypermediaAssembler.addLinks(opportunityResponseDTO);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(opportunityResponseDTO);
        } catch (OpportunityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
}
