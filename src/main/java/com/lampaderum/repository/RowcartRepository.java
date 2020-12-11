package com.lampaderum.repository;

import com.lampaderum.domain.Rowcart;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Rowcart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RowcartRepository extends JpaRepository<Rowcart, Long> {
}
