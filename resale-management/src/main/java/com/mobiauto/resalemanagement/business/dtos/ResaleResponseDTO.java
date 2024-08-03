package com.mobiauto.resalemanagement.business.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mobiauto.resalemanagement.utils.constants.Constants;
import lombok.Builder;
import org.springframework.hateoas.RepresentationModel;

@Builder
@JsonPropertyOrder({
        Constants.ID,
        Constants.SOCIAL_NAME,
        Constants.CNPJ
})
public class ResaleResponseDTO extends RepresentationModel<ResaleResponseDTO> {
        @JsonProperty(Constants.ID)
        private Integer resaleId;
        @JsonProperty(Constants.SOCIAL_NAME)
        private String socialName;
        @JsonProperty(Constants.CNPJ)
        private String cnpj;

        public Integer getResaleId() {
                return this.resaleId;
        }
}
