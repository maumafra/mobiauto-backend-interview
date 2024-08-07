package com.mobiauto.opportunityservice.business.services;

import com.mobiauto.opportunityservice.business.dtos.ConcludeOpporunityRequest;
import com.mobiauto.opportunityservice.business.dtos.OpportunityServiceRequest;
import com.mobiauto.opportunityservice.business.dtos.OpportunityServiceResponse;
import com.mobiauto.opportunityservice.business.dtos.oppportunity.OpportunityResponse;
import com.mobiauto.opportunityservice.business.dtos.user.UserResponse;
import com.mobiauto.opportunityservice.business.mappers.OpportunityServiceMapper;
import com.mobiauto.opportunityservice.entities.OpportunityServiceEntity;
import com.mobiauto.opportunityservice.repositories.OpportunityServiceRepository;
import com.mobiauto.opportunityservice.utils.exceptions.ForbiddenOperationException;
import com.mobiauto.opportunityservice.utils.exceptions.GetRequestException;
import com.mobiauto.opportunityservice.utils.exceptions.OpportunityAlreadyAssignedException;
import com.mobiauto.opportunityservice.utils.exceptions.OpportunityServiceNotFoundException;
import com.mobiauto.opportunityservice.utils.hateoas.OpportunityServiceResponseHypermediaAssembler;
import com.mobiauto.opportunityservice.utils.helper.ContextHelper;
import jakarta.validation.Valid;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OpportunityServiceService {

    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private final OpportunityServiceRepository opportunityServiceRepository;
    @Autowired
    private final OpportunityServiceMapper opportunityServiceMapper;
    @Autowired
    private final ContextHelper contextHelper;

    public ResponseEntity<?> assignOpportunity(final Integer opportunityId) {
        try {
            OpportunityResponse opportunityResponse = getOpportunity(opportunityId);
            checkOpportunityStatus(opportunityResponse);
            final Integer resaleId = opportunityResponse.resaleId();
            List<String> assignableUsers = getAssignableUsers(resaleId);
            String username = assignableUsers.stream().findFirst()
                    .orElseThrow(RuntimeException::new);
            OpportunityServiceEntity opportunityService = opportunityServiceMapper.map(opportunityId, username);
            opportunityServiceRepository.saveAndFlush(opportunityService);
            assignUser(username);
            setOpportunityInService(opportunityId);
            OpportunityServiceResponse opportunityServiceResponse = opportunityServiceMapper.map(opportunityService);
            OpportunityServiceResponseHypermediaAssembler.addLinks(opportunityServiceResponse);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(opportunityServiceResponse);
        } catch (GetRequestException ex) {
            return ResponseEntity
                    .status(ex.getStatusCode())
                    .body(ex.getMessage());
        } catch (OpportunityAlreadyAssignedException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> registerOpportunityService(final OpportunityServiceRequest request) {
        try {
            OpportunityResponse opportunityResponse = getOpportunity(request.opportunityId());
            checkResaleAuthorization(opportunityResponse.resaleId());
            checkOpportunityStatus(opportunityResponse);
            getUser(request.userInService());
            OpportunityServiceEntity opportunityService = opportunityServiceMapper.map(request);
            if (opportunityService.getAssignmentDate() == null) {
                opportunityService.setAssignmentDate(LocalDateTime.now());
            }
            opportunityServiceRepository.saveAndFlush(opportunityService);
            if (request.conclusionDate() != null) {
                setOpportunityConcluded(request.opportunityId());
            } else {
                assignUser(request.userInService());
                setOpportunityInService(request.opportunityId());
            }
            OpportunityServiceResponse opportunityServiceResponse = opportunityServiceMapper.map(opportunityService);
            OpportunityServiceResponseHypermediaAssembler.addLinks(opportunityServiceResponse);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(opportunityServiceResponse);
        } catch (GetRequestException ex) {
            return ResponseEntity
                    .status(ex.getStatusCode())
                    .body(ex.getMessage());
        } catch (OpportunityAlreadyAssignedException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        } catch (ForbiddenOperationException ex) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> getOpportunityServiceByOpportunityId(final Integer opportunityId) {
        try {
            getOpportunity(opportunityId);
            OpportunityServiceEntity opportunityService = getOpportunityServiceEntityByOpportunityId(opportunityId);
            OpportunityServiceResponse opportunityServiceResponse = opportunityServiceMapper.map(opportunityService);
            OpportunityServiceResponseHypermediaAssembler.addLinks(opportunityServiceResponse);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(opportunityServiceResponse);
        } catch (GetRequestException ex) {
            return ResponseEntity
                    .status(ex.getStatusCode())
                    .body(ex.getMessage());
        } catch (OpportunityServiceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    public ResponseEntity<?> changeOpportunityServiceUser(final Integer opportunityId, final String newUser) {
        try {
            OpportunityResponse opportunityResponse = getOpportunity(opportunityId);
            checkResaleAuthorization(opportunityResponse.resaleId());
            getUser(newUser);
            OpportunityServiceEntity opportunityService = getOpportunityServiceEntityByOpportunityId(opportunityId);
            final String oldUser = opportunityService.getUserInService();
            opportunityService.setUserInService(newUser);
            opportunityServiceRepository.save(opportunityService);
            userConcluded(oldUser);
            assignUser(newUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (GetRequestException ex) {
            return ResponseEntity
                    .status(ex.getStatusCode())
                    .body(ex.getMessage());
        } catch (ForbiddenOperationException ex) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        } catch (OpportunityServiceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    public ResponseEntity<?> concludeOpportunity(
            final Integer opportunityId,
            final @Valid ConcludeOpporunityRequest concludeOpporunityRequest
    ) {
        try {
            OpportunityResponse opportunityResponse = getOpportunity(opportunityId);
            checkResaleAuthorization(opportunityResponse.resaleId());
            OpportunityServiceEntity opportunityService = getOpportunityServiceEntityByOpportunityId(opportunityId);
            opportunityService.setConclusionDate(LocalDateTime.now());
            opportunityService.setReasonForConclusion(concludeOpporunityRequest.reasonForConclusion());
            opportunityServiceRepository.save(opportunityService);
            userConcluded(opportunityService.getUserInService());
            setOpportunityConcluded(opportunityId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (GetRequestException ex) {
            return ResponseEntity
                    .status(ex.getStatusCode())
                    .body(ex.getMessage());
        } catch (ForbiddenOperationException ex) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        } catch (OpportunityServiceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    public ResponseEntity<?> deleteOpportunityService(final Integer opportunityId) {
        try {
            OpportunityResponse opportunityResponse = getOpportunity(opportunityId);
            checkResaleAuthorization(opportunityResponse.resaleId());
            OpportunityServiceEntity opportunityService = getOpportunityServiceEntityByOpportunityId(opportunityId);
            final String userInService = opportunityService.getUserInService();
            opportunityServiceRepository.delete(opportunityService);
            setOpportunityNew(opportunityId);
            userConcluded(userInService);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (GetRequestException ex) {
            return ResponseEntity
                    .status(ex.getStatusCode())
                    .body(ex.getMessage());
        } catch (ForbiddenOperationException ex) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        } catch (OpportunityServiceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    //PRIVATE METHODS BELOW
    private void checkOpportunityStatus(final OpportunityResponse opportunityResponse) {
        if (!("NEW").equals(opportunityResponse.status())) {
            throw new OpportunityAlreadyAssignedException(opportunityResponse.id());
        }
    }

    private void checkResaleAuthorization(final Integer resaleId) {
        final boolean admin = ("ADMINISTRATOR").equals(contextHelper.getContextUser().getRole().name());
        final Integer contextResale = contextHelper.getContextUser().getResaleId();
        if (!admin && !Objects.equals(contextResale, resaleId)) {
            throw new ForbiddenOperationException();
        }
    }

    private void checkUserAuthorization(final String username) {
        final boolean assistant = ("ASSISTANT").equals(contextHelper.getContextUser().getRole().name());
        final String contextUsername = contextHelper.getContextUser().getUsername();
        if (assistant && !contextUsername.equals(username)) {
            throw new ForbiddenOperationException();
        }
    }

    private OpportunityServiceEntity getOpportunityServiceEntityByOpportunityId(final Integer opportunityId) {
        return opportunityServiceRepository.findByOpportunityId(opportunityId)
                .orElseThrow(() -> new OpportunityServiceNotFoundException(opportunityId));
    }

    private OpportunityResponse getOpportunity(final Integer opportunityId) {
        return getRequest(
                "http://localhost:8081/api/v1/opportunity-management/"+opportunityId,
                OpportunityResponse.class
        );
    }

    private UserResponse getUser(final String username) {
        return getRequest(
                "http://localhost:8082/api/v1/system-security/user/get/"+username,
                UserResponse.class
        );
    }

    private List<String> getAssignableUsers(final Integer resaleId) {
        return getRequest(
                "http://localhost:8082/api/v1/system-security/user/assignable-users/"+resaleId,
                List.class
        );
    }

    private void setOpportunityInService(final Integer opportunityId) {
        putRequest(
                "http://localhost:8081/api/v1/opportunity-management/opportunity-in-service/"+opportunityId
        );
    }

    private void setOpportunityConcluded(final Integer opportunityId) {
        putRequest(
                "http://localhost:8081/api/v1/opportunity-management/opportunity-concluded/"+opportunityId
        );
    }

    private void setOpportunityNew(final Integer opportunityId) {
        putRequest(
                "http://localhost:8081/api/v1/opportunity-management/opportunity-new/"+opportunityId
        );
    }

    private void assignUser(final String username) {
        putRequest(
                "http://localhost:8082/api/v1/system-security/user/assigned-for-opportunity/"+username
        );
    }

    private void userConcluded(final String username) {
        putRequest(
                "http://localhost:8082/api/v1/system-security/user/concluded-opportunity/"+username
        );
    }

    private <T> T getRequest(
            final String url,
            Class<T> responseClass
    ) {
        final String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseClass);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new GetRequestException(String.valueOf(response.getBody()), response.getStatusCode());
        }
        return response.getBody();
    }

    private void putRequest(
            final String url
    ) {
        final String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
    }
}
