package com.mobiauto.opportunitymanagement.business.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mobiauto.opportunitymanagement.utils.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/* poderia colocar um @Pattern com o regex "(\\(\\d{2}\\)\\s)(\\d{4,5}\\-\\d{4})" em clientPhone,
 * mas optei por não fazer, já que existem países que não seguem esse padrão.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OpportunityRegistration(

        @JsonProperty("clientName")
        @NotBlank(message = "clientName"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        String clientName,

        @JsonProperty("clientEmail")
        @NotBlank(message = "clientEmail"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        @Email
        String clientEmail,

        @JsonProperty("clientPhone")
        @NotBlank(message = "clientPhone"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        String clientPhone,

        @JsonProperty("vehicleCompany")
        @NotBlank(message = "vehicleCompany"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        String vehicleCompany,

        @JsonProperty("vehicleModel")
        @NotBlank(message = "vehicleModel"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        String vehicleModel,

        @JsonProperty("vehicleVersion")
        @NotBlank(message = "vehicleVersion"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        String vehicleVersion,

        @JsonProperty("vehicleYear")
        @NotBlank(message = "vehicleYear"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        String vehicleYear,

        @JsonProperty("resaleId")
        @NotBlank(message = "resaleId"+Constants.BLANK_FIELD_ERROR_MESSAGE)
        Integer resaleId
) {
}
