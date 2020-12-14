package com.lampaderum.web.rest;

import com.lampaderum.LampaderumApp;
import com.lampaderum.domain.Rowcart;
import com.lampaderum.repository.RowcartRepository;
import com.lampaderum.service.RowcartService;
import com.lampaderum.service.dto.RowcartDTO;
import com.lampaderum.service.mapper.RowcartMapper;

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
 * Integration tests for the {@link RowcartResource} REST controller.
 */
@SpringBootTest(classes = LampaderumApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RowcartResourceIT {

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    @Autowired
    private RowcartRepository rowcartRepository;

    @Autowired
    private RowcartMapper rowcartMapper;

    @Autowired
    private RowcartService rowcartService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRowcartMockMvc;

    private Rowcart rowcart;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rowcart createEntity(EntityManager em) {
        Rowcart rowcart = new Rowcart()
            .quantity(DEFAULT_QUANTITY);
        return rowcart;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rowcart createUpdatedEntity(EntityManager em) {
        Rowcart rowcart = new Rowcart()
            .quantity(UPDATED_QUANTITY);
        return rowcart;
    }

    @BeforeEach
    public void initTest() {
        rowcart = createEntity(em);
    }

    @Test
    @Transactional
    public void createRowcart() throws Exception {
        int databaseSizeBeforeCreate = rowcartRepository.findAll().size();
        // Create the Rowcart
        RowcartDTO rowcartDTO = rowcartMapper.toDto(rowcart);
        restRowcartMockMvc.perform(post("/api/rowcarts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rowcartDTO)))
            .andExpect(status().isCreated());

        // Validate the Rowcart in the database
        List<Rowcart> rowcartList = rowcartRepository.findAll();
        assertThat(rowcartList).hasSize(databaseSizeBeforeCreate + 1);
        Rowcart testRowcart = rowcartList.get(rowcartList.size() - 1);
        assertThat(testRowcart.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    public void createRowcartWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rowcartRepository.findAll().size();

        // Create the Rowcart with an existing ID
        rowcart.setId(1L);
        RowcartDTO rowcartDTO = rowcartMapper.toDto(rowcart);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRowcartMockMvc.perform(post("/api/rowcarts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rowcartDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rowcart in the database
        List<Rowcart> rowcartList = rowcartRepository.findAll();
        assertThat(rowcartList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRowcarts() throws Exception {
        // Initialize the database
        rowcartRepository.saveAndFlush(rowcart);

        // Get all the rowcartList
        restRowcartMockMvc.perform(get("/api/rowcarts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rowcart.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)));
    }
    
    @Test
    @Transactional
    public void getRowcart() throws Exception {
        // Initialize the database
        rowcartRepository.saveAndFlush(rowcart);

        // Get the rowcart
        restRowcartMockMvc.perform(get("/api/rowcarts/{id}", rowcart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rowcart.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY));
    }
    @Test
    @Transactional
    public void getNonExistingRowcart() throws Exception {
        // Get the rowcart
        restRowcartMockMvc.perform(get("/api/rowcarts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRowcart() throws Exception {
        // Initialize the database
        rowcartRepository.saveAndFlush(rowcart);

        int databaseSizeBeforeUpdate = rowcartRepository.findAll().size();

        // Update the rowcart
        Rowcart updatedRowcart = rowcartRepository.findById(rowcart.getId()).get();
        // Disconnect from session so that the updates on updatedRowcart are not directly saved in db
        em.detach(updatedRowcart);
        updatedRowcart
            .quantity(UPDATED_QUANTITY);
        RowcartDTO rowcartDTO = rowcartMapper.toDto(updatedRowcart);

        restRowcartMockMvc.perform(put("/api/rowcarts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rowcartDTO)))
            .andExpect(status().isOk());

        // Validate the Rowcart in the database
        List<Rowcart> rowcartList = rowcartRepository.findAll();
        assertThat(rowcartList).hasSize(databaseSizeBeforeUpdate);
        Rowcart testRowcart = rowcartList.get(rowcartList.size() - 1);
        assertThat(testRowcart.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingRowcart() throws Exception {
        int databaseSizeBeforeUpdate = rowcartRepository.findAll().size();

        // Create the Rowcart
        RowcartDTO rowcartDTO = rowcartMapper.toDto(rowcart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRowcartMockMvc.perform(put("/api/rowcarts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rowcartDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rowcart in the database
        List<Rowcart> rowcartList = rowcartRepository.findAll();
        assertThat(rowcartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRowcart() throws Exception {
        // Initialize the database
        rowcartRepository.saveAndFlush(rowcart);

        int databaseSizeBeforeDelete = rowcartRepository.findAll().size();

        // Delete the rowcart
        restRowcartMockMvc.perform(delete("/api/rowcarts/{id}", rowcart.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Rowcart> rowcartList = rowcartRepository.findAll();
        assertThat(rowcartList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
