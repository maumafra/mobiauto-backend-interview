package com.mobiauto.systemsecurity.config;

import com.mobiauto.systemsecurity.user.entities.Role;
import com.mobiauto.systemsecurity.user.entities.User;
import com.mobiauto.systemsecurity.user.repositories.UserRepository;
import com.mobiauto.systemsecurity.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class ConfigurationService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailsService userDetailsService() {
        return username -> repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found: "+username));
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User setupStartupUser() {
        return userRepository.findByUsername("admin")
                .orElseGet(() -> {
                    User user = User.builder()
                            .username("admin")
                            .fullName("admin")
                            .email(Constants.STARTUP_USER_EMAIL)
                            .password(passwordEncoder.encode(Constants.STARTUP_USER_PASSWORD))
                            .role(Role.ADMINISTRATOR)
                            .build();
                    userRepository.saveAndFlush(user);
                    return user;
                });
    }
}
