package com.lampaderum.web.rest;

import com.lampaderum.LampaderumApp;
import com.lampaderum.domain.Cartpersi;
import com.lampaderum.repository.CartpersiRepository;
import com.lampaderum.service.CartpersiService;
import com.lampaderum.service.dto.CartpersiDTO;
import com.lampaderum.service.mapper.CartpersiMapper;

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
 * Integration tests for the {@link CartpersiResource} REST controller.
 */
@SpringBootTest(classes = LampaderumApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CartpersiResourceIT {

    @Autowired
    private CartpersiRepository cartpersiRepository;

    @Autowired
    private CartpersiMapper cartpersiMapper;

    @Autowired
    private CartpersiService cartpersiService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCartpersiMockMvc;

    private Cartpersi cartpersi;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cartpersi createEntity(EntityManager em) {
        Cartpersi cartpersi = new Cartpersi();
        return cartpersi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cartpersi createUpdatedEntity(EntityManager em) {
        Cartpersi cartpersi = new Cartpersi();
        return cartpersi;
    }

    @BeforeEach
    public void initTest() {
        cartpersi = createEntity(em);
    }

    @Test
    @Transactional
    public void createCartpersi() throws Exception {
        int databaseSizeBeforeCreate = cartpersiRepository.findAll().size();
        // Create the Cartpersi
        CartpersiDTO cartpersiDTO = cartpersiMapper.toDto(cartpersi);
        restCartpersiMockMvc.perform(post("/api/cartpersis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartpersiDTO)))
            .andExpect(status().isCreated());

        // Validate the Cartpersi in the database
        List<Cartpersi> cartpersiList = cartpersiRepository.findAll();
        assertThat(cartpersiList).hasSize(databaseSizeBeforeCreate + 1);
        Cartpersi testCartpersi = cartpersiList.get(cartpersiList.size() - 1);
    }

    @Test
    @Transactional
    public void createCartpersiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cartpersiRepository.findAll().size();

        // Create the Cartpersi with an existing ID
        cartpersi.setId(1L);
        CartpersiDTO cartpersiDTO = cartpersiMapper.toDto(cartpersi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCartpersiMockMvc.perform(post("/api/cartpersis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartpersiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cartpersi in the database
        List<Cartpersi> cartpersiList = cartpersiRepository.findAll();
        assertThat(cartpersiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCartpersis() throws Exception {
        // Initialize the database
        cartpersiRepository.saveAndFlush(cartpersi);

        // Get all the cartpersiList
        restCartpersiMockMvc.perform(get("/api/cartpersis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cartpersi.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getCartpersi() throws Exception {
        // Initialize the database
        cartpersiRepository.saveAndFlush(cartpersi);

        // Get the cartpersi
        restCartpersiMockMvc.perform(get("/api/cartpersis/{id}", cartpersi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cartpersi.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCartpersi() throws Exception {
        // Get the cartpersi
        restCartpersiMockMvc.perform(get("/api/cartpersis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCartpersi() throws Exception {
        // Initialize the database
        cartpersiRepository.saveAndFlush(cartpersi);

        int databaseSizeBeforeUpdate = cartpersiRepository.findAll().size();

        // Update the cartpersi
        Cartpersi updatedCartpersi = cartpersiRepository.findById(cartpersi.getId()).get();
        // Disconnect from session so that the updates on updatedCartpersi are not directly saved in db
        em.detach(updatedCartpersi);
        CartpersiDTO cartpersiDTO = cartpersiMapper.toDto(updatedCartpersi);

        restCartpersiMockMvc.perform(put("/api/cartpersis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartpersiDTO)))
            .andExpect(status().isOk());

        // Validate the Cartpersi in the database
        List<Cartpersi> cartpersiList = cartpersiRepository.findAll();
        assertThat(cartpersiList).hasSize(databaseSizeBeforeUpdate);
        Cartpersi testCartpersi = cartpersiList.get(cartpersiList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingCartpersi() throws Exception {
        int databaseSizeBeforeUpdate = cartpersiRepository.findAll().size();

        // Create the Cartpersi
        CartpersiDTO cartpersiDTO = cartpersiMapper.toDto(cartpersi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCartpersiMockMvc.perform(put("/api/cartpersis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartpersiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cartpersi in the database
        List<Cartpersi> cartpersiList = cartpersiRepository.findAll();
        assertThat(cartpersiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCartpersi() throws Exception {
        // Initialize the database
        cartpersiRepository.saveAndFlush(cartpersi);

        int databaseSizeBeforeDelete = cartpersiRepository.findAll().size();

        // Delete the cartpersi
        restCartpersiMockMvc.perform(delete("/api/cartpersis/{id}", cartpersi.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cartpersi> cartpersiList = cartpersiRepository.findAll();
        assertThat(cartpersiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
