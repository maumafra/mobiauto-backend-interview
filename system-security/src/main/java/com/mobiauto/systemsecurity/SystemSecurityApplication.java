package com.mobiauto.systemsecurity;

import com.mobiauto.systemsecurity.auth.controller.AuthenticationController;
import com.mobiauto.systemsecurity.auth.mappers.UserAuthMapper;
import com.mobiauto.systemsecurity.auth.services.AuthenticationService;
import com.mobiauto.systemsecurity.config.ApplicationConfig;
import com.mobiauto.systemsecurity.config.ConfigurationService;
import com.mobiauto.systemsecurity.config.JwtAuthenticationFilter;
import com.mobiauto.systemsecurity.config.JwtService;
import com.mobiauto.systemsecurity.config.SecurityConfig;
import com.mobiauto.systemsecurity.config.StartupConfig;
import com.mobiauto.systemsecurity.config.UserDetailsConfig;
import com.mobiauto.systemsecurity.user.services.config.UserConfig;
import com.mobiauto.systemsecurity.user.controllers.UserController;
import com.mobiauto.systemsecurity.user.entities.User;
import com.mobiauto.systemsecurity.user.mapper.UserMapper;
import com.mobiauto.systemsecurity.user.repositories.UserRepository;
import com.mobiauto.systemsecurity.user.services.UserService;
import com.mobiauto.systemsecurity.utils.helper.ContextHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(
        basePackageClasses = {
                User.class
        }
)
@ComponentScan(
        basePackageClasses = {
                UserMapper.class,
                ContextHelper.class,
                UserController.class,
                UserService.class,
                JwtService.class,
                JwtAuthenticationFilter.class,
                ConfigurationService.class,
                AuthenticationService.class,
                UserAuthMapper.class,
                AuthenticationController.class
        }
)
@EnableJpaRepositories(
        basePackageClasses = {
                UserRepository.class
        }
)
@ConfigurationPropertiesScan(
        basePackageClasses = {
                UserConfig.class,
                ApplicationConfig.class,
                SecurityConfig.class,
                UserDetailsConfig.class,
                StartupConfig.class
        }
)
public class SystemSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemSecurityApplication.class, args);
    }
}
