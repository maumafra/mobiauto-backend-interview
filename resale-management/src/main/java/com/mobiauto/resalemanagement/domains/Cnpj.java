package com.mobiauto.resalemanagement.domains;

import com.mobiauto.resalemanagement.utils.validators.CNPJValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
//Gostaria de implementar record, entretanto Embbedable records é mais viável no hibernate 6.2
public class Cnpj implements Serializable {

    private static final CNPJValidator VALIDATOR = new CNPJValidator();

    @Column(name = "value", nullable = false)
    private String value;

    public Cnpj(String value) {
        if (!VALIDATOR.isValid(value)) {
            throw new IllegalArgumentException("invalid cnpj: "+value);
        }
        this.value = value;
    }

    public String value(){
        return this.value;
    }
}
