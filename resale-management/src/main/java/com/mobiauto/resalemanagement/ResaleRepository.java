package com.mobiauto.resalemanagement;

import com.mobiauto.resalemanagement.entities.Resale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResaleRepository extends JpaRepository<Resale, Integer> {
}
