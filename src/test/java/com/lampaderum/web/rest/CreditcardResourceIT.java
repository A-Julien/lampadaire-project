package com.lampaderum.web.rest;

import com.lampaderum.LampaderumApp;
import com.lampaderum.domain.Creditcard;
import com.lampaderum.repository.CreditcardRepository;
import com.lampaderum.service.CreditcardService;
import com.lampaderum.service.dto.CreditcardDTO;
import com.lampaderum.service.mapper.CreditcardMapper;

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
 * Integration tests for the {@link CreditcardResource} REST controller.
 */
@SpringBootTest(classes = LampaderumApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CreditcardResourceIT {

    private static final String DEFAULT_NUMCARTE = "AAAAAAAAAA";
    private static final String UPDATED_NUMCARTE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATEEXPIRATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEEXPIRATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private CreditcardRepository creditcardRepository;

    @Autowired
    private CreditcardMapper creditcardMapper;

    @Autowired
    private CreditcardService creditcardService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreditcardMockMvc;

    private Creditcard creditcard;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creditcard createEntity(EntityManager em) {
        Creditcard creditcard = new Creditcard()
            .numcarte(DEFAULT_NUMCARTE)
            .dateexpiration(DEFAULT_DATEEXPIRATION)
            .code(DEFAULT_CODE);
        return creditcard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creditcard createUpdatedEntity(EntityManager em) {
        Creditcard creditcard = new Creditcard()
            .numcarte(UPDATED_NUMCARTE)
            .dateexpiration(UPDATED_DATEEXPIRATION)
            .code(UPDATED_CODE);
        return creditcard;
    }

    @BeforeEach
    public void initTest() {
        creditcard = createEntity(em);
    }

    @Test
    @Transactional
    public void createCreditcard() throws Exception {
        int databaseSizeBeforeCreate = creditcardRepository.findAll().size();
        // Create the Creditcard
        CreditcardDTO creditcardDTO = creditcardMapper.toDto(creditcard);
        restCreditcardMockMvc.perform(post("/api/creditcards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditcardDTO)))
            .andExpect(status().isCreated());

        // Validate the Creditcard in the database
        List<Creditcard> creditcardList = creditcardRepository.findAll();
        assertThat(creditcardList).hasSize(databaseSizeBeforeCreate + 1);
        Creditcard testCreditcard = creditcardList.get(creditcardList.size() - 1);
        assertThat(testCreditcard.getNumcarte()).isEqualTo(DEFAULT_NUMCARTE);
        assertThat(testCreditcard.getDateexpiration()).isEqualTo(DEFAULT_DATEEXPIRATION);
        assertThat(testCreditcard.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createCreditcardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = creditcardRepository.findAll().size();

        // Create the Creditcard with an existing ID
        creditcard.setId(1L);
        CreditcardDTO creditcardDTO = creditcardMapper.toDto(creditcard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreditcardMockMvc.perform(post("/api/creditcards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditcardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Creditcard in the database
        List<Creditcard> creditcardList = creditcardRepository.findAll();
        assertThat(creditcardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCreditcards() throws Exception {
        // Initialize the database
        creditcardRepository.saveAndFlush(creditcard);

        // Get all the creditcardList
        restCreditcardMockMvc.perform(get("/api/creditcards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creditcard.getId().intValue())))
            .andExpect(jsonPath("$.[*].numcarte").value(hasItem(DEFAULT_NUMCARTE)))
            .andExpect(jsonPath("$.[*].dateexpiration").value(hasItem(DEFAULT_DATEEXPIRATION.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getCreditcard() throws Exception {
        // Initialize the database
        creditcardRepository.saveAndFlush(creditcard);

        // Get the creditcard
        restCreditcardMockMvc.perform(get("/api/creditcards/{id}", creditcard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(creditcard.getId().intValue()))
            .andExpect(jsonPath("$.numcarte").value(DEFAULT_NUMCARTE))
            .andExpect(jsonPath("$.dateexpiration").value(DEFAULT_DATEEXPIRATION.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingCreditcard() throws Exception {
        // Get the creditcard
        restCreditcardMockMvc.perform(get("/api/creditcards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCreditcard() throws Exception {
        // Initialize the database
        creditcardRepository.saveAndFlush(creditcard);

        int databaseSizeBeforeUpdate = creditcardRepository.findAll().size();

        // Update the creditcard
        Creditcard updatedCreditcard = creditcardRepository.findById(creditcard.getId()).get();
        // Disconnect from session so that the updates on updatedCreditcard are not directly saved in db
        em.detach(updatedCreditcard);
        updatedCreditcard
            .numcarte(UPDATED_NUMCARTE)
            .dateexpiration(UPDATED_DATEEXPIRATION)
            .code(UPDATED_CODE);
        CreditcardDTO creditcardDTO = creditcardMapper.toDto(updatedCreditcard);

        restCreditcardMockMvc.perform(put("/api/creditcards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditcardDTO)))
            .andExpect(status().isOk());

        // Validate the Creditcard in the database
        List<Creditcard> creditcardList = creditcardRepository.findAll();
        assertThat(creditcardList).hasSize(databaseSizeBeforeUpdate);
        Creditcard testCreditcard = creditcardList.get(creditcardList.size() - 1);
        assertThat(testCreditcard.getNumcarte()).isEqualTo(UPDATED_NUMCARTE);
        assertThat(testCreditcard.getDateexpiration()).isEqualTo(UPDATED_DATEEXPIRATION);
        assertThat(testCreditcard.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingCreditcard() throws Exception {
        int databaseSizeBeforeUpdate = creditcardRepository.findAll().size();

        // Create the Creditcard
        CreditcardDTO creditcardDTO = creditcardMapper.toDto(creditcard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreditcardMockMvc.perform(put("/api/creditcards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditcardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Creditcard in the database
        List<Creditcard> creditcardList = creditcardRepository.findAll();
        assertThat(creditcardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCreditcard() throws Exception {
        // Initialize the database
        creditcardRepository.saveAndFlush(creditcard);

        int databaseSizeBeforeDelete = creditcardRepository.findAll().size();

        // Delete the creditcard
        restCreditcardMockMvc.perform(delete("/api/creditcards/{id}", creditcard.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Creditcard> creditcardList = creditcardRepository.findAll();
        assertThat(creditcardList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
