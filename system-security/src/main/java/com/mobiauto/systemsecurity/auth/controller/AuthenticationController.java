package com.mobiauto.systemsecurity.auth.controller;

import com.mobiauto.systemsecurity.auth.dtos.UserAuthenticationRequest;
import com.mobiauto.systemsecurity.auth.dtos.UserDetailsResponse;
import com.mobiauto.systemsecurity.auth.dtos.UserRegistrationRequest;
import com.mobiauto.systemsecurity.auth.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/system-security/auth")
@AllArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMINISTRATOR', 'OWNER', 'MANAGER')")
    public ResponseEntity<?> register(final @RequestBody @Valid UserRegistrationRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(final @RequestBody UserAuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

    @GetMapping(
            path = "/user-details/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserDetailsResponse getUserDetails(final @PathVariable("username") String username) {
        return authenticationService.getUserDetails(username);
    }
}
