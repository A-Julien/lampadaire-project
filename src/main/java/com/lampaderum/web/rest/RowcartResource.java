package com.lampaderum.web.rest;

import com.lampaderum.service.RowcartService;
import com.lampaderum.web.rest.errors.BadRequestAlertException;
import com.lampaderum.service.dto.RowcartDTO;

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
 * REST controller for managing {@link com.lampaderum.domain.Rowcart}.
 */
@RestController
@RequestMapping("/api")
public class RowcartResource {

    private final Logger log = LoggerFactory.getLogger(RowcartResource.class);

    private static final String ENTITY_NAME = "rowcart";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RowcartService rowcartService;

    public RowcartResource(RowcartService rowcartService) {
        this.rowcartService = rowcartService;
    }

    /**
     * {@code POST  /rowcarts} : Create a new rowcart.
     *
     * @param rowcartDTO the rowcartDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rowcartDTO, or with status {@code 400 (Bad Request)} if the rowcart has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rowcarts")
    public ResponseEntity<RowcartDTO> createRowcart(@RequestBody RowcartDTO rowcartDTO) throws URISyntaxException {
        log.debug("REST request to save Rowcart : {}", rowcartDTO);
        if (rowcartDTO.getId() != null) {
            throw new BadRequestAlertException("A new rowcart cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RowcartDTO result = rowcartService.save(rowcartDTO);
        return ResponseEntity.created(new URI("/api/rowcarts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rowcarts} : Updates an existing rowcart.
     *
     * @param rowcartDTO the rowcartDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rowcartDTO,
     * or with status {@code 400 (Bad Request)} if the rowcartDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rowcartDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rowcarts")
    public ResponseEntity<RowcartDTO> updateRowcart(@RequestBody RowcartDTO rowcartDTO) throws URISyntaxException {
        log.debug("REST request to update Rowcart : {}", rowcartDTO);
        if (rowcartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RowcartDTO result = rowcartService.save(rowcartDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rowcartDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rowcarts} : get all the rowcarts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rowcarts in body.
     */
    @GetMapping("/rowcarts")
    public ResponseEntity<List<RowcartDTO>> getAllRowcarts(Pageable pageable) {
        log.debug("REST request to get a page of Rowcarts");
        Page<RowcartDTO> page = rowcartService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rowcarts/:id} : get the "id" rowcart.
     *
     * @param id the id of the rowcartDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rowcartDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rowcarts/{id}")
    public ResponseEntity<RowcartDTO> getRowcart(@PathVariable Long id) {
        log.debug("REST request to get Rowcart : {}", id);
        Optional<RowcartDTO> rowcartDTO = rowcartService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rowcartDTO);
    }

    /**
     * {@code DELETE  /rowcarts/:id} : delete the "id" rowcart.
     *
     * @param id the id of the rowcartDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rowcarts/{id}")
    public ResponseEntity<Void> deleteRowcart(@PathVariable Long id) {
        log.debug("REST request to delete Rowcart : {}", id);
        rowcartService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
