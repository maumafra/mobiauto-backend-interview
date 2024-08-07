package com.mobiauto.opportunitymanagement.business.services;

import com.mobiauto.opportunitymanagement.business.domains.OpportunityStatus;
import com.mobiauto.opportunitymanagement.business.dtos.OpportunityRegistration;
import com.mobiauto.opportunitymanagement.business.dtos.OpportunityResponse;
import com.mobiauto.opportunitymanagement.business.mappers.OpportunityMapper;
import com.mobiauto.opportunitymanagement.entities.Opportunity;
import com.mobiauto.opportunitymanagement.repositories.OpportunityRepository;
import com.mobiauto.opportunitymanagement.utils.Constants;
import com.mobiauto.opportunitymanagement.utils.exceptions.OpportunityNotFoundException;
import com.mobiauto.opportunitymanagement.utils.hateoas.OpportunityResponseHypermediaAssembler;
import com.mobiauto.systemsecurity.user.dtos.ResaleResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
@AllArgsConstructor
public class OpportunityManagementService {

    @Autowired
    private final OpportunityRepository opportunityRepository;
    @Autowired
    private final OpportunityMapper opportunityMapper;
    @Autowired
    private final RestTemplate restTemplate;


    public ResponseEntity<?> registerOpportunity(OpportunityRegistration opportunityRegistration) {
        try {
            getRequest(
                    "http://localhost:8080/api/v1/resale-management/"+opportunityRegistration.resaleId(),
                    ResaleResponse.class
            );
            Opportunity opportunity = opportunityMapper.map(opportunityRegistration);
            opportunityRepository.saveAndFlush(opportunity);
            OpportunityResponse opportunityResponse = opportunityMapper.map(opportunity);
            OpportunityResponseHypermediaAssembler.addLinks(opportunityResponse);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(opportunityResponse);
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> getOpportunityById(Integer opportunityId) {
        try {
            Opportunity opportunity = opportunityRepository.findById(opportunityId)
                    .orElseThrow(() -> new OpportunityNotFoundException(opportunityId));
            OpportunityResponse opportunityResponse = opportunityMapper.map(opportunity);
            OpportunityResponseHypermediaAssembler.addLinks(opportunityResponse);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(opportunityResponse);
        } catch (OpportunityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> getAllOpportunities() {
        try {
            List<OpportunityResponse> opportunityList = opportunityRepository.findAll()
                    .stream()
                    .map(opportunityMapper::map)
                    .toList();
            OpportunityResponseHypermediaAssembler.addLinks(opportunityList);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(opportunityList);
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> deleteOpportunity(Integer opportunityId) {
        try {
            Opportunity opportunity = opportunityRepository.findById(opportunityId)
                    .orElseThrow(() -> new OpportunityNotFoundException(opportunityId));
            opportunityRepository.delete(opportunity);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Constants.OPPORTUNITY_DELETED_SUCCESSFULLY);
        } catch (OpportunityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> opportunityInService(Integer opportunityId) {
        return this.changeOpportunityStatus(opportunityId, OpportunityStatus.IN_SERVICE);
    }

    public ResponseEntity<?> opportunityConcluded(Integer opportunityId) {
        return this.changeOpportunityStatus(opportunityId, OpportunityStatus.CONCLUDED);
    }

    public ResponseEntity<?> opportunityNew(Integer opportunityId) {
        return this.changeOpportunityStatus(opportunityId, OpportunityStatus.NEW);
    }

    private ResponseEntity<?> changeOpportunityStatus(Integer opportunityId, OpportunityStatus status) {
        try {
            Opportunity opportunity = opportunityRepository.findById(opportunityId)
                    .orElseThrow(() -> new OpportunityNotFoundException(opportunityId));
            opportunity.setStatus(status);
            opportunityRepository.save(opportunity);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (OpportunityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    private <T> T getRequest(
            String url,
            Class<T> responseClass
    ) {
        final String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseClass);
        return response.getBody();
    }
}
