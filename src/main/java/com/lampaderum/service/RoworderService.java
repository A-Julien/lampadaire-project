package com.lampaderum.service;

import com.lampaderum.domain.Roworder;
import com.lampaderum.repository.RoworderRepository;
import com.lampaderum.service.dto.RoworderDTO;
import com.lampaderum.service.mapper.RoworderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Roworder}.
 */
@Service
@Transactional
public class RoworderService {

    private final Logger log = LoggerFactory.getLogger(RoworderService.class);

    private final RoworderRepository roworderRepository;

    private final RoworderMapper roworderMapper;

    public RoworderService(RoworderRepository roworderRepository, RoworderMapper roworderMapper) {
        this.roworderRepository = roworderRepository;
        this.roworderMapper = roworderMapper;
    }

    /**
     * Save a roworder.
     *
     * @param roworderDTO the entity to save.
     * @return the persisted entity.
     */
    public RoworderDTO save(RoworderDTO roworderDTO) {
        log.debug("Request to save Roworder : {}", roworderDTO);
        Roworder roworder = roworderMapper.toEntity(roworderDTO);
        roworder = roworderRepository.save(roworder);
        return roworderMapper.toDto(roworder);
    }

    /**
     * Get all the roworders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RoworderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Roworders");
        return roworderRepository.findAll(pageable)
            .map(roworderMapper::toDto);
    }


    /**
     * Get one roworder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RoworderDTO> findOne(Long id) {
        log.debug("Request to get Roworder : {}", id);
        return roworderRepository.findById(id)
            .map(roworderMapper::toDto);
    }

    /**
     * Delete the roworder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Roworder : {}", id);
        roworderRepository.deleteById(id);
    }
}
