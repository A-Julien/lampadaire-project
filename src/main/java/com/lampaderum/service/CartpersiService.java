package com.lampaderum.service;

import com.lampaderum.domain.Cartpersi;
import com.lampaderum.repository.CartpersiRepository;
import com.lampaderum.service.dto.CartpersiDTO;
import com.lampaderum.service.mapper.CartpersiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Cartpersi}.
 */
@Service
@Transactional
public class CartpersiService {

    private final Logger log = LoggerFactory.getLogger(CartpersiService.class);

    private final CartpersiRepository cartpersiRepository;

    private final CartpersiMapper cartpersiMapper;

    public CartpersiService(CartpersiRepository cartpersiRepository, CartpersiMapper cartpersiMapper) {
        this.cartpersiRepository = cartpersiRepository;
        this.cartpersiMapper = cartpersiMapper;
    }

    /**
     * Save a cartpersi.
     *
     * @param cartpersiDTO the entity to save.
     * @return the persisted entity.
     */
    public CartpersiDTO save(CartpersiDTO cartpersiDTO) {
        log.debug("Request to save Cartpersi : {}", cartpersiDTO);
        Cartpersi cartpersi = cartpersiMapper.toEntity(cartpersiDTO);
        cartpersi = cartpersiRepository.save(cartpersi);
        return cartpersiMapper.toDto(cartpersi);
    }

    /**
     * Get all the cartpersis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CartpersiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cartpersis");
        return cartpersiRepository.findAll(pageable)
            .map(cartpersiMapper::toDto);
    }


    /**
     * Get one cartpersi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CartpersiDTO> findOne(Long id) {
        log.debug("Request to get Cartpersi : {}", id);
        return cartpersiRepository.findById(id)
            .map(cartpersiMapper::toDto);
    }

    /**
     * Delete the cartpersi by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cartpersi : {}", id);
        cartpersiRepository.deleteById(id);
    }
}
