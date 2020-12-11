package com.lampaderum.repository;

import com.lampaderum.domain.Roworder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Roworder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoworderRepository extends JpaRepository<Roworder, Long> {
}
