package com.mobiauto.resalemanagement;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ResaleMapper {

    public Resale map (final ResaleRegistrationRequest resaleRegistrationRequest) {
        return Resale.builder()
                .socialName(resaleRegistrationRequest.socialName())
                .cnpj(new Cnpj(resaleRegistrationRequest.cnpj()))
                .build();
    }
}
