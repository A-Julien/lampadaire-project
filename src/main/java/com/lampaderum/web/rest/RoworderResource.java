package com.lampaderum.web.rest;

import com.lampaderum.service.RoworderService;
import com.lampaderum.web.rest.errors.BadRequestAlertException;
import com.lampaderum.service.dto.RoworderDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lampaderum.domain.Roworder}.
 */
@RestController
@RequestMapping("/api")
public class RoworderResource {

    private final Logger log = LoggerFactory.getLogger(RoworderResource.class);

    private static final String ENTITY_NAME = "roworder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RoworderService roworderService;

    public RoworderResource(RoworderService roworderService) {
        this.roworderService = roworderService;
    }

    /**
     * {@code POST  /roworders} : Create a new roworder.
     *
     * @param roworderDTO the roworderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roworderDTO, or with status {@code 400 (Bad Request)} if the roworder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/roworders")
    public ResponseEntity<RoworderDTO> createRoworder(@Valid @RequestBody RoworderDTO roworderDTO) throws URISyntaxException {
        log.debug("REST request to save Roworder : {}", roworderDTO);
        if (roworderDTO.getId() != null) {
            throw new BadRequestAlertException("A new roworder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoworderDTO result = roworderService.save(roworderDTO);
        return ResponseEntity.created(new URI("/api/roworders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /roworders} : Updates an existing roworder.
     *
     * @param roworderDTO the roworderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roworderDTO,
     * or with status {@code 400 (Bad Request)} if the roworderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roworderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/roworders")
    public ResponseEntity<RoworderDTO> updateRoworder(@Valid @RequestBody RoworderDTO roworderDTO) throws URISyntaxException {
        log.debug("REST request to update Roworder : {}", roworderDTO);
        if (roworderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RoworderDTO result = roworderService.save(roworderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roworderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /roworders} : get all the roworders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roworders in body.
     */
    @GetMapping("/roworders")
    public ResponseEntity<List<RoworderDTO>> getAllRoworders(Pageable pageable) {
        log.debug("REST request to get a page of Roworders");
        Page<RoworderDTO> page = roworderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /roworders/:id} : get the "id" roworder.
     *
     * @param id the id of the roworderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roworderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/roworders/{id}")
    public ResponseEntity<RoworderDTO> getRoworder(@PathVariable Long id) {
        log.debug("REST request to get Roworder : {}", id);
        Optional<RoworderDTO> roworderDTO = roworderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(roworderDTO);
    }

    /**
     * {@code DELETE  /roworders/:id} : delete the "id" roworder.
     *
     * @param id the id of the roworderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/roworders/{id}")
    public ResponseEntity<Void> deleteRoworder(@PathVariable Long id) {
        log.debug("REST request to delete Roworder : {}", id);
        roworderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
