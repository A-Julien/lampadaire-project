package com.lampaderum.web.rest;

import com.lampaderum.LampaderumApp;
import com.lampaderum.domain.Streetlamp;
import com.lampaderum.repository.StreetlampRepository;
import com.lampaderum.service.StreetlampService;
import com.lampaderum.service.dto.StreetlampDTO;
import com.lampaderum.service.mapper.StreetlampMapper;

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
 * Integration tests for the {@link StreetlampResource} REST controller.
 */
@SpringBootTest(classes = LampaderumApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StreetlampResourceIT {

    private static final String DEFAULT_LIBSTREETLAMP = "AAAAAAAAAA";
    private static final String UPDATED_LIBSTREETLAMP = "BBBBBBBBBB";

    private static final String DEFAULT_MODELESTREETLAMP = "AAAAAAAAAA";
    private static final String UPDATED_MODELESTREETLAMP = "BBBBBBBBBB";

    private static final Double DEFAULT_DUREEVIESTREETLAMP = 1D;
    private static final Double UPDATED_DUREEVIESTREETLAMP = 2D;

    private static final String DEFAULT_UNITEVIESTREETLAMP = "AAAAAAAAAA";
    private static final String UPDATED_UNITEVIESTREETLAMP = "BBBBBBBBBB";

    private static final String DEFAULT_MATERIAUSTREETLAMP = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAUSTREETLAMP = "BBBBBBBBBB";

    private static final String DEFAULT_LIBLAMPE = "AAAAAAAAAA";
    private static final String UPDATED_LIBLAMPE = "BBBBBBBBBB";

    private static final Double DEFAULT_PWLAMPE = 1D;
    private static final Double UPDATED_PWLAMPE = 2D;

    private static final String DEFAULT_FORMELAMPE = "AAAAAAAAAA";
    private static final String UPDATED_FORMELAMPE = "BBBBBBBBBB";

    private static final String DEFAULT_MODELELAMPE = "AAAAAAAAAA";
    private static final String UPDATED_MODELELAMPE = "BBBBBBBBBB";

    private static final Double DEFAULT_DUREEVIELAMPE = 1D;
    private static final Double UPDATED_DUREEVIELAMPE = 2D;

    private static final String DEFAULT_UNITEVIELAMPE = "AAAAAAAAAA";
    private static final String UPDATED_UNITEVIELAMPE = "BBBBBBBBBB";

    private static final Double DEFAULT_VOLTLAMPE = 1D;
    private static final Double UPDATED_VOLTLAMPE = 2D;

    private static final Double DEFAULT_TEMPLAMPE = 1D;
    private static final Double UPDATED_TEMPLAMPE = 2D;

    private static final String DEFAULT_IMAGEPATHSTREETLAMP = "AAAAAAAAAA";
    private static final String UPDATED_IMAGEPATHSTREETLAMP = "BBBBBBBBBB";

    private static final Integer DEFAULT_STOCKSTREETLAMP = 1;
    private static final Integer UPDATED_STOCKSTREETLAMP = 2;

    private static final Double DEFAULT_PRICESTREETLAMP = 1D;
    private static final Double UPDATED_PRICESTREETLAMP = 2D;

    @Autowired
    private StreetlampRepository streetlampRepository;

    @Autowired
    private StreetlampMapper streetlampMapper;

    @Autowired
    private StreetlampService streetlampService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStreetlampMockMvc;

    private Streetlamp streetlamp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Streetlamp createEntity(EntityManager em) {
        Streetlamp streetlamp = new Streetlamp()
            .libstreetlamp(DEFAULT_LIBSTREETLAMP)
            .modelestreetlamp(DEFAULT_MODELESTREETLAMP)
            .dureeviestreetlamp(DEFAULT_DUREEVIESTREETLAMP)
            .uniteviestreetlamp(DEFAULT_UNITEVIESTREETLAMP)
            .materiaustreetlamp(DEFAULT_MATERIAUSTREETLAMP)
            .liblampe(DEFAULT_LIBLAMPE)
            .pwlampe(DEFAULT_PWLAMPE)
            .formelampe(DEFAULT_FORMELAMPE)
            .modelelampe(DEFAULT_MODELELAMPE)
            .dureevielampe(DEFAULT_DUREEVIELAMPE)
            .unitevielampe(DEFAULT_UNITEVIELAMPE)
            .voltlampe(DEFAULT_VOLTLAMPE)
            .templampe(DEFAULT_TEMPLAMPE)
            .imagepathstreetlamp(DEFAULT_IMAGEPATHSTREETLAMP)
            .stockstreetlamp(DEFAULT_STOCKSTREETLAMP)
            .pricestreetlamp(DEFAULT_PRICESTREETLAMP);
        return streetlamp;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Streetlamp createUpdatedEntity(EntityManager em) {
        Streetlamp streetlamp = new Streetlamp()
            .libstreetlamp(UPDATED_LIBSTREETLAMP)
            .modelestreetlamp(UPDATED_MODELESTREETLAMP)
            .dureeviestreetlamp(UPDATED_DUREEVIESTREETLAMP)
            .uniteviestreetlamp(UPDATED_UNITEVIESTREETLAMP)
            .materiaustreetlamp(UPDATED_MATERIAUSTREETLAMP)
            .liblampe(UPDATED_LIBLAMPE)
            .pwlampe(UPDATED_PWLAMPE)
            .formelampe(UPDATED_FORMELAMPE)
            .modelelampe(UPDATED_MODELELAMPE)
            .dureevielampe(UPDATED_DUREEVIELAMPE)
            .unitevielampe(UPDATED_UNITEVIELAMPE)
            .voltlampe(UPDATED_VOLTLAMPE)
            .templampe(UPDATED_TEMPLAMPE)
            .imagepathstreetlamp(UPDATED_IMAGEPATHSTREETLAMP)
            .stockstreetlamp(UPDATED_STOCKSTREETLAMP)
            .pricestreetlamp(UPDATED_PRICESTREETLAMP);
        return streetlamp;
    }

    @BeforeEach
    public void initTest() {
        streetlamp = createEntity(em);
    }

    @Test
    @Transactional
    public void createStreetlamp() throws Exception {
        int databaseSizeBeforeCreate = streetlampRepository.findAll().size();
        // Create the Streetlamp
        StreetlampDTO streetlampDTO = streetlampMapper.toDto(streetlamp);
        restStreetlampMockMvc.perform(post("/api/streetlamps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(streetlampDTO)))
            .andExpect(status().isCreated());

        // Validate the Streetlamp in the database
        List<Streetlamp> streetlampList = streetlampRepository.findAll();
        assertThat(streetlampList).hasSize(databaseSizeBeforeCreate + 1);
        Streetlamp testStreetlamp = streetlampList.get(streetlampList.size() - 1);
        assertThat(testStreetlamp.getLibstreetlamp()).isEqualTo(DEFAULT_LIBSTREETLAMP);
        assertThat(testStreetlamp.getModelestreetlamp()).isEqualTo(DEFAULT_MODELESTREETLAMP);
        assertThat(testStreetlamp.getDureeviestreetlamp()).isEqualTo(DEFAULT_DUREEVIESTREETLAMP);
        assertThat(testStreetlamp.getUniteviestreetlamp()).isEqualTo(DEFAULT_UNITEVIESTREETLAMP);
        assertThat(testStreetlamp.getMateriaustreetlamp()).isEqualTo(DEFAULT_MATERIAUSTREETLAMP);
        assertThat(testStreetlamp.getLiblampe()).isEqualTo(DEFAULT_LIBLAMPE);
        assertThat(testStreetlamp.getPwlampe()).isEqualTo(DEFAULT_PWLAMPE);
        assertThat(testStreetlamp.getFormelampe()).isEqualTo(DEFAULT_FORMELAMPE);
        assertThat(testStreetlamp.getModelelampe()).isEqualTo(DEFAULT_MODELELAMPE);
        assertThat(testStreetlamp.getDureevielampe()).isEqualTo(DEFAULT_DUREEVIELAMPE);
        assertThat(testStreetlamp.getUnitevielampe()).isEqualTo(DEFAULT_UNITEVIELAMPE);
        assertThat(testStreetlamp.getVoltlampe()).isEqualTo(DEFAULT_VOLTLAMPE);
        assertThat(testStreetlamp.getTemplampe()).isEqualTo(DEFAULT_TEMPLAMPE);
        //assertThat(testStreetlamp.getImagepathstreetlamp()).isEqualTo(DEFAULT_IMAGEPATHSTREETLAMP);
        assertThat(testStreetlamp.getStockstreetlamp()).isEqualTo(DEFAULT_STOCKSTREETLAMP);
        assertThat(testStreetlamp.getPricestreetlamp()).isEqualTo(DEFAULT_PRICESTREETLAMP);
    }

    @Test
    @Transactional
    public void createStreetlampWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = streetlampRepository.findAll().size();

        // Create the Streetlamp with an existing ID
        streetlamp.setId(1L);
        StreetlampDTO streetlampDTO = streetlampMapper.toDto(streetlamp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStreetlampMockMvc.perform(post("/api/streetlamps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(streetlampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Streetlamp in the database
        List<Streetlamp> streetlampList = streetlampRepository.findAll();
        assertThat(streetlampList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStreetlamps() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList
        restStreetlampMockMvc.perform(get("/api/streetlamps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(streetlamp.getId().intValue())))
            .andExpect(jsonPath("$.[*].libstreetlamp").value(hasItem(DEFAULT_LIBSTREETLAMP)))
            .andExpect(jsonPath("$.[*].modelestreetlamp").value(hasItem(DEFAULT_MODELESTREETLAMP)))
            .andExpect(jsonPath("$.[*].dureeviestreetlamp").value(hasItem(DEFAULT_DUREEVIESTREETLAMP.doubleValue())))
            .andExpect(jsonPath("$.[*].uniteviestreetlamp").value(hasItem(DEFAULT_UNITEVIESTREETLAMP)))
            .andExpect(jsonPath("$.[*].materiaustreetlamp").value(hasItem(DEFAULT_MATERIAUSTREETLAMP)))
            .andExpect(jsonPath("$.[*].liblampe").value(hasItem(DEFAULT_LIBLAMPE)))
            .andExpect(jsonPath("$.[*].pwlampe").value(hasItem(DEFAULT_PWLAMPE.doubleValue())))
            .andExpect(jsonPath("$.[*].formelampe").value(hasItem(DEFAULT_FORMELAMPE)))
            .andExpect(jsonPath("$.[*].modelelampe").value(hasItem(DEFAULT_MODELELAMPE)))
            .andExpect(jsonPath("$.[*].dureevielampe").value(hasItem(DEFAULT_DUREEVIELAMPE.doubleValue())))
            .andExpect(jsonPath("$.[*].unitevielampe").value(hasItem(DEFAULT_UNITEVIELAMPE)))
            .andExpect(jsonPath("$.[*].voltlampe").value(hasItem(DEFAULT_VOLTLAMPE.doubleValue())))
            .andExpect(jsonPath("$.[*].templampe").value(hasItem(DEFAULT_TEMPLAMPE.doubleValue())))
            .andExpect(jsonPath("$.[*].stockstreetlamp").value(hasItem(DEFAULT_STOCKSTREETLAMP)))
            .andExpect(jsonPath("$.[*].pricestreetlamp").value(hasItem(DEFAULT_PRICESTREETLAMP.doubleValue())));
            //.andExpect(jsonPath("$.[*].imagepathstreetlamp").value(hasItem(DEFAULT_IMAGEPATHSTREETLAMP)))
    }

    @Test
    @Transactional
    public void getStreetlamp() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get the streetlamp
        restStreetlampMockMvc.perform(get("/api/streetlamps/{id}", streetlamp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(streetlamp.getId().intValue()))
            .andExpect(jsonPath("$.libstreetlamp").value(DEFAULT_LIBSTREETLAMP))
            .andExpect(jsonPath("$.modelestreetlamp").value(DEFAULT_MODELESTREETLAMP))
            .andExpect(jsonPath("$.dureeviestreetlamp").value(DEFAULT_DUREEVIESTREETLAMP.doubleValue()))
            .andExpect(jsonPath("$.uniteviestreetlamp").value(DEFAULT_UNITEVIESTREETLAMP))
            .andExpect(jsonPath("$.materiaustreetlamp").value(DEFAULT_MATERIAUSTREETLAMP))
            .andExpect(jsonPath("$.liblampe").value(DEFAULT_LIBLAMPE))
            .andExpect(jsonPath("$.pwlampe").value(DEFAULT_PWLAMPE.doubleValue()))
            .andExpect(jsonPath("$.formelampe").value(DEFAULT_FORMELAMPE))
            .andExpect(jsonPath("$.modelelampe").value(DEFAULT_MODELELAMPE))
            .andExpect(jsonPath("$.dureevielampe").value(DEFAULT_DUREEVIELAMPE.doubleValue()))
            .andExpect(jsonPath("$.unitevielampe").value(DEFAULT_UNITEVIELAMPE))
            .andExpect(jsonPath("$.voltlampe").value(DEFAULT_VOLTLAMPE.doubleValue()))
            .andExpect(jsonPath("$.templampe").value(DEFAULT_TEMPLAMPE.doubleValue()))
            .andExpect(jsonPath("$.stockstreetlamp").value(DEFAULT_STOCKSTREETLAMP))
            .andExpect(jsonPath("$.pricestreetlamp").value(DEFAULT_PRICESTREETLAMP.doubleValue()));
            //.andExpect(jsonPath("$.imagepathstreetlamp").value(DEFAULT_IMAGEPATHSTREETLAMP))
    }
    @Test
    @Transactional
    public void getNonExistingStreetlamp() throws Exception {
        // Get the streetlamp
        restStreetlampMockMvc.perform(get("/api/streetlamps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStreetlamp() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        int databaseSizeBeforeUpdate = streetlampRepository.findAll().size();

        // Update the streetlamp
        Streetlamp updatedStreetlamp = streetlampRepository.findById(streetlamp.getId()).get();
        // Disconnect from session so that the updates on updatedStreetlamp are not directly saved in db
        em.detach(updatedStreetlamp);
        updatedStreetlamp
            .libstreetlamp(UPDATED_LIBSTREETLAMP)
            .modelestreetlamp(UPDATED_MODELESTREETLAMP)
            .dureeviestreetlamp(UPDATED_DUREEVIESTREETLAMP)
            .uniteviestreetlamp(UPDATED_UNITEVIESTREETLAMP)
            .materiaustreetlamp(UPDATED_MATERIAUSTREETLAMP)
            .liblampe(UPDATED_LIBLAMPE)
            .pwlampe(UPDATED_PWLAMPE)
            .formelampe(UPDATED_FORMELAMPE)
            .modelelampe(UPDATED_MODELELAMPE)
            .dureevielampe(UPDATED_DUREEVIELAMPE)
            .unitevielampe(UPDATED_UNITEVIELAMPE)
            .voltlampe(UPDATED_VOLTLAMPE)
            .templampe(UPDATED_TEMPLAMPE)
            .imagepathstreetlamp(UPDATED_IMAGEPATHSTREETLAMP)
            .stockstreetlamp(UPDATED_STOCKSTREETLAMP)
            .pricestreetlamp(UPDATED_PRICESTREETLAMP);
        StreetlampDTO streetlampDTO = streetlampMapper.toDto(updatedStreetlamp);

        restStreetlampMockMvc.perform(put("/api/streetlamps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(streetlampDTO)))
            .andExpect(status().isOk());

        // Validate the Streetlamp in the database
        List<Streetlamp> streetlampList = streetlampRepository.findAll();
        assertThat(streetlampList).hasSize(databaseSizeBeforeUpdate);
        Streetlamp testStreetlamp = streetlampList.get(streetlampList.size() - 1);
        assertThat(testStreetlamp.getLibstreetlamp()).isEqualTo(UPDATED_LIBSTREETLAMP);
        assertThat(testStreetlamp.getModelestreetlamp()).isEqualTo(UPDATED_MODELESTREETLAMP);
        assertThat(testStreetlamp.getDureeviestreetlamp()).isEqualTo(UPDATED_DUREEVIESTREETLAMP);
        assertThat(testStreetlamp.getUniteviestreetlamp()).isEqualTo(UPDATED_UNITEVIESTREETLAMP);
        assertThat(testStreetlamp.getMateriaustreetlamp()).isEqualTo(UPDATED_MATERIAUSTREETLAMP);
        assertThat(testStreetlamp.getLiblampe()).isEqualTo(UPDATED_LIBLAMPE);
        assertThat(testStreetlamp.getPwlampe()).isEqualTo(UPDATED_PWLAMPE);
        assertThat(testStreetlamp.getFormelampe()).isEqualTo(UPDATED_FORMELAMPE);
        assertThat(testStreetlamp.getModelelampe()).isEqualTo(UPDATED_MODELELAMPE);
        assertThat(testStreetlamp.getDureevielampe()).isEqualTo(UPDATED_DUREEVIELAMPE);
        assertThat(testStreetlamp.getUnitevielampe()).isEqualTo(UPDATED_UNITEVIELAMPE);
        assertThat(testStreetlamp.getVoltlampe()).isEqualTo(UPDATED_VOLTLAMPE);
        assertThat(testStreetlamp.getTemplampe()).isEqualTo(UPDATED_TEMPLAMPE);
        //assertThat(testStreetlamp.getImagepathstreetlamp()).isEqualTo(UPDATED_IMAGEPATHSTREETLAMP);
        assertThat(testStreetlamp.getStockstreetlamp()).isEqualTo(UPDATED_STOCKSTREETLAMP);
        assertThat(testStreetlamp.getPricestreetlamp()).isEqualTo(UPDATED_PRICESTREETLAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingStreetlamp() throws Exception {
        int databaseSizeBeforeUpdate = streetlampRepository.findAll().size();

        // Create the Streetlamp
        StreetlampDTO streetlampDTO = streetlampMapper.toDto(streetlamp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStreetlampMockMvc.perform(put("/api/streetlamps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(streetlampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Streetlamp in the database
        List<Streetlamp> streetlampList = streetlampRepository.findAll();
        assertThat(streetlampList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStreetlamp() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        int databaseSizeBeforeDelete = streetlampRepository.findAll().size();

        // Delete the streetlamp
        restStreetlampMockMvc.perform(delete("/api/streetlamps/{id}", streetlamp.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Streetlamp> streetlampList = streetlampRepository.findAll();
        assertThat(streetlampList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
