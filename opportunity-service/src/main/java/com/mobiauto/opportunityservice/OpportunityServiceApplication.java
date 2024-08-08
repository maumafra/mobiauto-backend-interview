package com.mobiauto.opportunityservice;

import com.mobiauto.opportunityservice.business.mappers.OpportunityServiceMapper;
import com.mobiauto.opportunityservice.business.services.OpportunityServiceService;
import com.mobiauto.opportunityservice.business.services.config.OpportunityServiceServiceConfig;
import com.mobiauto.opportunityservice.controllers.OpportunityServiceController;
import com.mobiauto.opportunityservice.entities.OpportunityServiceEntity;
import com.mobiauto.opportunityservice.repositories.OpportunityServiceRepository;
import com.mobiauto.opportunityservice.security.config.OpportunityServiceSecurityConfig;
import com.mobiauto.opportunityservice.utils.helper.ContextHelper;
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
                OpportunityServiceEntity.class
        }
)
@ComponentScan(
        basePackageClasses = {
                JwtService.class,
                JwtAuthenticationFilter.class,
                OpportunityServiceController.class,
                OpportunityServiceMapper.class,
                OpportunityServiceService.class,
                ContextHelper.class
        }
)
@EnableJpaRepositories(
        basePackageClasses = {
                OpportunityServiceRepository.class
        }
)
@ConfigurationPropertiesScan(
        basePackageClasses = {
                ApplicationConfig.class,
                OpportunityServiceSecurityConfig.class,
                OpportunityServiceServiceConfig.class
        }
)
public class OpportunityServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpportunityServiceApplication.class, args);
    }
}
