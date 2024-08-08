package com.mobiauto.systemsecurity.config;

import com.mobiauto.systemsecurity.auth.mappers.UserAuthMapper;
import com.mobiauto.systemsecurity.user.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@NoArgsConstructor
public class ApplicationConfig {
    @Autowired
    private ConfigurationService configurationService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return configurationService.authenticationProvider();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return configurationService.authenticationManager(config);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return configurationService.passwordEncoder();
    }
}
