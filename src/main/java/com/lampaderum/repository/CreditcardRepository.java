package com.lampaderum.repository;

import com.lampaderum.domain.Creditcard;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Creditcard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreditcardRepository extends JpaRepository<Creditcard, Long> {
}
