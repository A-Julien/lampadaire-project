package com.lampaderum.service;

import com.lampaderum.domain.Roworder;
import com.lampaderum.repository.RoworderRepository;
import com.lampaderum.service.dto.RoworderDTO;
import com.lampaderum.service.dto.SOrderDTO;
import com.lampaderum.service.mapper.RoworderMapper;
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
     * Get roworder by SOrder id.
     *
     * @param id the id of ApplicationUser of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public List<RoworderDTO> findBySOrderId(Long id) {

        Object[] plop=roworderRepository.findAll().stream()
            .map(roworderMapper::toDto).toArray();
        ArrayList<RoworderDTO> sorders= new ArrayList<RoworderDTO>();
        log.debug("findBySOrderId id : {}",id);
        log.debug("findBySOrderId plop.length : {}",plop.length);
        for (int i=0;i< plop.length;i++){
            log.debug("findBySOrderId plop[i].id : {}",plop.length);
            log.debug("findBySOrderId plop[i].orderid : {}",((RoworderDTO)plop[i]).getSorderId());
            log.debug("findBySOrderId plop[i].tostring : {}",((RoworderDTO)plop[i]).toString());
            log.debug("findBySOrderId if : {}",id.equals(((RoworderDTO)plop[i]).getSorderId()));
            if(id.equals(((RoworderDTO)plop[i]).getSorderId())){
                log.debug("plop : {}",(RoworderDTO) plop[i]);
                sorders.add((RoworderDTO) plop[i]);
            }
        }
        return sorders;
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
