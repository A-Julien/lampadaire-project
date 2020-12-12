package com.lampaderum.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.lampaderum.domain.Streetlamp;
import com.lampaderum.domain.*; // for static metamodels
import com.lampaderum.repository.StreetlampRepository;
import com.lampaderum.service.dto.StreetlampCriteria;
import com.lampaderum.service.dto.StreetlampDTO;
import com.lampaderum.service.mapper.StreetlampMapper;

/**
 * Service for executing complex queries for {@link Streetlamp} entities in the database.
 * The main input is a {@link StreetlampCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link StreetlampDTO} or a {@link Page} of {@link StreetlampDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class StreetlampQueryService extends QueryService<Streetlamp> {

    private final Logger log = LoggerFactory.getLogger(StreetlampQueryService.class);

    private final StreetlampRepository streetlampRepository;

    private final StreetlampMapper streetlampMapper;

    public StreetlampQueryService(StreetlampRepository streetlampRepository, StreetlampMapper streetlampMapper) {
        this.streetlampRepository = streetlampRepository;
        this.streetlampMapper = streetlampMapper;
    }

    /**
     * Return a {@link List} of {@link StreetlampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<StreetlampDTO> findByCriteria(StreetlampCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Streetlamp> specification = createSpecification(criteria);
        return streetlampMapper.toDto(streetlampRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link StreetlampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<StreetlampDTO> findByCriteria(StreetlampCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Streetlamp> specification = createSpecification(criteria);
        return streetlampRepository.findAll(specification, page)
            .map(streetlampMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(StreetlampCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Streetlamp> specification = createSpecification(criteria);
        return streetlampRepository.count(specification);
    }

    /**
     * Function to convert {@link StreetlampCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Streetlamp> createSpecification(StreetlampCriteria criteria) {
        Specification<Streetlamp> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Streetlamp_.id));
            }
            if (criteria.getLibstreetlamp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibstreetlamp(), Streetlamp_.libstreetlamp));
            }
            if (criteria.getModelestreetlamp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModelestreetlamp(), Streetlamp_.modelestreetlamp));
            }
            if (criteria.getDureeviestreetlamp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDureeviestreetlamp(), Streetlamp_.dureeviestreetlamp));
            }
            if (criteria.getUniteviestreetlamp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUniteviestreetlamp(), Streetlamp_.uniteviestreetlamp));
            }
            if (criteria.getMateriaustreetlamp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMateriaustreetlamp(), Streetlamp_.materiaustreetlamp));
            }
            if (criteria.getLiblampe() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLiblampe(), Streetlamp_.liblampe));
            }
            if (criteria.getPwlampe() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPwlampe(), Streetlamp_.pwlampe));
            }
            if (criteria.getFormelampe() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFormelampe(), Streetlamp_.formelampe));
            }
            if (criteria.getModelelampe() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModelelampe(), Streetlamp_.modelelampe));
            }
            if (criteria.getDureevielampe() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDureevielampe(), Streetlamp_.dureevielampe));
            }
            if (criteria.getUnitevielampe() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUnitevielampe(), Streetlamp_.unitevielampe));
            }
            if (criteria.getVoltlampe() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVoltlampe(), Streetlamp_.voltlampe));
            }
            if (criteria.getTemplampe() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTemplampe(), Streetlamp_.templampe));
            }
            if (criteria.getImagepathstreetlamp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImagepathstreetlamp(), Streetlamp_.imagepathstreetlamp));
            }
            if (criteria.getStockstreetlamp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStockstreetlamp(), Streetlamp_.stockstreetlamp));
            }
            if (criteria.getPricestreetlamp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPricestreetlamp(), Streetlamp_.pricestreetlamp));
            }
        }
        return specification;
    }
}
