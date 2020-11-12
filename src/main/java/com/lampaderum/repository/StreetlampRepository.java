package com.lampaderum.repository;

import com.lampaderum.domain.Streetlamp;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Streetlamp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StreetlampRepository extends JpaRepository<Streetlamp, Long> {
}
