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
public class OpportunityClient {
    @Column(name = "client_name")
    private String name;
    @Column(name = "client_email")
    private String email;
    @Column(name = "client_phone")
    private String phone;
}
