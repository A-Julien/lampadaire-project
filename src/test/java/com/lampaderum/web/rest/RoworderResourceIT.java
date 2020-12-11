package com.lampaderum.web.rest;

import com.lampaderum.LampaderumApp;
import com.lampaderum.domain.Roworder;
import com.lampaderum.repository.RoworderRepository;
import com.lampaderum.service.RoworderService;
import com.lampaderum.service.dto.RoworderDTO;
import com.lampaderum.service.mapper.RoworderMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RoworderResource} REST controller.
 */
@SpringBootTest(classes = LampaderumApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RoworderResourceIT {

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Integer DEFAULT_QUANTITE = 0;
    private static final Integer UPDATED_QUANTITE = 1;

    @Autowired
    private RoworderRepository roworderRepository;

    @Autowired
    private RoworderMapper roworderMapper;

    @Autowired
    private RoworderService roworderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRoworderMockMvc;

    private Roworder roworder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Roworder createEntity(EntityManager em) {
        Roworder roworder = new Roworder()
            .price(DEFAULT_PRICE)
            .quantite(DEFAULT_QUANTITE);
        return roworder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Roworder createUpdatedEntity(EntityManager em) {
        Roworder roworder = new Roworder()
            .price(UPDATED_PRICE)
            .quantite(UPDATED_QUANTITE);
        return roworder;
    }

    @BeforeEach
    public void initTest() {
        roworder = createEntity(em);
    }

    @Test
    @Transactional
    public void createRoworder() throws Exception {
        int databaseSizeBeforeCreate = roworderRepository.findAll().size();
        // Create the Roworder
        RoworderDTO roworderDTO = roworderMapper.toDto(roworder);
        restRoworderMockMvc.perform(post("/api/roworders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(roworderDTO)))
            .andExpect(status().isCreated());

        // Validate the Roworder in the database
        List<Roworder> roworderList = roworderRepository.findAll();
        assertThat(roworderList).hasSize(databaseSizeBeforeCreate + 1);
        Roworder testRoworder = roworderList.get(roworderList.size() - 1);
        assertThat(testRoworder.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testRoworder.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
    }

    @Test
    @Transactional
    public void createRoworderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = roworderRepository.findAll().size();

        // Create the Roworder with an existing ID
        roworder.setId(1L);
        RoworderDTO roworderDTO = roworderMapper.toDto(roworder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRoworderMockMvc.perform(post("/api/roworders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(roworderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Roworder in the database
        List<Roworder> roworderList = roworderRepository.findAll();
        assertThat(roworderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRoworders() throws Exception {
        // Initialize the database
        roworderRepository.saveAndFlush(roworder);

        // Get all the roworderList
        restRoworderMockMvc.perform(get("/api/roworders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(roworder.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE)));
    }
    
    @Test
    @Transactional
    public void getRoworder() throws Exception {
        // Initialize the database
        roworderRepository.saveAndFlush(roworder);

        // Get the roworder
        restRoworderMockMvc.perform(get("/api/roworders/{id}", roworder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(roworder.getId().intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE));
    }
    @Test
    @Transactional
    public void getNonExistingRoworder() throws Exception {
        // Get the roworder
        restRoworderMockMvc.perform(get("/api/roworders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRoworder() throws Exception {
        // Initialize the database
        roworderRepository.saveAndFlush(roworder);

        int databaseSizeBeforeUpdate = roworderRepository.findAll().size();

        // Update the roworder
        Roworder updatedRoworder = roworderRepository.findById(roworder.getId()).get();
        // Disconnect from session so that the updates on updatedRoworder are not directly saved in db
        em.detach(updatedRoworder);
        updatedRoworder
            .price(UPDATED_PRICE)
            .quantite(UPDATED_QUANTITE);
        RoworderDTO roworderDTO = roworderMapper.toDto(updatedRoworder);

        restRoworderMockMvc.perform(put("/api/roworders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(roworderDTO)))
            .andExpect(status().isOk());

        // Validate the Roworder in the database
        List<Roworder> roworderList = roworderRepository.findAll();
        assertThat(roworderList).hasSize(databaseSizeBeforeUpdate);
        Roworder testRoworder = roworderList.get(roworderList.size() - 1);
        assertThat(testRoworder.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testRoworder.getQuantite()).isEqualTo(UPDATED_QUANTITE);
    }

    @Test
    @Transactional
    public void updateNonExistingRoworder() throws Exception {
        int databaseSizeBeforeUpdate = roworderRepository.findAll().size();

        // Create the Roworder
        RoworderDTO roworderDTO = roworderMapper.toDto(roworder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoworderMockMvc.perform(put("/api/roworders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(roworderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Roworder in the database
        List<Roworder> roworderList = roworderRepository.findAll();
        assertThat(roworderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRoworder() throws Exception {
        // Initialize the database
        roworderRepository.saveAndFlush(roworder);

        int databaseSizeBeforeDelete = roworderRepository.findAll().size();

        // Delete the roworder
        restRoworderMockMvc.perform(delete("/api/roworders/{id}", roworder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Roworder> roworderList = roworderRepository.findAll();
        assertThat(roworderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
