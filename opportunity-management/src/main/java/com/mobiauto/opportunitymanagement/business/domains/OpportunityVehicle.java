package com.mobiauto.opportunitymanagement.business.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OpportunityVehicle {
    @Column(name = "vehicle_company")
    private String company;
    @Column(name = "vehicle_model")
    private String model;
    @Column(name = "vehicle_version")
    private String version;
    @Column(name = "vehicle_year")
    private String year;
}
