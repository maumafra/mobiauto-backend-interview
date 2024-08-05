package com.mobiauto.opportunitymanagement.business.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

public enum OpportunityStatus {

    NEW("NEW"),
    IN_SERVICE("IN_SERVICE"),
    CONCLUDED("CONCLUDED");

    @Column(name = "status", nullable = false)
    private String opportunityStatus;

    OpportunityStatus(final String status) {
        this.opportunityStatus = status;
    }
}
