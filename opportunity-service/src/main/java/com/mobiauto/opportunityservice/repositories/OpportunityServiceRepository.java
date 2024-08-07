package com.mobiauto.opportunityservice.repositories;

import com.mobiauto.opportunityservice.entities.OpportunityServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpportunityServiceRepository extends JpaRepository<OpportunityServiceEntity, Integer> {
    Optional<OpportunityServiceEntity> findByOpportunityId(Integer opportunityId);
}
