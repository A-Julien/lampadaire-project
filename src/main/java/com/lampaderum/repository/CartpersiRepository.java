package com.lampaderum.repository;

import com.lampaderum.domain.Cartpersi;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Cartpersi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CartpersiRepository extends JpaRepository<Cartpersi, Long> {
}
