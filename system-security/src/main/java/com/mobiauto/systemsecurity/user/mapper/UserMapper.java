package com.mobiauto.systemsecurity.user.mapper;

import com.mobiauto.systemsecurity.user.dtos.UserResponse;
import com.mobiauto.systemsecurity.user.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse map(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .resaleId(user.getResaleId())
                .build();
    }
}
