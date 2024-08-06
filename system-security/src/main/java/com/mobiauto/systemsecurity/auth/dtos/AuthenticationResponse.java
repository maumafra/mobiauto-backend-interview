package com.mobiauto.systemsecurity.auth.dtos;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token
) {
}
