package com.mobiauto.resalemanagement;

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

    @Id
    @SequenceGenerator(
            name = "resale_id_sequence",
            sequenceName = "resale_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "resale_id_sequence"
    )
    private Integer id;
    private String socialName;
    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "cnpj")
    )
    private Cnpj cnpj;
}
