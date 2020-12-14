package com.lampaderum.web.rest;

import com.lampaderum.LampaderumApp;
import com.lampaderum.domain.SOrder;
import com.lampaderum.repository.SOrderRepository;
import com.lampaderum.service.SOrderService;
import com.lampaderum.service.dto.SOrderDTO;
import com.lampaderum.service.mapper.SOrderMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SOrderResource} REST controller.
 */
@SpringBootTest(classes = LampaderumApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SOrderResourceIT {

    private static final LocalDate DEFAULT_DATECOMMANDE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATECOMMANDE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SOrderRepository sOrderRepository;

    @Autowired
    private SOrderMapper sOrderMapper;

    @Autowired
    private SOrderService sOrderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSOrderMockMvc;

    private SOrder sOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SOrder createEntity(EntityManager em) {
        SOrder sOrder = new SOrder()
            .datecommande(DEFAULT_DATECOMMANDE);
        return sOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SOrder createUpdatedEntity(EntityManager em) {
        SOrder sOrder = new SOrder()
            .datecommande(UPDATED_DATECOMMANDE);
        return sOrder;
    }

    @BeforeEach
    public void initTest() {
        sOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createSOrder() throws Exception {
        int databaseSizeBeforeCreate = sOrderRepository.findAll().size();
        // Create the SOrder
        SOrderDTO sOrderDTO = sOrderMapper.toDto(sOrder);
        restSOrderMockMvc.perform(post("/api/s-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the SOrder in the database
        List<SOrder> sOrderList = sOrderRepository.findAll();
        assertThat(sOrderList).hasSize(databaseSizeBeforeCreate + 1);
        SOrder testSOrder = sOrderList.get(sOrderList.size() - 1);
        assertThat(testSOrder.getDatecommande()).isEqualTo(DEFAULT_DATECOMMANDE);
    }

    @Test
    @Transactional
    public void createSOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sOrderRepository.findAll().size();

        // Create the SOrder with an existing ID
        sOrder.setId(1L);
        SOrderDTO sOrderDTO = sOrderMapper.toDto(sOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSOrderMockMvc.perform(post("/api/s-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SOrder in the database
        List<SOrder> sOrderList = sOrderRepository.findAll();
        assertThat(sOrderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSOrders() throws Exception {
        // Initialize the database
        sOrderRepository.saveAndFlush(sOrder);

        // Get all the sOrderList
        restSOrderMockMvc.perform(get("/api/s-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].datecommande").value(hasItem(DEFAULT_DATECOMMANDE.toString())));
    }
    
    @Test
    @Transactional
    public void getSOrder() throws Exception {
        // Initialize the database
        sOrderRepository.saveAndFlush(sOrder);

        // Get the sOrder
        restSOrderMockMvc.perform(get("/api/s-orders/{id}", sOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sOrder.getId().intValue()))
            .andExpect(jsonPath("$.datecommande").value(DEFAULT_DATECOMMANDE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSOrder() throws Exception {
        // Get the sOrder
        restSOrderMockMvc.perform(get("/api/s-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSOrder() throws Exception {
        // Initialize the database
        sOrderRepository.saveAndFlush(sOrder);

        int databaseSizeBeforeUpdate = sOrderRepository.findAll().size();

        // Update the sOrder
        SOrder updatedSOrder = sOrderRepository.findById(sOrder.getId()).get();
        // Disconnect from session so that the updates on updatedSOrder are not directly saved in db
        em.detach(updatedSOrder);
        updatedSOrder
            .datecommande(UPDATED_DATECOMMANDE);
        SOrderDTO sOrderDTO = sOrderMapper.toDto(updatedSOrder);

        restSOrderMockMvc.perform(put("/api/s-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sOrderDTO)))
            .andExpect(status().isOk());

        // Validate the SOrder in the database
        List<SOrder> sOrderList = sOrderRepository.findAll();
        assertThat(sOrderList).hasSize(databaseSizeBeforeUpdate);
        SOrder testSOrder = sOrderList.get(sOrderList.size() - 1);
        assertThat(testSOrder.getDatecommande()).isEqualTo(UPDATED_DATECOMMANDE);
    }

    @Test
    @Transactional
    public void updateNonExistingSOrder() throws Exception {
        int databaseSizeBeforeUpdate = sOrderRepository.findAll().size();

        // Create the SOrder
        SOrderDTO sOrderDTO = sOrderMapper.toDto(sOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSOrderMockMvc.perform(put("/api/s-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SOrder in the database
        List<SOrder> sOrderList = sOrderRepository.findAll();
        assertThat(sOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSOrder() throws Exception {
        // Initialize the database
        sOrderRepository.saveAndFlush(sOrder);

        int databaseSizeBeforeDelete = sOrderRepository.findAll().size();

        // Delete the sOrder
        restSOrderMockMvc.perform(delete("/api/s-orders/{id}", sOrder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SOrder> sOrderList = sOrderRepository.findAll();
        assertThat(sOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
