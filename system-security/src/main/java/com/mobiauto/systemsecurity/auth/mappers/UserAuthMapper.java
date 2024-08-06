package com.mobiauto.systemsecurity.auth.mappers;

import com.mobiauto.systemsecurity.auth.dtos.UserRegistrationRequest;
import com.mobiauto.systemsecurity.user.entities.Role;
import com.mobiauto.systemsecurity.user.entities.User;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserAuthMapper {

    public User map(
            UserRegistrationRequest userRegistrationRequest,
            PasswordEncoder passwordEncoder
    ) {
        return User.builder()
                .username(userRegistrationRequest.username())
                .fullName(userRegistrationRequest.fullName())
                .email(userRegistrationRequest.email())
                .password(passwordEncoder.encode(userRegistrationRequest.password()))
                .resaleId(userRegistrationRequest.resaleId())
                .role(Role.ASSISTANT)
                .build();
    }
}
