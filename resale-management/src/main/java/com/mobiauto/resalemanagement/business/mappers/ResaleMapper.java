package com.mobiauto.resalemanagement.business.mappers;

import com.mobiauto.resalemanagement.business.domains.Cnpj;
import com.mobiauto.resalemanagement.business.dtos.ResaleResponseDTO;
import com.mobiauto.resalemanagement.entities.Resale;
import com.mobiauto.resalemanagement.business.dtos.ResaleRequestDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ResaleMapper {

    public Resale map(final ResaleRequestDTO resaleRequestDTO) {
        return Resale.builder()
                .socialName(resaleRequestDTO.socialName())
                .cnpj(new Cnpj(resaleRequestDTO.cnpj()))
                .build();
    }

    public ResaleResponseDTO map(final Resale resale) {
        return ResaleResponseDTO.builder()
                .resaleId(resale.getId())
                .socialName(resale.getSocialName())
                .cnpj(resale.getCnpj().value())
                .build();
    }
}
