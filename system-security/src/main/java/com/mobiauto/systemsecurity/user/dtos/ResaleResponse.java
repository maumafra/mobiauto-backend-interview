package com.mobiauto.systemsecurity.user.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResaleResponse(
        @JsonProperty("id")
        Integer resaleId,
        @JsonProperty("socialName")
        String socialName,
        @JsonProperty("cnpj")
        String cnpj
) {
}
