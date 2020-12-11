package com.lampaderum.web.rest;

import com.lampaderum.service.SOrderService;
import com.lampaderum.web.rest.errors.BadRequestAlertException;
import com.lampaderum.service.dto.SOrderDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lampaderum.domain.SOrder}.
 */
@RestController
@RequestMapping("/api")
public class SOrderResource {

    private final Logger log = LoggerFactory.getLogger(SOrderResource.class);

    private static final String ENTITY_NAME = "sOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SOrderService sOrderService;

    public SOrderResource(SOrderService sOrderService) {
        this.sOrderService = sOrderService;
    }

    /**
     * {@code POST  /s-orders} : Create a new sOrder.
     *
     * @param sOrderDTO the sOrderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sOrderDTO, or with status {@code 400 (Bad Request)} if the sOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/s-orders")
    public ResponseEntity<SOrderDTO> createSOrder(@RequestBody SOrderDTO sOrderDTO) throws URISyntaxException {
        log.debug("REST request to save SOrder : {}", sOrderDTO);
        if (sOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new sOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SOrderDTO result = sOrderService.save(sOrderDTO);
        return ResponseEntity.created(new URI("/api/s-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /s-orders} : Updates an existing sOrder.
     *
     * @param sOrderDTO the sOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sOrderDTO,
     * or with status {@code 400 (Bad Request)} if the sOrderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/s-orders")
    public ResponseEntity<SOrderDTO> updateSOrder(@RequestBody SOrderDTO sOrderDTO) throws URISyntaxException {
        log.debug("REST request to update SOrder : {}", sOrderDTO);
        if (sOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SOrderDTO result = sOrderService.save(sOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /s-orders} : get all the sOrders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sOrders in body.
     */
    @GetMapping("/s-orders")
    public ResponseEntity<List<SOrderDTO>> getAllSOrders(Pageable pageable) {
        log.debug("REST request to get a page of SOrders");
        Page<SOrderDTO> page = sOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /s-orders/:id} : get the "id" sOrder.
     *
     * @param id the id of the sOrderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sOrderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/s-orders/{id}")
    public ResponseEntity<SOrderDTO> getSOrder(@PathVariable Long id) {
        log.debug("REST request to get SOrder : {}", id);
        Optional<SOrderDTO> sOrderDTO = sOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sOrderDTO);
    }

    /**
     * {@code DELETE  /s-orders/:id} : delete the "id" sOrder.
     *
     * @param id the id of the sOrderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/s-orders/{id}")
    public ResponseEntity<Void> deleteSOrder(@PathVariable Long id) {
        log.debug("REST request to delete SOrder : {}", id);
        sOrderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
