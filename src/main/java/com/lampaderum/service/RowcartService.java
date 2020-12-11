package com.lampaderum.service;

import com.lampaderum.domain.Rowcart;
import com.lampaderum.repository.RowcartRepository;
import com.lampaderum.service.dto.RowcartDTO;
import com.lampaderum.service.mapper.RowcartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Rowcart}.
 */
@Service
@Transactional
public class RowcartService {

    private final Logger log = LoggerFactory.getLogger(RowcartService.class);

    private final RowcartRepository rowcartRepository;

    private final RowcartMapper rowcartMapper;

    public RowcartService(RowcartRepository rowcartRepository, RowcartMapper rowcartMapper) {
        this.rowcartRepository = rowcartRepository;
        this.rowcartMapper = rowcartMapper;
    }

    /**
     * Save a rowcart.
     *
     * @param rowcartDTO the entity to save.
     * @return the persisted entity.
     */
    public RowcartDTO save(RowcartDTO rowcartDTO) {
        log.debug("Request to save Rowcart : {}", rowcartDTO);
        Rowcart rowcart = rowcartMapper.toEntity(rowcartDTO);
        rowcart = rowcartRepository.save(rowcart);
        return rowcartMapper.toDto(rowcart);
    }

    /**
     * Get all the rowcarts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RowcartDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Rowcarts");
        return rowcartRepository.findAll(pageable)
            .map(rowcartMapper::toDto);
    }


    /**
     * Get one rowcart by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RowcartDTO> findOne(Long id) {
        log.debug("Request to get Rowcart : {}", id);
        return rowcartRepository.findById(id)
            .map(rowcartMapper::toDto);
    }

    /**
     * Delete the rowcart by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Rowcart : {}", id);
        rowcartRepository.deleteById(id);
    }
}
