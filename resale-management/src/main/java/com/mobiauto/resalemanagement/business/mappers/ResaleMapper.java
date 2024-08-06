package com.mobiauto.resalemanagement.business.mappers;

import com.mobiauto.resalemanagement.business.domains.Cnpj;
import com.mobiauto.resalemanagement.business.dtos.ResaleResponse;
import com.mobiauto.resalemanagement.entities.Resale;
import com.mobiauto.resalemanagement.business.dtos.ResaleRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ResaleMapper {

    public Resale map(final ResaleRequest resaleRequest) {
        return Resale.builder()
                .socialName(resaleRequest.socialName())
                .cnpj(new Cnpj(resaleRequest.cnpj()))
                .build();
    }

    public ResaleResponse map(final Resale resale) {
        return ResaleResponse.builder()
                .resaleId(resale.getId())
                .socialName(resale.getSocialName())
                .cnpj(resale.getCnpj().value())
                .build();
    }
}
