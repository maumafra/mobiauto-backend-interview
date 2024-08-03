package com.mobiauto.resalemanagement.dto;

import com.mobiauto.resalemanagement.utils.constants.FieldConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResaleDTO(

        @JsonProperty(FieldConstants.SOCIAL_NAME)
        @NotNull(message = "socialName cannot be null")
        String socialName,

        @JsonProperty(FieldConstants.CNPJ)
        @NotNull(message = "cnpj cannot be null")
        String cnpj
) {
}
