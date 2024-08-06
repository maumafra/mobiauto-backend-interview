package com.mobiauto.resalemanagement.business.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mobiauto.resalemanagement.utils.Constants;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@JsonPropertyOrder({
        Constants.ID,
        Constants.SOCIAL_NAME,
        Constants.CNPJ
})
public class ResaleResponse extends RepresentationModel<ResaleResponse> {
        @JsonProperty(Constants.ID)
        private Integer resaleId;
        @JsonProperty(Constants.SOCIAL_NAME)
        private String socialName;
        @JsonProperty(Constants.CNPJ)
        private String cnpj;
}
