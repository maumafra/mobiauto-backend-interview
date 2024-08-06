package com.mobiauto.resalemanagement.security.config;

import com.mobiauto.systemsecurity.config.ConfigurationService;
import com.mobiauto.systemsecurity.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class StartupConfig {
    @Autowired
    private ConfigurationService configurationService;

    @Bean
    public User setupStartupUser() {
        return configurationService.setupStartupUser();
    }
}
