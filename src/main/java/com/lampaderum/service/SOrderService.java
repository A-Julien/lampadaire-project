package com.lampaderum.service;

import com.lampaderum.domain.SOrder;
import com.lampaderum.repository.SOrderRepository;
import com.lampaderum.service.dto.CreditcardDTO;
import com.lampaderum.service.dto.SOrderDTO;
import com.lampaderum.service.mapper.SOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link SOrder}.
 */
@Service
@Transactional
public class SOrderService {

    private final Logger log = LoggerFactory.getLogger(SOrderService.class);

    private final SOrderRepository sOrderRepository;

    private final SOrderMapper sOrderMapper;

    public SOrderService(SOrderRepository sOrderRepository, SOrderMapper sOrderMapper) {
        this.sOrderRepository = sOrderRepository;
        this.sOrderMapper = sOrderMapper;
    }

    /**
     * Save a sOrder.
     *
     * @param sOrderDTO the entity to save.
     * @return the persisted entity.
     */
    public SOrderDTO save(SOrderDTO sOrderDTO) {
        log.debug("Request to save SOrder : {}", sOrderDTO);
        SOrder sOrder = sOrderMapper.toEntity(sOrderDTO);
        sOrder = sOrderRepository.save(sOrder);
        return sOrderMapper.toDto(sOrder);
    }

    /**
     * Get all the sOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SOrders");
        return sOrderRepository.findAll(pageable)
            .map(sOrderMapper::toDto);
    }


    /**
     * Get one sOrder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SOrderDTO> findOne(Long id) {
        log.debug("Request to get SOrder : {}", id);
        return sOrderRepository.findById(id)
            .map(sOrderMapper::toDto);
    }

    /**
     * Get one sOrder by ApplicationUser id.
     *
     * @param id the id of ApplicationUser of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public List<SOrderDTO> findByApplicationUserId(Long id) {
        log.debug("Request to get Creditcard : {}", id);

        Object[] plop=sOrderRepository.findAll().stream()
            .map(sOrderMapper::toDto).toArray();
        ArrayList<SOrderDTO> sorders= new ArrayList<SOrderDTO>();

        for (int i=0;i< plop.length;i++){
            if(id==((SOrderDTO)plop[i]).getApplicationUserId()){

                sorders.add((SOrderDTO) plop[i]);
            }
        }
        return sorders;
    }

    /**
     * Delete the sOrder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SOrder : {}", id);
        sOrderRepository.deleteById(id);
    }
}
