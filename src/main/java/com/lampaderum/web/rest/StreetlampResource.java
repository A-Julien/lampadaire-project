package com.lampaderum.web.rest;

import com.lampaderum.service.StreetlampService;
import com.lampaderum.web.rest.errors.BadRequestAlertException;
import com.lampaderum.service.dto.StreetlampDTO;

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
 * REST controller for managing {@link com.lampaderum.domain.Streetlamp}.
 */
@RestController
@RequestMapping("/api")
public class StreetlampResource {

    private final Logger log = LoggerFactory.getLogger(StreetlampResource.class);

    private static final String ENTITY_NAME = "streetlamp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StreetlampService streetlampService;

    public StreetlampResource(StreetlampService streetlampService) {
        this.streetlampService = streetlampService;
    }

    /**
     * {@code POST  /streetlamps} : Create a new streetlamp.
     *
     * @param streetlampDTO the streetlampDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new streetlampDTO, or with status {@code 400 (Bad Request)} if the streetlamp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/streetlamps")
    public ResponseEntity<StreetlampDTO> createStreetlamp(@RequestBody StreetlampDTO streetlampDTO) throws URISyntaxException {
        log.debug("REST request to save Streetlamp : {}", streetlampDTO);
        if (streetlampDTO.getId() != null) {
            throw new BadRequestAlertException("A new streetlamp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StreetlampDTO result = streetlampService.save(streetlampDTO);
        return ResponseEntity.created(new URI("/api/streetlamps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /streetlamps} : Updates an existing streetlamp.
     *
     * @param streetlampDTO the streetlampDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated streetlampDTO,
     * or with status {@code 400 (Bad Request)} if the streetlampDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the streetlampDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/streetlamps")
    public ResponseEntity<StreetlampDTO> updateStreetlamp(@RequestBody StreetlampDTO streetlampDTO) throws URISyntaxException {
        log.debug("REST request to update Streetlamp : {}", streetlampDTO);
        if (streetlampDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StreetlampDTO result = streetlampService.save(streetlampDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, streetlampDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /streetlamps} : get all the streetlamps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of streetlamps in body.
     */
    @GetMapping("/streetlamps")
    public ResponseEntity<List<StreetlampDTO>> getAllStreetlamps(Pageable pageable) {
        log.debug("REST request to get a page of Streetlamps");
        Page<StreetlampDTO> page = streetlampService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /streetlamps/:id} : get the "id" streetlamp.
     *
     * @param id the id of the streetlampDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the streetlampDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/streetlamps/{id}")
    public ResponseEntity<StreetlampDTO> getStreetlamp(@PathVariable Long id) {
        log.debug("REST request to get Streetlamp : {}", id);
        Optional<StreetlampDTO> streetlampDTO = streetlampService.findOne(id);
        return ResponseUtil.wrapOrNotFound(streetlampDTO);
    }

    /**
     * {@code DELETE  /streetlamps/:id} : delete the "id" streetlamp.
     *
     * @param id the id of the streetlampDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/streetlamps/{id}")
    public ResponseEntity<Void> deleteStreetlamp(@PathVariable Long id) {
        log.debug("REST request to delete Streetlamp : {}", id);
        streetlampService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
