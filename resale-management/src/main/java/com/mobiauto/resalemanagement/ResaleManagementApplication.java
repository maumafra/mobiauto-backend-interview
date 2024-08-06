package com.mobiauto.resalemanagement;

import com.mobiauto.resalemanagement.business.mappers.ResaleMapper;
import com.mobiauto.resalemanagement.business.services.ResaleManagementService;
import com.mobiauto.resalemanagement.controllers.ResaleManagementController;
import com.mobiauto.resalemanagement.entities.Resale;
import com.mobiauto.resalemanagement.repositories.ResaleRepository;
import com.mobiauto.resalemanagement.security.config.SecurityConfig;
import com.mobiauto.systemsecurity.config.ApplicationConfig;
import com.mobiauto.systemsecurity.config.ConfigurationService;
import com.mobiauto.systemsecurity.config.JwtAuthenticationFilter;
import com.mobiauto.systemsecurity.config.JwtService;
import com.mobiauto.systemsecurity.config.StartupConfig;
import com.mobiauto.systemsecurity.user.entities.User;
import com.mobiauto.systemsecurity.user.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(
        basePackageClasses = {
                Resale.class,
                User.class
        }
)
@ComponentScan(
        basePackageClasses = {
                JwtService.class,
                JwtAuthenticationFilter.class,
                ConfigurationService.class,
                ResaleManagementController.class,
                ResaleMapper.class,
                ResaleManagementService.class
        }
)
@EnableJpaRepositories(
        basePackageClasses = {
                ResaleRepository.class,
                UserRepository.class
        }
)
@ConfigurationPropertiesScan(
        basePackageClasses = {
                ApplicationConfig.class,
                SecurityConfig.class,
                StartupConfig.class
        }
)
public class ResaleManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResaleManagementApplication.class, args);
    }
}
