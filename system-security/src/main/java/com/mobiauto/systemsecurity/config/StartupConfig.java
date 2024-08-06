package com.mobiauto.systemsecurity.config;

import com.mobiauto.systemsecurity.user.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class StartupConfig {
    @Autowired
    private ConfigurationService configurationService;

    @Bean
    public User setupStartupUser() {
        return configurationService.setupStartupUser();
    }
}
