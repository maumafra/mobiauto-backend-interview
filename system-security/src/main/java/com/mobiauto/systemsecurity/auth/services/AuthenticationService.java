package com.mobiauto.systemsecurity.auth.services;

import com.mobiauto.systemsecurity.auth.dtos.AuthenticationResponse;
import com.mobiauto.systemsecurity.auth.dtos.UserAuthenticationRequest;
import com.mobiauto.systemsecurity.auth.dtos.UserRegistrationRequest;
import com.mobiauto.systemsecurity.auth.mappers.UserAuthMapper;
import com.mobiauto.systemsecurity.config.JwtService;
import com.mobiauto.systemsecurity.user.entities.User;
import com.mobiauto.systemsecurity.user.repositories.UserRepository;
import com.mobiauto.systemsecurity.utils.helper.ContextHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserAuthMapper userAuthMapper;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final ContextHelper contextHelper;

    public ResponseEntity<?> register(final UserRegistrationRequest request) {
        try {
            User user = userAuthMapper.map(request, passwordEncoder);
            User requestUser = contextHelper.getContextUser();
            if (!requestUser.getRole().name().equals("ADMINISTRATOR")) {
                user.setResaleId(requestUser.getResaleId());
            }
            userRepository.saveAndFlush(user);
            final String jwtToken = jwtService.generateToken(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(AuthenticationResponse.builder()
                            .token(jwtToken)
                            .build()
                    );
        } catch (Exception ex) {
            return  ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    public ResponseEntity<?> authenticate(UserAuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(),
                            request.password()
                    )
            );
            User user = userRepository.findByUsername(request.username())
                    .orElseThrow(() -> new UsernameNotFoundException("username nor found: "+request.username()));
            final String jwtToken = jwtService.generateToken(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(AuthenticationResponse.builder()
                            .token(jwtToken)
                            .build()
                    );
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (AuthenticationException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }  catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
