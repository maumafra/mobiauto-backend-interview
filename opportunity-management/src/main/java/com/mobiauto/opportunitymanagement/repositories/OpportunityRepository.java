package com.mobiauto.opportunitymanagement.repositories;

import com.mobiauto.opportunitymanagement.entities.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
}
