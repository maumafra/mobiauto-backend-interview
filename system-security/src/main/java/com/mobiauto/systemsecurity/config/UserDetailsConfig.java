package com.mobiauto.systemsecurity.config;

import com.mobiauto.systemsecurity.auth.dtos.UserDetailsResponse;
import com.mobiauto.systemsecurity.auth.mappers.UserAuthMapper;
import com.mobiauto.systemsecurity.user.entities.User;
import com.mobiauto.systemsecurity.user.repositories.UserRepository;
import com.mobiauto.systemsecurity.utils.exceptions.UserNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Configuration
@NoArgsConstructor
public class UserDetailsConfig {

    @Autowired
    private Optional<UserRepository> userRepository;

    private UserAuthMapper userAuthMapper = new UserAuthMapper();

    @Bean
    public UserDetailsService userDetailsService() {
        return userRepository.
                <UserDetailsService>map(
                        repository -> username -> repository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(username))
                ).orElseGet(
                        () -> username -> userAuthMapper.map(new RestTemplate().getForEntity(
                            "http://localhost:8082/api/v1/system-security/auth/user-details/" + username,
                                UserDetailsResponse.class
                        ).getBody())
                );
    }
}
