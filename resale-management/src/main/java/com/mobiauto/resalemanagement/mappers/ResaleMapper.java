package com.mobiauto.resalemanagement.mappers;

import com.mobiauto.resalemanagement.domain.Cnpj;
import com.mobiauto.resalemanagement.entities.Resale;
import com.mobiauto.resalemanagement.dto.ResaleDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ResaleMapper {

    public Resale map(final ResaleDTO resaleDTO) {
        return Resale.builder()
                .socialName(resaleDTO.socialName())
                .cnpj(new Cnpj(resaleDTO.cnpj()))
                .build();
    }

    public ResaleDTO map(final Resale resale) {
        return new ResaleDTO(
                resale.getSocialName(),
                resale.getCnpj().value()
        );
    }
}
