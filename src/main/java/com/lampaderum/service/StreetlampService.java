package com.lampaderum.service;

import com.lampaderum.domain.Streetlamp;
import com.lampaderum.repository.StreetlampRepository;
import com.lampaderum.service.dto.StreetlampDTO;
import com.lampaderum.service.mapper.StreetlampMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Streetlamp}.
 */
@Service
@Transactional
public class StreetlampService {

    private final Logger log = LoggerFactory.getLogger(StreetlampService.class);

    private final StreetlampRepository streetlampRepository;

    private final StreetlampMapper streetlampMapper;

    public StreetlampService(StreetlampRepository streetlampRepository, StreetlampMapper streetlampMapper) {
        this.streetlampRepository = streetlampRepository;
        this.streetlampMapper = streetlampMapper;
    }

    /**
     * Save a streetlamp.
     *
     * @param streetlampDTO the entity to save.
     * @return the persisted entity.
     */
    public StreetlampDTO save(StreetlampDTO streetlampDTO) {
        log.debug("Request to save Streetlamp : {}", streetlampDTO);
        Streetlamp streetlamp = streetlampMapper.toEntity(streetlampDTO);
        streetlamp = streetlampRepository.save(streetlamp);
        return streetlampMapper.toDto(streetlamp);
    }

    /**
     * Get all the streetlamps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<StreetlampDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Streetlamps");
        return streetlampRepository.findAll(pageable)
            .map(streetlampMapper::toDto);
    }


    /**
     * Get one streetlamp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StreetlampDTO> findOne(Long id) {
        log.debug("Request to get Streetlamp : {}", id);
        return streetlampRepository.findById(id)
            .map(streetlampMapper::toDto);
    }

    /**
     * Delete the streetlamp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Streetlamp : {}", id);
        streetlampRepository.deleteById(id);
    }
}
