package com.mobiauto.opportunitymanagement.entities;

import com.mobiauto.opportunitymanagement.business.domains.OpportunityClient;
import com.mobiauto.opportunitymanagement.business.domains.OpportunityStatus;
import com.mobiauto.opportunitymanagement.business.domains.OpportunityVehicle;
import com.mobiauto.opportunitymanagement.utils.Constants;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Opportunity {

    private static final String OPPORTUNITY_ID_SEQUENCE = "opportunity_id_sequence";

    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = OPPORTUNITY_ID_SEQUENCE,
            sequenceName = OPPORTUNITY_ID_SEQUENCE
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = OPPORTUNITY_ID_SEQUENCE
    )
    private Integer id;

    @Enumerated(EnumType.STRING)
    @AttributeOverride(name = Constants.STATUS, column = @Column(name = Constants.STATUS))
    private OpportunityStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "client_name")),
            @AttributeOverride(name = "email", column = @Column(name = "client_email")),
            @AttributeOverride(name = "phone", column = @Column(name = "client_phone"))
    })
    private OpportunityClient client;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "company", column = @Column(name = "vehicle_company")),
            @AttributeOverride(name = "model", column = @Column(name = "vehicle_model")),
            @AttributeOverride(name = "version", column = @Column(name = "vehicle_version")),
            @AttributeOverride(name = "year", column = @Column(name = "vehicle_year"))
    })
    private OpportunityVehicle vehicle;

    @Column(name = "resale_id")
    private Integer resaleId;

    @AttributeOverride(name = "reasonForConclusion", column = @Column(name = "reason_for_conclusion"))
    private String reasonForConclusion;
}
