package com.mobiauto.systemsecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    @Autowired
    private ConfigurationService configurationService;
    @Bean
    public UserDetailsService userDetailsService() {
        return configurationService.userDetailsService();
    }

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
