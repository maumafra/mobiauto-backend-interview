package com.mobiauto.systemsecurity.auth.mappers;

import com.mobiauto.systemsecurity.auth.dtos.UserDetailsResponse;
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
                .qttOpportunitiesAttended(0)
                .build();
    }

    public UserDetailsResponse map(User user) {
        return UserDetailsResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullname(user.getFullName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().name())
                .lastOpportunityReceived(user.getLastOpportunityReceived())
                .qttOpportunitiesAttended(user.getQttOpportunitiesAttended())
                .resaleId(user.getResaleId())
                .build();
    }

    public User map(UserDetailsResponse user) {
        return User.builder()
                .id(user.id())
                .username(user.username())
                .fullName(user.fullname())
                .email(user.email())
                .password(user.password())
                .role(Role.valueOf(user.role()))
                .lastOpportunityReceived(user.lastOpportunityReceived())
                .qttOpportunitiesAttended(user.qttOpportunitiesAttended())
                .resaleId(user.resaleId())
                .build();
    }
}
