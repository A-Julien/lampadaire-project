package com.lampaderum.repository;

import com.lampaderum.domain.SOrder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SOrderRepository extends JpaRepository<SOrder, Long> {
}
