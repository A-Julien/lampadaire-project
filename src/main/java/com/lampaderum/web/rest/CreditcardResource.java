package com.lampaderum.web.rest;

import com.lampaderum.service.CreditcardService;
import com.lampaderum.web.rest.errors.BadRequestAlertException;
import com.lampaderum.service.dto.CreditcardDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lampaderum.domain.Creditcard}.
 */
@RestController
@RequestMapping("/api")
public class CreditcardResource {

    private final Logger log = LoggerFactory.getLogger(CreditcardResource.class);

    private static final String ENTITY_NAME = "creditcard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreditcardService creditcardService;

    public CreditcardResource(CreditcardService creditcardService) {
        this.creditcardService = creditcardService;
    }

    /**
     * {@code POST  /creditcards} : Create a new creditcard.
     *
     * @param creditcardDTO the creditcardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creditcardDTO, or with status {@code 400 (Bad Request)} if the creditcard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/creditcards")
    public ResponseEntity<CreditcardDTO> createCreditcard(@RequestBody CreditcardDTO creditcardDTO) throws URISyntaxException {
        log.debug("REST request to save Creditcard : {}", creditcardDTO);
        if (creditcardDTO.getId() != null) {
            throw new BadRequestAlertException("A new creditcard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreditcardDTO result = creditcardService.save(creditcardDTO);
        return ResponseEntity.created(new URI("/api/creditcards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /creditcards} : Updates an existing creditcard.
     *
     * @param creditcardDTO the creditcardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditcardDTO,
     * or with status {@code 400 (Bad Request)} if the creditcardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the creditcardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/creditcards")
    public ResponseEntity<CreditcardDTO> updateCreditcard(@RequestBody CreditcardDTO creditcardDTO) throws URISyntaxException {
        log.debug("REST request to update Creditcard : {}", creditcardDTO);
        if (creditcardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CreditcardDTO result = creditcardService.save(creditcardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, creditcardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /creditcards} : get all the creditcards.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creditcards in body.
     */
    @GetMapping("/creditcards")
    public ResponseEntity<List<CreditcardDTO>> getAllCreditcards(Pageable pageable) {
        log.debug("REST request to get a page of Creditcards");
        Page<CreditcardDTO> page = creditcardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /creditcards/:id} : get the "id" creditcard.
     *
     * @param id the id of the creditcardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creditcardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/creditcards/{id}")
    public ResponseEntity<CreditcardDTO> getCreditcard(@PathVariable Long id) {
        log.debug("REST request to get Creditcard : {}", id);
        Optional<CreditcardDTO> creditcardDTO = creditcardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(creditcardDTO);
    }

    /**
     * {@code GET  /creditcards/:id} : get the "id" of the ApplicationUser of the creditcard.
     *
     * @param id the id of the the ApplicationUser of the creditcardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creditcardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/creditcards/ApplicationUserId/{id}")
    public ResponseEntity<List<CreditcardDTO>> getCreditcardByApplicationUserId(@PathVariable Long id) {
        log.debug("REST request to get Creditcard : {}", id);
        List<CreditcardDTO> creditcardDTO = (List<CreditcardDTO>) creditcardService.findOneByApplicationUserId(id);
        return ResponseEntity.ok().body(creditcardDTO);
    }

    /**
     * {@code DELETE  /creditcards/:id} : delete the "id" creditcard.
     *
     * @param id the id of the creditcardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/creditcards/{id}")
    public ResponseEntity<Void> deleteCreditcard(@PathVariable Long id) {
        log.debug("REST request to delete Creditcard : {}", id);
        creditcardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
