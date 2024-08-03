package com.mobiauto.resalemanagement.entities;

import com.mobiauto.resalemanagement.business.domains.Cnpj;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Resale {

    private static final String RESALE_ID_SEQUENCE = "resale_id_sequence";

    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = RESALE_ID_SEQUENCE,
            sequenceName = RESALE_ID_SEQUENCE
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = RESALE_ID_SEQUENCE
    )
    private Integer id;

    @Column(name = "social_name")
    private String socialName;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(
                    name = "cnpj",
                    unique = true
            )
    )
    private Cnpj cnpj;
}
