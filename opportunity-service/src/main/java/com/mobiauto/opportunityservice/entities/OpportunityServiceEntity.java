package com.mobiauto.opportunityservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OpportunityServiceEntity {

    private static final String OPPORTUNITY_SERVICE_ID_SEQUENCE = "opportunity_service_id_sequence";

    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = OPPORTUNITY_SERVICE_ID_SEQUENCE,
            sequenceName = OPPORTUNITY_SERVICE_ID_SEQUENCE
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = OPPORTUNITY_SERVICE_ID_SEQUENCE
    )
    private Integer id;

    @Column(name = "user_in_service")
    private String userInService;

    @Column(name = "opportunity_id", unique = true)
    private Integer opportunityId;

    @Column(name = "assignment_date")
    private LocalDateTime assignmentDate;

    @Column(name = "conclusion_date")
    private LocalDateTime conclusionDate;

    @Column(name = "reason_for_conclusion")
    private String reasonForConclusion;
}
