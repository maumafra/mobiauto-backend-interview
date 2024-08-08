package com.mobiauto.opportunitymanagement;

import com.mobiauto.opportunitymanagement.business.mappers.OpportunityMapper;
import com.mobiauto.opportunitymanagement.business.services.OpportunityManagementService;
import com.mobiauto.opportunitymanagement.controllers.OpportunityManagementController;
import com.mobiauto.opportunitymanagement.entities.Opportunity;
import com.mobiauto.opportunitymanagement.repositories.OpportunityRepository;
import com.mobiauto.opportunitymanagement.security.config.OpportunityManagementSecurityConfig;
import com.mobiauto.systemsecurity.config.ApplicationConfig;
import com.mobiauto.systemsecurity.config.JwtAuthenticationFilter;
import com.mobiauto.systemsecurity.config.JwtService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(
        basePackageClasses = {
                Opportunity.class
        }
)
@ComponentScan(
        basePackageClasses = {
                JwtService.class,
                JwtAuthenticationFilter.class,
                OpportunityManagementController.class,
                OpportunityMapper.class,
                OpportunityManagementService.class
        }
)
@EnableJpaRepositories(
        basePackageClasses = {
                OpportunityRepository.class
        }
)
@ConfigurationPropertiesScan(
        basePackageClasses = {
                ApplicationConfig.class,
                OpportunityManagementSecurityConfig.class
        }
)
public class OpportunityManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpportunityManagementApplication.class, args);
    }
}
