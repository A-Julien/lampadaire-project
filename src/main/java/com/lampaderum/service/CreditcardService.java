package com.lampaderum.service;

import com.lampaderum.domain.Creditcard;
import com.lampaderum.repository.CreditcardRepository;
import com.lampaderum.service.dto.CreditcardDTO;
import com.lampaderum.service.mapper.CreditcardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Creditcard}.
 */
@Service
@Transactional
public class CreditcardService {

    private final Logger log = LoggerFactory.getLogger(CreditcardService.class);

    private final CreditcardRepository creditcardRepository;

    private final CreditcardMapper creditcardMapper;

    public CreditcardService(CreditcardRepository creditcardRepository, CreditcardMapper creditcardMapper) {
        this.creditcardRepository = creditcardRepository;
        this.creditcardMapper = creditcardMapper;
    }

    /**
     * Save a creditcard.
     *
     * @param creditcardDTO the entity to save.
     * @return the persisted entity.
     */
    public CreditcardDTO save(CreditcardDTO creditcardDTO) {
        log.debug("Request to save Creditcard : {}", creditcardDTO);
        Creditcard creditcard = creditcardMapper.toEntity(creditcardDTO);
        creditcard = creditcardRepository.save(creditcard);
        return creditcardMapper.toDto(creditcard);
    }

    /**
     * Get all the creditcards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CreditcardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Creditcards");
        return creditcardRepository.findAll(pageable)
            .map(creditcardMapper::toDto);
    }


    /**
     * Get one creditcard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreditcardDTO> findOne(Long id) {
        log.debug("Request to get Creditcard : {}", id);
        return creditcardRepository.findById(id)
            .map(creditcardMapper::toDto);
    }

    /**
     * Delete the creditcard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Creditcard : {}", id);
        creditcardRepository.deleteById(id);
    }
}
