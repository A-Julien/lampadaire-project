package com.lampaderum.web.rest;

import com.lampaderum.service.CartpersiService;
import com.lampaderum.web.rest.errors.BadRequestAlertException;
import com.lampaderum.service.dto.CartpersiDTO;

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
 * REST controller for managing {@link com.lampaderum.domain.Cartpersi}.
 */
@RestController
@RequestMapping("/api")
public class CartpersiResource {

    private final Logger log = LoggerFactory.getLogger(CartpersiResource.class);

    private static final String ENTITY_NAME = "cartpersi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CartpersiService cartpersiService;

    public CartpersiResource(CartpersiService cartpersiService) {
        this.cartpersiService = cartpersiService;
    }

    /**
     * {@code POST  /cartpersis} : Create a new cartpersi.
     *
     * @param cartpersiDTO the cartpersiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cartpersiDTO, or with status {@code 400 (Bad Request)} if the cartpersi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cartpersis")
    public ResponseEntity<CartpersiDTO> createCartpersi(@RequestBody CartpersiDTO cartpersiDTO) throws URISyntaxException {
        log.debug("REST request to save Cartpersi : {}", cartpersiDTO);
        if (cartpersiDTO.getId() != null) {
            throw new BadRequestAlertException("A new cartpersi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CartpersiDTO result = cartpersiService.save(cartpersiDTO);
        return ResponseEntity.created(new URI("/api/cartpersis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cartpersis} : Updates an existing cartpersi.
     *
     * @param cartpersiDTO the cartpersiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cartpersiDTO,
     * or with status {@code 400 (Bad Request)} if the cartpersiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cartpersiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cartpersis")
    public ResponseEntity<CartpersiDTO> updateCartpersi(@RequestBody CartpersiDTO cartpersiDTO) throws URISyntaxException {
        log.debug("REST request to update Cartpersi : {}", cartpersiDTO);
        if (cartpersiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CartpersiDTO result = cartpersiService.save(cartpersiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cartpersiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cartpersis} : get all the cartpersis.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartpersis in body.
     */
    @GetMapping("/cartpersis")
    public ResponseEntity<List<CartpersiDTO>> getAllCartpersis(Pageable pageable) {
        log.debug("REST request to get a page of Cartpersis");
        Page<CartpersiDTO> page = cartpersiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cartpersis/:id} : get the "id" cartpersi.
     *
     * @param id the id of the cartpersiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cartpersiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cartpersis/{id}")
    public ResponseEntity<CartpersiDTO> getCartpersi(@PathVariable Long id) {
        log.debug("REST request to get Cartpersi : {}", id);
        Optional<CartpersiDTO> cartpersiDTO = cartpersiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cartpersiDTO);
    }

    /**
     * {@code DELETE  /cartpersis/:id} : delete the "id" cartpersi.
     *
     * @param id the id of the cartpersiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cartpersis/{id}")
    public ResponseEntity<Void> deleteCartpersi(@PathVariable Long id) {
        log.debug("REST request to delete Cartpersi : {}", id);
        cartpersiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
