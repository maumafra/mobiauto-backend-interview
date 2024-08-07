package com.mobiauto.systemsecurity.user.services;

import com.mobiauto.systemsecurity.user.dtos.ResaleResponse;
import com.mobiauto.systemsecurity.user.dtos.UpdateResaleIdRequest;
import com.mobiauto.systemsecurity.user.dtos.UpdateRoleRequest;
import com.mobiauto.systemsecurity.user.dtos.UserResponse;
import com.mobiauto.systemsecurity.user.entities.Role;
import com.mobiauto.systemsecurity.user.mapper.UserMapper;
import com.mobiauto.systemsecurity.user.entities.User;
import com.mobiauto.systemsecurity.user.repositories.UserRepository;
import com.mobiauto.systemsecurity.utils.Constants;
import com.mobiauto.systemsecurity.utils.exceptions.UserNotFoundException;
import com.mobiauto.systemsecurity.utils.helper.ContextHelper;
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

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final ContextHelper contextHelper;
    @Autowired
    private final RestTemplate restTemplate;

    public ResponseEntity<?> getUserById(final Integer userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));
            UserResponse userResponse = userMapper.map(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userResponse);
        } catch (UserNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    public ResponseEntity<?> getUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException(username));
            UserResponse userResponse = userMapper.map(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userResponse);
        } catch (UserNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    public ResponseEntity<?> deleteUserById(Integer userId) {
        try {
            final User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));
            final User requestUser = contextHelper.getContextUser();
            if (checkAuthorities(requestUser, user.getResaleId())) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .build();
            }
            userRepository.delete(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Constants.USER_DELETED_SUCCESSFULLY);
        } catch (UserNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
    }

    public ResponseEntity<?> updateRole(UpdateRoleRequest updateRoleRequest) {
        try {
            final User user = userRepository.findById(updateRoleRequest.userId())
                    .orElseThrow(() -> new UserNotFoundException(updateRoleRequest.userId()));
            final User requestUser = contextHelper.getContextUser();
            if (checkAuthorities(requestUser, user.getResaleId())) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .build();
            }
            final Role role = Role.valueOf(updateRoleRequest.newRole());
            user.setRole(role);
            userRepository.save(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (UserNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> updateResaleId(UpdateResaleIdRequest updateResaleIdRequest) {
        try {
            final User user = userRepository.findById(updateResaleIdRequest.userId())
                    .orElseThrow(() -> new UserNotFoundException(updateResaleIdRequest.userId()));
            ResaleResponse resaleResponse = getRequest(
                    "http://localhost:8080/api/v1/resale-management/"+updateResaleIdRequest.newResaleId(),
                    ResaleResponse.class
            );
            user.setResaleId(resaleResponse.resaleId());
            userRepository.save(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (UserNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> getAssignableUsers(Integer resaleId) {
        try {
            final List<String> usernames = userRepository.findUsersWithLeastOpportunities(resaleId)
                    .orElseThrow(RuntimeException::new);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(usernames);
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> userAssigned(String username) {
        return this.updateNumberOfOpportunities(username, +1);
    }

    public ResponseEntity<?> concludedOpportunity(String username) {
        return this.updateNumberOfOpportunities(username, -1);
    }

    private ResponseEntity<?> updateNumberOfOpportunities(String username, int increment) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException(username));
            user.setQttOpportunitiesAttended(user.getQttOpportunitiesAttended()+increment);
            if (increment > 0) {
                user.setLastOpportunityReceived(LocalDateTime.now());
            }
            userRepository.save(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (UserNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
    }


    private boolean checkAuthorities(User requestUser, Integer resaleId) {
        return !requestUser.getRole().equals(Role.ADMINISTRATOR) && !requestUser.getResaleId().equals(resaleId);
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
