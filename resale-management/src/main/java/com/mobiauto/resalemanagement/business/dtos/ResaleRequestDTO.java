package com.mobiauto.resalemanagement.business.dtos;

import com.mobiauto.resalemanagement.utils.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResaleRequestDTO(

        @JsonProperty(Constants.SOCIAL_NAME)
        @NotBlank(message = "socialName cannot be null/blank")
        String socialName,

        @JsonProperty(Constants.CNPJ)
        @NotBlank(message = "cnpj cannot be null/blank")
        @Pattern(
                regexp = "(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)",
                message = "invalid cnpj format: it should follow the pattern 00.000.000/0000-00"
        )
        String cnpj
) {
}
