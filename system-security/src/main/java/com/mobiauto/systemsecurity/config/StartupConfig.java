package com.mobiauto.systemsecurity.config;

import com.mobiauto.systemsecurity.user.entities.Role;
import com.mobiauto.systemsecurity.user.entities.User;
import com.mobiauto.systemsecurity.user.repositories.UserRepository;
import com.mobiauto.systemsecurity.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@AllArgsConstructor
public class StartupConfig {

    @Autowired
    private Optional<UserRepository> userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public void setupStartupUser() {
        userRepository.ifPresent(repository -> repository.findByUsername("admin")
                .orElseGet(() -> {
                    User user = User.builder()
                            .username("admin")
                            .fullName("admin")
                            .email(Constants.STARTUP_USER_EMAIL)
                            .password(passwordEncoder.encode(Constants.STARTUP_USER_PASSWORD))
                            .role(Role.ADMINISTRATOR)
                            .qttOpportunitiesAttended(0)
                            .build();
                    repository.saveAndFlush(user);
                    return user;
                }));
    }
}
