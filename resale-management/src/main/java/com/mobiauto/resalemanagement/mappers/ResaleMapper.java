package com.mobiauto.resalemanagement.mappers;

import com.mobiauto.resalemanagement.domains.Cnpj;
import com.mobiauto.resalemanagement.dtos.ResaleResponseDTO;
import com.mobiauto.resalemanagement.entities.Resale;
import com.mobiauto.resalemanagement.dtos.ResaleRequestDTO;
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
        return new ResaleResponseDTO(
                resale.getId(),
                resale.getSocialName(),
                resale.getCnpj().value()
        );
    }
}
