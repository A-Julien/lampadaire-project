package com.lampaderum.web.rest;

import com.lampaderum.LampaderumApp;
import com.lampaderum.domain.Streetlamp;
import com.lampaderum.repository.StreetlampRepository;
import com.lampaderum.service.StreetlampService;
import com.lampaderum.service.dto.StreetlampDTO;
import com.lampaderum.service.mapper.StreetlampMapper;
import com.lampaderum.service.dto.StreetlampCriteria;
import com.lampaderum.service.StreetlampQueryService;

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
    private static final Double SMALLER_DUREEVIESTREETLAMP = 1D - 1D;

    private static final String DEFAULT_UNITEVIESTREETLAMP = "AAAAAAAAAA";
    private static final String UPDATED_UNITEVIESTREETLAMP = "BBBBBBBBBB";

    private static final String DEFAULT_MATERIAUSTREETLAMP = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAUSTREETLAMP = "BBBBBBBBBB";

    private static final String DEFAULT_LIBLAMPE = "AAAAAAAAAA";
    private static final String UPDATED_LIBLAMPE = "BBBBBBBBBB";

    private static final Double DEFAULT_PWLAMPE = 1D;
    private static final Double UPDATED_PWLAMPE = 2D;
    private static final Double SMALLER_PWLAMPE = 1D - 1D;

    private static final String DEFAULT_FORMELAMPE = "AAAAAAAAAA";
    private static final String UPDATED_FORMELAMPE = "BBBBBBBBBB";

    private static final String DEFAULT_MODELELAMPE = "AAAAAAAAAA";
    private static final String UPDATED_MODELELAMPE = "BBBBBBBBBB";

    private static final Double DEFAULT_DUREEVIELAMPE = 1D;
    private static final Double UPDATED_DUREEVIELAMPE = 2D;
    private static final Double SMALLER_DUREEVIELAMPE = 1D - 1D;

    private static final String DEFAULT_UNITEVIELAMPE = "AAAAAAAAAA";
    private static final String UPDATED_UNITEVIELAMPE = "BBBBBBBBBB";

    private static final Double DEFAULT_VOLTLAMPE = 1D;
    private static final Double UPDATED_VOLTLAMPE = 2D;
    private static final Double SMALLER_VOLTLAMPE = 1D - 1D;

    private static final Double DEFAULT_TEMPLAMPE = 1D;
    private static final Double UPDATED_TEMPLAMPE = 2D;
    private static final Double SMALLER_TEMPLAMPE = 1D - 1D;

    private static final String DEFAULT_IMAGEPATHSTREETLAMP = "AAAAAAAAAA";
    private static final String UPDATED_IMAGEPATHSTREETLAMP = "BBBBBBBBBB";

    private static final Integer DEFAULT_STOCKSTREETLAMP = 1;
    private static final Integer UPDATED_STOCKSTREETLAMP = 2;
    private static final Integer SMALLER_STOCKSTREETLAMP = 1 - 1;

    private static final Double DEFAULT_PRICESTREETLAMP = 1D;
    private static final Double UPDATED_PRICESTREETLAMP = 2D;
    private static final Double SMALLER_PRICESTREETLAMP = 1D - 1D;

    @Autowired
    private StreetlampRepository streetlampRepository;

    @Autowired
    private StreetlampMapper streetlampMapper;

    @Autowired
    private StreetlampService streetlampService;

    @Autowired
    private StreetlampQueryService streetlampQueryService;

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
    }


    @Test
    @Transactional
    public void getStreetlampsByIdFiltering() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        Long id = streetlamp.getId();

        defaultStreetlampShouldBeFound("id.equals=" + id);
        defaultStreetlampShouldNotBeFound("id.notEquals=" + id);

        defaultStreetlampShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultStreetlampShouldNotBeFound("id.greaterThan=" + id);

        defaultStreetlampShouldBeFound("id.lessThanOrEqual=" + id);
        defaultStreetlampShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByLibstreetlampIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where libstreetlamp equals to DEFAULT_LIBSTREETLAMP
        defaultStreetlampShouldBeFound("libstreetlamp.equals=" + DEFAULT_LIBSTREETLAMP);

        // Get all the streetlampList where libstreetlamp equals to UPDATED_LIBSTREETLAMP
        defaultStreetlampShouldNotBeFound("libstreetlamp.equals=" + UPDATED_LIBSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByLibstreetlampIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where libstreetlamp not equals to DEFAULT_LIBSTREETLAMP
        defaultStreetlampShouldNotBeFound("libstreetlamp.notEquals=" + DEFAULT_LIBSTREETLAMP);

        // Get all the streetlampList where libstreetlamp not equals to UPDATED_LIBSTREETLAMP
        defaultStreetlampShouldBeFound("libstreetlamp.notEquals=" + UPDATED_LIBSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByLibstreetlampIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where libstreetlamp in DEFAULT_LIBSTREETLAMP or UPDATED_LIBSTREETLAMP
        defaultStreetlampShouldBeFound("libstreetlamp.in=" + DEFAULT_LIBSTREETLAMP + "," + UPDATED_LIBSTREETLAMP);

        // Get all the streetlampList where libstreetlamp equals to UPDATED_LIBSTREETLAMP
        defaultStreetlampShouldNotBeFound("libstreetlamp.in=" + UPDATED_LIBSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByLibstreetlampIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where libstreetlamp is not null
        defaultStreetlampShouldBeFound("libstreetlamp.specified=true");

        // Get all the streetlampList where libstreetlamp is null
        defaultStreetlampShouldNotBeFound("libstreetlamp.specified=false");
    }
                @Test
    @Transactional
    public void getAllStreetlampsByLibstreetlampContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where libstreetlamp contains DEFAULT_LIBSTREETLAMP
        defaultStreetlampShouldBeFound("libstreetlamp.contains=" + DEFAULT_LIBSTREETLAMP);

        // Get all the streetlampList where libstreetlamp contains UPDATED_LIBSTREETLAMP
        defaultStreetlampShouldNotBeFound("libstreetlamp.contains=" + UPDATED_LIBSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByLibstreetlampNotContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where libstreetlamp does not contain DEFAULT_LIBSTREETLAMP
        defaultStreetlampShouldNotBeFound("libstreetlamp.doesNotContain=" + DEFAULT_LIBSTREETLAMP);

        // Get all the streetlampList where libstreetlamp does not contain UPDATED_LIBSTREETLAMP
        defaultStreetlampShouldBeFound("libstreetlamp.doesNotContain=" + UPDATED_LIBSTREETLAMP);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByModelestreetlampIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelestreetlamp equals to DEFAULT_MODELESTREETLAMP
        defaultStreetlampShouldBeFound("modelestreetlamp.equals=" + DEFAULT_MODELESTREETLAMP);

        // Get all the streetlampList where modelestreetlamp equals to UPDATED_MODELESTREETLAMP
        defaultStreetlampShouldNotBeFound("modelestreetlamp.equals=" + UPDATED_MODELESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByModelestreetlampIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelestreetlamp not equals to DEFAULT_MODELESTREETLAMP
        defaultStreetlampShouldNotBeFound("modelestreetlamp.notEquals=" + DEFAULT_MODELESTREETLAMP);

        // Get all the streetlampList where modelestreetlamp not equals to UPDATED_MODELESTREETLAMP
        defaultStreetlampShouldBeFound("modelestreetlamp.notEquals=" + UPDATED_MODELESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByModelestreetlampIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelestreetlamp in DEFAULT_MODELESTREETLAMP or UPDATED_MODELESTREETLAMP
        defaultStreetlampShouldBeFound("modelestreetlamp.in=" + DEFAULT_MODELESTREETLAMP + "," + UPDATED_MODELESTREETLAMP);

        // Get all the streetlampList where modelestreetlamp equals to UPDATED_MODELESTREETLAMP
        defaultStreetlampShouldNotBeFound("modelestreetlamp.in=" + UPDATED_MODELESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByModelestreetlampIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelestreetlamp is not null
        defaultStreetlampShouldBeFound("modelestreetlamp.specified=true");

        // Get all the streetlampList where modelestreetlamp is null
        defaultStreetlampShouldNotBeFound("modelestreetlamp.specified=false");
    }
                @Test
    @Transactional
    public void getAllStreetlampsByModelestreetlampContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelestreetlamp contains DEFAULT_MODELESTREETLAMP
        defaultStreetlampShouldBeFound("modelestreetlamp.contains=" + DEFAULT_MODELESTREETLAMP);

        // Get all the streetlampList where modelestreetlamp contains UPDATED_MODELESTREETLAMP
        defaultStreetlampShouldNotBeFound("modelestreetlamp.contains=" + UPDATED_MODELESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByModelestreetlampNotContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelestreetlamp does not contain DEFAULT_MODELESTREETLAMP
        defaultStreetlampShouldNotBeFound("modelestreetlamp.doesNotContain=" + DEFAULT_MODELESTREETLAMP);

        // Get all the streetlampList where modelestreetlamp does not contain UPDATED_MODELESTREETLAMP
        defaultStreetlampShouldBeFound("modelestreetlamp.doesNotContain=" + UPDATED_MODELESTREETLAMP);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByDureeviestreetlampIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureeviestreetlamp equals to DEFAULT_DUREEVIESTREETLAMP
        defaultStreetlampShouldBeFound("dureeviestreetlamp.equals=" + DEFAULT_DUREEVIESTREETLAMP);

        // Get all the streetlampList where dureeviestreetlamp equals to UPDATED_DUREEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("dureeviestreetlamp.equals=" + UPDATED_DUREEVIESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureeviestreetlampIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureeviestreetlamp not equals to DEFAULT_DUREEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("dureeviestreetlamp.notEquals=" + DEFAULT_DUREEVIESTREETLAMP);

        // Get all the streetlampList where dureeviestreetlamp not equals to UPDATED_DUREEVIESTREETLAMP
        defaultStreetlampShouldBeFound("dureeviestreetlamp.notEquals=" + UPDATED_DUREEVIESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureeviestreetlampIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureeviestreetlamp in DEFAULT_DUREEVIESTREETLAMP or UPDATED_DUREEVIESTREETLAMP
        defaultStreetlampShouldBeFound("dureeviestreetlamp.in=" + DEFAULT_DUREEVIESTREETLAMP + "," + UPDATED_DUREEVIESTREETLAMP);

        // Get all the streetlampList where dureeviestreetlamp equals to UPDATED_DUREEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("dureeviestreetlamp.in=" + UPDATED_DUREEVIESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureeviestreetlampIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureeviestreetlamp is not null
        defaultStreetlampShouldBeFound("dureeviestreetlamp.specified=true");

        // Get all the streetlampList where dureeviestreetlamp is null
        defaultStreetlampShouldNotBeFound("dureeviestreetlamp.specified=false");
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureeviestreetlampIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureeviestreetlamp is greater than or equal to DEFAULT_DUREEVIESTREETLAMP
        defaultStreetlampShouldBeFound("dureeviestreetlamp.greaterThanOrEqual=" + DEFAULT_DUREEVIESTREETLAMP);

        // Get all the streetlampList where dureeviestreetlamp is greater than or equal to UPDATED_DUREEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("dureeviestreetlamp.greaterThanOrEqual=" + UPDATED_DUREEVIESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureeviestreetlampIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureeviestreetlamp is less than or equal to DEFAULT_DUREEVIESTREETLAMP
        defaultStreetlampShouldBeFound("dureeviestreetlamp.lessThanOrEqual=" + DEFAULT_DUREEVIESTREETLAMP);

        // Get all the streetlampList where dureeviestreetlamp is less than or equal to SMALLER_DUREEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("dureeviestreetlamp.lessThanOrEqual=" + SMALLER_DUREEVIESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureeviestreetlampIsLessThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureeviestreetlamp is less than DEFAULT_DUREEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("dureeviestreetlamp.lessThan=" + DEFAULT_DUREEVIESTREETLAMP);

        // Get all the streetlampList where dureeviestreetlamp is less than UPDATED_DUREEVIESTREETLAMP
        defaultStreetlampShouldBeFound("dureeviestreetlamp.lessThan=" + UPDATED_DUREEVIESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureeviestreetlampIsGreaterThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureeviestreetlamp is greater than DEFAULT_DUREEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("dureeviestreetlamp.greaterThan=" + DEFAULT_DUREEVIESTREETLAMP);

        // Get all the streetlampList where dureeviestreetlamp is greater than SMALLER_DUREEVIESTREETLAMP
        defaultStreetlampShouldBeFound("dureeviestreetlamp.greaterThan=" + SMALLER_DUREEVIESTREETLAMP);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByUniteviestreetlampIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where uniteviestreetlamp equals to DEFAULT_UNITEVIESTREETLAMP
        defaultStreetlampShouldBeFound("uniteviestreetlamp.equals=" + DEFAULT_UNITEVIESTREETLAMP);

        // Get all the streetlampList where uniteviestreetlamp equals to UPDATED_UNITEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("uniteviestreetlamp.equals=" + UPDATED_UNITEVIESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByUniteviestreetlampIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where uniteviestreetlamp not equals to DEFAULT_UNITEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("uniteviestreetlamp.notEquals=" + DEFAULT_UNITEVIESTREETLAMP);

        // Get all the streetlampList where uniteviestreetlamp not equals to UPDATED_UNITEVIESTREETLAMP
        defaultStreetlampShouldBeFound("uniteviestreetlamp.notEquals=" + UPDATED_UNITEVIESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByUniteviestreetlampIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where uniteviestreetlamp in DEFAULT_UNITEVIESTREETLAMP or UPDATED_UNITEVIESTREETLAMP
        defaultStreetlampShouldBeFound("uniteviestreetlamp.in=" + DEFAULT_UNITEVIESTREETLAMP + "," + UPDATED_UNITEVIESTREETLAMP);

        // Get all the streetlampList where uniteviestreetlamp equals to UPDATED_UNITEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("uniteviestreetlamp.in=" + UPDATED_UNITEVIESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByUniteviestreetlampIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where uniteviestreetlamp is not null
        defaultStreetlampShouldBeFound("uniteviestreetlamp.specified=true");

        // Get all the streetlampList where uniteviestreetlamp is null
        defaultStreetlampShouldNotBeFound("uniteviestreetlamp.specified=false");
    }
                @Test
    @Transactional
    public void getAllStreetlampsByUniteviestreetlampContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where uniteviestreetlamp contains DEFAULT_UNITEVIESTREETLAMP
        defaultStreetlampShouldBeFound("uniteviestreetlamp.contains=" + DEFAULT_UNITEVIESTREETLAMP);

        // Get all the streetlampList where uniteviestreetlamp contains UPDATED_UNITEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("uniteviestreetlamp.contains=" + UPDATED_UNITEVIESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByUniteviestreetlampNotContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where uniteviestreetlamp does not contain DEFAULT_UNITEVIESTREETLAMP
        defaultStreetlampShouldNotBeFound("uniteviestreetlamp.doesNotContain=" + DEFAULT_UNITEVIESTREETLAMP);

        // Get all the streetlampList where uniteviestreetlamp does not contain UPDATED_UNITEVIESTREETLAMP
        defaultStreetlampShouldBeFound("uniteviestreetlamp.doesNotContain=" + UPDATED_UNITEVIESTREETLAMP);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByMateriaustreetlampIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where materiaustreetlamp equals to DEFAULT_MATERIAUSTREETLAMP
        defaultStreetlampShouldBeFound("materiaustreetlamp.equals=" + DEFAULT_MATERIAUSTREETLAMP);

        // Get all the streetlampList where materiaustreetlamp equals to UPDATED_MATERIAUSTREETLAMP
        defaultStreetlampShouldNotBeFound("materiaustreetlamp.equals=" + UPDATED_MATERIAUSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByMateriaustreetlampIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where materiaustreetlamp not equals to DEFAULT_MATERIAUSTREETLAMP
        defaultStreetlampShouldNotBeFound("materiaustreetlamp.notEquals=" + DEFAULT_MATERIAUSTREETLAMP);

        // Get all the streetlampList where materiaustreetlamp not equals to UPDATED_MATERIAUSTREETLAMP
        defaultStreetlampShouldBeFound("materiaustreetlamp.notEquals=" + UPDATED_MATERIAUSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByMateriaustreetlampIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where materiaustreetlamp in DEFAULT_MATERIAUSTREETLAMP or UPDATED_MATERIAUSTREETLAMP
        defaultStreetlampShouldBeFound("materiaustreetlamp.in=" + DEFAULT_MATERIAUSTREETLAMP + "," + UPDATED_MATERIAUSTREETLAMP);

        // Get all the streetlampList where materiaustreetlamp equals to UPDATED_MATERIAUSTREETLAMP
        defaultStreetlampShouldNotBeFound("materiaustreetlamp.in=" + UPDATED_MATERIAUSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByMateriaustreetlampIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where materiaustreetlamp is not null
        defaultStreetlampShouldBeFound("materiaustreetlamp.specified=true");

        // Get all the streetlampList where materiaustreetlamp is null
        defaultStreetlampShouldNotBeFound("materiaustreetlamp.specified=false");
    }
                @Test
    @Transactional
    public void getAllStreetlampsByMateriaustreetlampContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where materiaustreetlamp contains DEFAULT_MATERIAUSTREETLAMP
        defaultStreetlampShouldBeFound("materiaustreetlamp.contains=" + DEFAULT_MATERIAUSTREETLAMP);

        // Get all the streetlampList where materiaustreetlamp contains UPDATED_MATERIAUSTREETLAMP
        defaultStreetlampShouldNotBeFound("materiaustreetlamp.contains=" + UPDATED_MATERIAUSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByMateriaustreetlampNotContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where materiaustreetlamp does not contain DEFAULT_MATERIAUSTREETLAMP
        defaultStreetlampShouldNotBeFound("materiaustreetlamp.doesNotContain=" + DEFAULT_MATERIAUSTREETLAMP);

        // Get all the streetlampList where materiaustreetlamp does not contain UPDATED_MATERIAUSTREETLAMP
        defaultStreetlampShouldBeFound("materiaustreetlamp.doesNotContain=" + UPDATED_MATERIAUSTREETLAMP);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByLiblampeIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where liblampe equals to DEFAULT_LIBLAMPE
        defaultStreetlampShouldBeFound("liblampe.equals=" + DEFAULT_LIBLAMPE);

        // Get all the streetlampList where liblampe equals to UPDATED_LIBLAMPE
        defaultStreetlampShouldNotBeFound("liblampe.equals=" + UPDATED_LIBLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByLiblampeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where liblampe not equals to DEFAULT_LIBLAMPE
        defaultStreetlampShouldNotBeFound("liblampe.notEquals=" + DEFAULT_LIBLAMPE);

        // Get all the streetlampList where liblampe not equals to UPDATED_LIBLAMPE
        defaultStreetlampShouldBeFound("liblampe.notEquals=" + UPDATED_LIBLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByLiblampeIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where liblampe in DEFAULT_LIBLAMPE or UPDATED_LIBLAMPE
        defaultStreetlampShouldBeFound("liblampe.in=" + DEFAULT_LIBLAMPE + "," + UPDATED_LIBLAMPE);

        // Get all the streetlampList where liblampe equals to UPDATED_LIBLAMPE
        defaultStreetlampShouldNotBeFound("liblampe.in=" + UPDATED_LIBLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByLiblampeIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where liblampe is not null
        defaultStreetlampShouldBeFound("liblampe.specified=true");

        // Get all the streetlampList where liblampe is null
        defaultStreetlampShouldNotBeFound("liblampe.specified=false");
    }
                @Test
    @Transactional
    public void getAllStreetlampsByLiblampeContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where liblampe contains DEFAULT_LIBLAMPE
        defaultStreetlampShouldBeFound("liblampe.contains=" + DEFAULT_LIBLAMPE);

        // Get all the streetlampList where liblampe contains UPDATED_LIBLAMPE
        defaultStreetlampShouldNotBeFound("liblampe.contains=" + UPDATED_LIBLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByLiblampeNotContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where liblampe does not contain DEFAULT_LIBLAMPE
        defaultStreetlampShouldNotBeFound("liblampe.doesNotContain=" + DEFAULT_LIBLAMPE);

        // Get all the streetlampList where liblampe does not contain UPDATED_LIBLAMPE
        defaultStreetlampShouldBeFound("liblampe.doesNotContain=" + UPDATED_LIBLAMPE);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByPwlampeIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pwlampe equals to DEFAULT_PWLAMPE
        defaultStreetlampShouldBeFound("pwlampe.equals=" + DEFAULT_PWLAMPE);

        // Get all the streetlampList where pwlampe equals to UPDATED_PWLAMPE
        defaultStreetlampShouldNotBeFound("pwlampe.equals=" + UPDATED_PWLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPwlampeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pwlampe not equals to DEFAULT_PWLAMPE
        defaultStreetlampShouldNotBeFound("pwlampe.notEquals=" + DEFAULT_PWLAMPE);

        // Get all the streetlampList where pwlampe not equals to UPDATED_PWLAMPE
        defaultStreetlampShouldBeFound("pwlampe.notEquals=" + UPDATED_PWLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPwlampeIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pwlampe in DEFAULT_PWLAMPE or UPDATED_PWLAMPE
        defaultStreetlampShouldBeFound("pwlampe.in=" + DEFAULT_PWLAMPE + "," + UPDATED_PWLAMPE);

        // Get all the streetlampList where pwlampe equals to UPDATED_PWLAMPE
        defaultStreetlampShouldNotBeFound("pwlampe.in=" + UPDATED_PWLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPwlampeIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pwlampe is not null
        defaultStreetlampShouldBeFound("pwlampe.specified=true");

        // Get all the streetlampList where pwlampe is null
        defaultStreetlampShouldNotBeFound("pwlampe.specified=false");
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPwlampeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pwlampe is greater than or equal to DEFAULT_PWLAMPE
        defaultStreetlampShouldBeFound("pwlampe.greaterThanOrEqual=" + DEFAULT_PWLAMPE);

        // Get all the streetlampList where pwlampe is greater than or equal to UPDATED_PWLAMPE
        defaultStreetlampShouldNotBeFound("pwlampe.greaterThanOrEqual=" + UPDATED_PWLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPwlampeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pwlampe is less than or equal to DEFAULT_PWLAMPE
        defaultStreetlampShouldBeFound("pwlampe.lessThanOrEqual=" + DEFAULT_PWLAMPE);

        // Get all the streetlampList where pwlampe is less than or equal to SMALLER_PWLAMPE
        defaultStreetlampShouldNotBeFound("pwlampe.lessThanOrEqual=" + SMALLER_PWLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPwlampeIsLessThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pwlampe is less than DEFAULT_PWLAMPE
        defaultStreetlampShouldNotBeFound("pwlampe.lessThan=" + DEFAULT_PWLAMPE);

        // Get all the streetlampList where pwlampe is less than UPDATED_PWLAMPE
        defaultStreetlampShouldBeFound("pwlampe.lessThan=" + UPDATED_PWLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPwlampeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pwlampe is greater than DEFAULT_PWLAMPE
        defaultStreetlampShouldNotBeFound("pwlampe.greaterThan=" + DEFAULT_PWLAMPE);

        // Get all the streetlampList where pwlampe is greater than SMALLER_PWLAMPE
        defaultStreetlampShouldBeFound("pwlampe.greaterThan=" + SMALLER_PWLAMPE);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByFormelampeIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where formelampe equals to DEFAULT_FORMELAMPE
        defaultStreetlampShouldBeFound("formelampe.equals=" + DEFAULT_FORMELAMPE);

        // Get all the streetlampList where formelampe equals to UPDATED_FORMELAMPE
        defaultStreetlampShouldNotBeFound("formelampe.equals=" + UPDATED_FORMELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByFormelampeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where formelampe not equals to DEFAULT_FORMELAMPE
        defaultStreetlampShouldNotBeFound("formelampe.notEquals=" + DEFAULT_FORMELAMPE);

        // Get all the streetlampList where formelampe not equals to UPDATED_FORMELAMPE
        defaultStreetlampShouldBeFound("formelampe.notEquals=" + UPDATED_FORMELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByFormelampeIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where formelampe in DEFAULT_FORMELAMPE or UPDATED_FORMELAMPE
        defaultStreetlampShouldBeFound("formelampe.in=" + DEFAULT_FORMELAMPE + "," + UPDATED_FORMELAMPE);

        // Get all the streetlampList where formelampe equals to UPDATED_FORMELAMPE
        defaultStreetlampShouldNotBeFound("formelampe.in=" + UPDATED_FORMELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByFormelampeIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where formelampe is not null
        defaultStreetlampShouldBeFound("formelampe.specified=true");

        // Get all the streetlampList where formelampe is null
        defaultStreetlampShouldNotBeFound("formelampe.specified=false");
    }
                @Test
    @Transactional
    public void getAllStreetlampsByFormelampeContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where formelampe contains DEFAULT_FORMELAMPE
        defaultStreetlampShouldBeFound("formelampe.contains=" + DEFAULT_FORMELAMPE);

        // Get all the streetlampList where formelampe contains UPDATED_FORMELAMPE
        defaultStreetlampShouldNotBeFound("formelampe.contains=" + UPDATED_FORMELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByFormelampeNotContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where formelampe does not contain DEFAULT_FORMELAMPE
        defaultStreetlampShouldNotBeFound("formelampe.doesNotContain=" + DEFAULT_FORMELAMPE);

        // Get all the streetlampList where formelampe does not contain UPDATED_FORMELAMPE
        defaultStreetlampShouldBeFound("formelampe.doesNotContain=" + UPDATED_FORMELAMPE);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByModelelampeIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelelampe equals to DEFAULT_MODELELAMPE
        defaultStreetlampShouldBeFound("modelelampe.equals=" + DEFAULT_MODELELAMPE);

        // Get all the streetlampList where modelelampe equals to UPDATED_MODELELAMPE
        defaultStreetlampShouldNotBeFound("modelelampe.equals=" + UPDATED_MODELELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByModelelampeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelelampe not equals to DEFAULT_MODELELAMPE
        defaultStreetlampShouldNotBeFound("modelelampe.notEquals=" + DEFAULT_MODELELAMPE);

        // Get all the streetlampList where modelelampe not equals to UPDATED_MODELELAMPE
        defaultStreetlampShouldBeFound("modelelampe.notEquals=" + UPDATED_MODELELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByModelelampeIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelelampe in DEFAULT_MODELELAMPE or UPDATED_MODELELAMPE
        defaultStreetlampShouldBeFound("modelelampe.in=" + DEFAULT_MODELELAMPE + "," + UPDATED_MODELELAMPE);

        // Get all the streetlampList where modelelampe equals to UPDATED_MODELELAMPE
        defaultStreetlampShouldNotBeFound("modelelampe.in=" + UPDATED_MODELELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByModelelampeIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelelampe is not null
        defaultStreetlampShouldBeFound("modelelampe.specified=true");

        // Get all the streetlampList where modelelampe is null
        defaultStreetlampShouldNotBeFound("modelelampe.specified=false");
    }
                @Test
    @Transactional
    public void getAllStreetlampsByModelelampeContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelelampe contains DEFAULT_MODELELAMPE
        defaultStreetlampShouldBeFound("modelelampe.contains=" + DEFAULT_MODELELAMPE);

        // Get all the streetlampList where modelelampe contains UPDATED_MODELELAMPE
        defaultStreetlampShouldNotBeFound("modelelampe.contains=" + UPDATED_MODELELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByModelelampeNotContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where modelelampe does not contain DEFAULT_MODELELAMPE
        defaultStreetlampShouldNotBeFound("modelelampe.doesNotContain=" + DEFAULT_MODELELAMPE);

        // Get all the streetlampList where modelelampe does not contain UPDATED_MODELELAMPE
        defaultStreetlampShouldBeFound("modelelampe.doesNotContain=" + UPDATED_MODELELAMPE);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByDureevielampeIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureevielampe equals to DEFAULT_DUREEVIELAMPE
        defaultStreetlampShouldBeFound("dureevielampe.equals=" + DEFAULT_DUREEVIELAMPE);

        // Get all the streetlampList where dureevielampe equals to UPDATED_DUREEVIELAMPE
        defaultStreetlampShouldNotBeFound("dureevielampe.equals=" + UPDATED_DUREEVIELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureevielampeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureevielampe not equals to DEFAULT_DUREEVIELAMPE
        defaultStreetlampShouldNotBeFound("dureevielampe.notEquals=" + DEFAULT_DUREEVIELAMPE);

        // Get all the streetlampList where dureevielampe not equals to UPDATED_DUREEVIELAMPE
        defaultStreetlampShouldBeFound("dureevielampe.notEquals=" + UPDATED_DUREEVIELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureevielampeIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureevielampe in DEFAULT_DUREEVIELAMPE or UPDATED_DUREEVIELAMPE
        defaultStreetlampShouldBeFound("dureevielampe.in=" + DEFAULT_DUREEVIELAMPE + "," + UPDATED_DUREEVIELAMPE);

        // Get all the streetlampList where dureevielampe equals to UPDATED_DUREEVIELAMPE
        defaultStreetlampShouldNotBeFound("dureevielampe.in=" + UPDATED_DUREEVIELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureevielampeIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureevielampe is not null
        defaultStreetlampShouldBeFound("dureevielampe.specified=true");

        // Get all the streetlampList where dureevielampe is null
        defaultStreetlampShouldNotBeFound("dureevielampe.specified=false");
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureevielampeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureevielampe is greater than or equal to DEFAULT_DUREEVIELAMPE
        defaultStreetlampShouldBeFound("dureevielampe.greaterThanOrEqual=" + DEFAULT_DUREEVIELAMPE);

        // Get all the streetlampList where dureevielampe is greater than or equal to UPDATED_DUREEVIELAMPE
        defaultStreetlampShouldNotBeFound("dureevielampe.greaterThanOrEqual=" + UPDATED_DUREEVIELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureevielampeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureevielampe is less than or equal to DEFAULT_DUREEVIELAMPE
        defaultStreetlampShouldBeFound("dureevielampe.lessThanOrEqual=" + DEFAULT_DUREEVIELAMPE);

        // Get all the streetlampList where dureevielampe is less than or equal to SMALLER_DUREEVIELAMPE
        defaultStreetlampShouldNotBeFound("dureevielampe.lessThanOrEqual=" + SMALLER_DUREEVIELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureevielampeIsLessThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureevielampe is less than DEFAULT_DUREEVIELAMPE
        defaultStreetlampShouldNotBeFound("dureevielampe.lessThan=" + DEFAULT_DUREEVIELAMPE);

        // Get all the streetlampList where dureevielampe is less than UPDATED_DUREEVIELAMPE
        defaultStreetlampShouldBeFound("dureevielampe.lessThan=" + UPDATED_DUREEVIELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByDureevielampeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where dureevielampe is greater than DEFAULT_DUREEVIELAMPE
        defaultStreetlampShouldNotBeFound("dureevielampe.greaterThan=" + DEFAULT_DUREEVIELAMPE);

        // Get all the streetlampList where dureevielampe is greater than SMALLER_DUREEVIELAMPE
        defaultStreetlampShouldBeFound("dureevielampe.greaterThan=" + SMALLER_DUREEVIELAMPE);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByUnitevielampeIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where unitevielampe equals to DEFAULT_UNITEVIELAMPE
        defaultStreetlampShouldBeFound("unitevielampe.equals=" + DEFAULT_UNITEVIELAMPE);

        // Get all the streetlampList where unitevielampe equals to UPDATED_UNITEVIELAMPE
        defaultStreetlampShouldNotBeFound("unitevielampe.equals=" + UPDATED_UNITEVIELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByUnitevielampeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where unitevielampe not equals to DEFAULT_UNITEVIELAMPE
        defaultStreetlampShouldNotBeFound("unitevielampe.notEquals=" + DEFAULT_UNITEVIELAMPE);

        // Get all the streetlampList where unitevielampe not equals to UPDATED_UNITEVIELAMPE
        defaultStreetlampShouldBeFound("unitevielampe.notEquals=" + UPDATED_UNITEVIELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByUnitevielampeIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where unitevielampe in DEFAULT_UNITEVIELAMPE or UPDATED_UNITEVIELAMPE
        defaultStreetlampShouldBeFound("unitevielampe.in=" + DEFAULT_UNITEVIELAMPE + "," + UPDATED_UNITEVIELAMPE);

        // Get all the streetlampList where unitevielampe equals to UPDATED_UNITEVIELAMPE
        defaultStreetlampShouldNotBeFound("unitevielampe.in=" + UPDATED_UNITEVIELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByUnitevielampeIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where unitevielampe is not null
        defaultStreetlampShouldBeFound("unitevielampe.specified=true");

        // Get all the streetlampList where unitevielampe is null
        defaultStreetlampShouldNotBeFound("unitevielampe.specified=false");
    }
                @Test
    @Transactional
    public void getAllStreetlampsByUnitevielampeContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where unitevielampe contains DEFAULT_UNITEVIELAMPE
        defaultStreetlampShouldBeFound("unitevielampe.contains=" + DEFAULT_UNITEVIELAMPE);

        // Get all the streetlampList where unitevielampe contains UPDATED_UNITEVIELAMPE
        defaultStreetlampShouldNotBeFound("unitevielampe.contains=" + UPDATED_UNITEVIELAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByUnitevielampeNotContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where unitevielampe does not contain DEFAULT_UNITEVIELAMPE
        defaultStreetlampShouldNotBeFound("unitevielampe.doesNotContain=" + DEFAULT_UNITEVIELAMPE);

        // Get all the streetlampList where unitevielampe does not contain UPDATED_UNITEVIELAMPE
        defaultStreetlampShouldBeFound("unitevielampe.doesNotContain=" + UPDATED_UNITEVIELAMPE);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByVoltlampeIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where voltlampe equals to DEFAULT_VOLTLAMPE
        defaultStreetlampShouldBeFound("voltlampe.equals=" + DEFAULT_VOLTLAMPE);

        // Get all the streetlampList where voltlampe equals to UPDATED_VOLTLAMPE
        defaultStreetlampShouldNotBeFound("voltlampe.equals=" + UPDATED_VOLTLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByVoltlampeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where voltlampe not equals to DEFAULT_VOLTLAMPE
        defaultStreetlampShouldNotBeFound("voltlampe.notEquals=" + DEFAULT_VOLTLAMPE);

        // Get all the streetlampList where voltlampe not equals to UPDATED_VOLTLAMPE
        defaultStreetlampShouldBeFound("voltlampe.notEquals=" + UPDATED_VOLTLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByVoltlampeIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where voltlampe in DEFAULT_VOLTLAMPE or UPDATED_VOLTLAMPE
        defaultStreetlampShouldBeFound("voltlampe.in=" + DEFAULT_VOLTLAMPE + "," + UPDATED_VOLTLAMPE);

        // Get all the streetlampList where voltlampe equals to UPDATED_VOLTLAMPE
        defaultStreetlampShouldNotBeFound("voltlampe.in=" + UPDATED_VOLTLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByVoltlampeIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where voltlampe is not null
        defaultStreetlampShouldBeFound("voltlampe.specified=true");

        // Get all the streetlampList where voltlampe is null
        defaultStreetlampShouldNotBeFound("voltlampe.specified=false");
    }

    @Test
    @Transactional
    public void getAllStreetlampsByVoltlampeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where voltlampe is greater than or equal to DEFAULT_VOLTLAMPE
        defaultStreetlampShouldBeFound("voltlampe.greaterThanOrEqual=" + DEFAULT_VOLTLAMPE);

        // Get all the streetlampList where voltlampe is greater than or equal to UPDATED_VOLTLAMPE
        defaultStreetlampShouldNotBeFound("voltlampe.greaterThanOrEqual=" + UPDATED_VOLTLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByVoltlampeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where voltlampe is less than or equal to DEFAULT_VOLTLAMPE
        defaultStreetlampShouldBeFound("voltlampe.lessThanOrEqual=" + DEFAULT_VOLTLAMPE);

        // Get all the streetlampList where voltlampe is less than or equal to SMALLER_VOLTLAMPE
        defaultStreetlampShouldNotBeFound("voltlampe.lessThanOrEqual=" + SMALLER_VOLTLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByVoltlampeIsLessThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where voltlampe is less than DEFAULT_VOLTLAMPE
        defaultStreetlampShouldNotBeFound("voltlampe.lessThan=" + DEFAULT_VOLTLAMPE);

        // Get all the streetlampList where voltlampe is less than UPDATED_VOLTLAMPE
        defaultStreetlampShouldBeFound("voltlampe.lessThan=" + UPDATED_VOLTLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByVoltlampeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where voltlampe is greater than DEFAULT_VOLTLAMPE
        defaultStreetlampShouldNotBeFound("voltlampe.greaterThan=" + DEFAULT_VOLTLAMPE);

        // Get all the streetlampList where voltlampe is greater than SMALLER_VOLTLAMPE
        defaultStreetlampShouldBeFound("voltlampe.greaterThan=" + SMALLER_VOLTLAMPE);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByTemplampeIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where templampe equals to DEFAULT_TEMPLAMPE
        defaultStreetlampShouldBeFound("templampe.equals=" + DEFAULT_TEMPLAMPE);

        // Get all the streetlampList where templampe equals to UPDATED_TEMPLAMPE
        defaultStreetlampShouldNotBeFound("templampe.equals=" + UPDATED_TEMPLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByTemplampeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where templampe not equals to DEFAULT_TEMPLAMPE
        defaultStreetlampShouldNotBeFound("templampe.notEquals=" + DEFAULT_TEMPLAMPE);

        // Get all the streetlampList where templampe not equals to UPDATED_TEMPLAMPE
        defaultStreetlampShouldBeFound("templampe.notEquals=" + UPDATED_TEMPLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByTemplampeIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where templampe in DEFAULT_TEMPLAMPE or UPDATED_TEMPLAMPE
        defaultStreetlampShouldBeFound("templampe.in=" + DEFAULT_TEMPLAMPE + "," + UPDATED_TEMPLAMPE);

        // Get all the streetlampList where templampe equals to UPDATED_TEMPLAMPE
        defaultStreetlampShouldNotBeFound("templampe.in=" + UPDATED_TEMPLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByTemplampeIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where templampe is not null
        defaultStreetlampShouldBeFound("templampe.specified=true");

        // Get all the streetlampList where templampe is null
        defaultStreetlampShouldNotBeFound("templampe.specified=false");
    }

    @Test
    @Transactional
    public void getAllStreetlampsByTemplampeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where templampe is greater than or equal to DEFAULT_TEMPLAMPE
        defaultStreetlampShouldBeFound("templampe.greaterThanOrEqual=" + DEFAULT_TEMPLAMPE);

        // Get all the streetlampList where templampe is greater than or equal to UPDATED_TEMPLAMPE
        defaultStreetlampShouldNotBeFound("templampe.greaterThanOrEqual=" + UPDATED_TEMPLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByTemplampeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where templampe is less than or equal to DEFAULT_TEMPLAMPE
        defaultStreetlampShouldBeFound("templampe.lessThanOrEqual=" + DEFAULT_TEMPLAMPE);

        // Get all the streetlampList where templampe is less than or equal to SMALLER_TEMPLAMPE
        defaultStreetlampShouldNotBeFound("templampe.lessThanOrEqual=" + SMALLER_TEMPLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByTemplampeIsLessThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where templampe is less than DEFAULT_TEMPLAMPE
        defaultStreetlampShouldNotBeFound("templampe.lessThan=" + DEFAULT_TEMPLAMPE);

        // Get all the streetlampList where templampe is less than UPDATED_TEMPLAMPE
        defaultStreetlampShouldBeFound("templampe.lessThan=" + UPDATED_TEMPLAMPE);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByTemplampeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where templampe is greater than DEFAULT_TEMPLAMPE
        defaultStreetlampShouldNotBeFound("templampe.greaterThan=" + DEFAULT_TEMPLAMPE);

        // Get all the streetlampList where templampe is greater than SMALLER_TEMPLAMPE
        defaultStreetlampShouldBeFound("templampe.greaterThan=" + SMALLER_TEMPLAMPE);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByImagepathstreetlampIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where imagepathstreetlamp equals to DEFAULT_IMAGEPATHSTREETLAMP
        defaultStreetlampShouldBeFound("imagepathstreetlamp.equals=" + DEFAULT_IMAGEPATHSTREETLAMP);

        // Get all the streetlampList where imagepathstreetlamp equals to UPDATED_IMAGEPATHSTREETLAMP
        defaultStreetlampShouldNotBeFound("imagepathstreetlamp.equals=" + UPDATED_IMAGEPATHSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByImagepathstreetlampIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where imagepathstreetlamp not equals to DEFAULT_IMAGEPATHSTREETLAMP
        defaultStreetlampShouldNotBeFound("imagepathstreetlamp.notEquals=" + DEFAULT_IMAGEPATHSTREETLAMP);

        // Get all the streetlampList where imagepathstreetlamp not equals to UPDATED_IMAGEPATHSTREETLAMP
        defaultStreetlampShouldBeFound("imagepathstreetlamp.notEquals=" + UPDATED_IMAGEPATHSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByImagepathstreetlampIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where imagepathstreetlamp in DEFAULT_IMAGEPATHSTREETLAMP or UPDATED_IMAGEPATHSTREETLAMP
        defaultStreetlampShouldBeFound("imagepathstreetlamp.in=" + DEFAULT_IMAGEPATHSTREETLAMP + "," + UPDATED_IMAGEPATHSTREETLAMP);

        // Get all the streetlampList where imagepathstreetlamp equals to UPDATED_IMAGEPATHSTREETLAMP
        defaultStreetlampShouldNotBeFound("imagepathstreetlamp.in=" + UPDATED_IMAGEPATHSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByImagepathstreetlampIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where imagepathstreetlamp is not null
        defaultStreetlampShouldBeFound("imagepathstreetlamp.specified=true");

        // Get all the streetlampList where imagepathstreetlamp is null
        defaultStreetlampShouldNotBeFound("imagepathstreetlamp.specified=false");
    }
                @Test
    @Transactional
    public void getAllStreetlampsByImagepathstreetlampContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where imagepathstreetlamp contains DEFAULT_IMAGEPATHSTREETLAMP
        defaultStreetlampShouldBeFound("imagepathstreetlamp.contains=" + DEFAULT_IMAGEPATHSTREETLAMP);

        // Get all the streetlampList where imagepathstreetlamp contains UPDATED_IMAGEPATHSTREETLAMP
        defaultStreetlampShouldNotBeFound("imagepathstreetlamp.contains=" + UPDATED_IMAGEPATHSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByImagepathstreetlampNotContainsSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where imagepathstreetlamp does not contain DEFAULT_IMAGEPATHSTREETLAMP
        defaultStreetlampShouldNotBeFound("imagepathstreetlamp.doesNotContain=" + DEFAULT_IMAGEPATHSTREETLAMP);

        // Get all the streetlampList where imagepathstreetlamp does not contain UPDATED_IMAGEPATHSTREETLAMP
        defaultStreetlampShouldBeFound("imagepathstreetlamp.doesNotContain=" + UPDATED_IMAGEPATHSTREETLAMP);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByStockstreetlampIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where stockstreetlamp equals to DEFAULT_STOCKSTREETLAMP
        defaultStreetlampShouldBeFound("stockstreetlamp.equals=" + DEFAULT_STOCKSTREETLAMP);

        // Get all the streetlampList where stockstreetlamp equals to UPDATED_STOCKSTREETLAMP
        defaultStreetlampShouldNotBeFound("stockstreetlamp.equals=" + UPDATED_STOCKSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByStockstreetlampIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where stockstreetlamp not equals to DEFAULT_STOCKSTREETLAMP
        defaultStreetlampShouldNotBeFound("stockstreetlamp.notEquals=" + DEFAULT_STOCKSTREETLAMP);

        // Get all the streetlampList where stockstreetlamp not equals to UPDATED_STOCKSTREETLAMP
        defaultStreetlampShouldBeFound("stockstreetlamp.notEquals=" + UPDATED_STOCKSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByStockstreetlampIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where stockstreetlamp in DEFAULT_STOCKSTREETLAMP or UPDATED_STOCKSTREETLAMP
        defaultStreetlampShouldBeFound("stockstreetlamp.in=" + DEFAULT_STOCKSTREETLAMP + "," + UPDATED_STOCKSTREETLAMP);

        // Get all the streetlampList where stockstreetlamp equals to UPDATED_STOCKSTREETLAMP
        defaultStreetlampShouldNotBeFound("stockstreetlamp.in=" + UPDATED_STOCKSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByStockstreetlampIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where stockstreetlamp is not null
        defaultStreetlampShouldBeFound("stockstreetlamp.specified=true");

        // Get all the streetlampList where stockstreetlamp is null
        defaultStreetlampShouldNotBeFound("stockstreetlamp.specified=false");
    }

    @Test
    @Transactional
    public void getAllStreetlampsByStockstreetlampIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where stockstreetlamp is greater than or equal to DEFAULT_STOCKSTREETLAMP
        defaultStreetlampShouldBeFound("stockstreetlamp.greaterThanOrEqual=" + DEFAULT_STOCKSTREETLAMP);

        // Get all the streetlampList where stockstreetlamp is greater than or equal to UPDATED_STOCKSTREETLAMP
        defaultStreetlampShouldNotBeFound("stockstreetlamp.greaterThanOrEqual=" + UPDATED_STOCKSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByStockstreetlampIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where stockstreetlamp is less than or equal to DEFAULT_STOCKSTREETLAMP
        defaultStreetlampShouldBeFound("stockstreetlamp.lessThanOrEqual=" + DEFAULT_STOCKSTREETLAMP);

        // Get all the streetlampList where stockstreetlamp is less than or equal to SMALLER_STOCKSTREETLAMP
        defaultStreetlampShouldNotBeFound("stockstreetlamp.lessThanOrEqual=" + SMALLER_STOCKSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByStockstreetlampIsLessThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where stockstreetlamp is less than DEFAULT_STOCKSTREETLAMP
        defaultStreetlampShouldNotBeFound("stockstreetlamp.lessThan=" + DEFAULT_STOCKSTREETLAMP);

        // Get all the streetlampList where stockstreetlamp is less than UPDATED_STOCKSTREETLAMP
        defaultStreetlampShouldBeFound("stockstreetlamp.lessThan=" + UPDATED_STOCKSTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByStockstreetlampIsGreaterThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where stockstreetlamp is greater than DEFAULT_STOCKSTREETLAMP
        defaultStreetlampShouldNotBeFound("stockstreetlamp.greaterThan=" + DEFAULT_STOCKSTREETLAMP);

        // Get all the streetlampList where stockstreetlamp is greater than SMALLER_STOCKSTREETLAMP
        defaultStreetlampShouldBeFound("stockstreetlamp.greaterThan=" + SMALLER_STOCKSTREETLAMP);
    }


    @Test
    @Transactional
    public void getAllStreetlampsByPricestreetlampIsEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pricestreetlamp equals to DEFAULT_PRICESTREETLAMP
        defaultStreetlampShouldBeFound("pricestreetlamp.equals=" + DEFAULT_PRICESTREETLAMP);

        // Get all the streetlampList where pricestreetlamp equals to UPDATED_PRICESTREETLAMP
        defaultStreetlampShouldNotBeFound("pricestreetlamp.equals=" + UPDATED_PRICESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPricestreetlampIsNotEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pricestreetlamp not equals to DEFAULT_PRICESTREETLAMP
        defaultStreetlampShouldNotBeFound("pricestreetlamp.notEquals=" + DEFAULT_PRICESTREETLAMP);

        // Get all the streetlampList where pricestreetlamp not equals to UPDATED_PRICESTREETLAMP
        defaultStreetlampShouldBeFound("pricestreetlamp.notEquals=" + UPDATED_PRICESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPricestreetlampIsInShouldWork() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pricestreetlamp in DEFAULT_PRICESTREETLAMP or UPDATED_PRICESTREETLAMP
        defaultStreetlampShouldBeFound("pricestreetlamp.in=" + DEFAULT_PRICESTREETLAMP + "," + UPDATED_PRICESTREETLAMP);

        // Get all the streetlampList where pricestreetlamp equals to UPDATED_PRICESTREETLAMP
        defaultStreetlampShouldNotBeFound("pricestreetlamp.in=" + UPDATED_PRICESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPricestreetlampIsNullOrNotNull() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pricestreetlamp is not null
        defaultStreetlampShouldBeFound("pricestreetlamp.specified=true");

        // Get all the streetlampList where pricestreetlamp is null
        defaultStreetlampShouldNotBeFound("pricestreetlamp.specified=false");
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPricestreetlampIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pricestreetlamp is greater than or equal to DEFAULT_PRICESTREETLAMP
        defaultStreetlampShouldBeFound("pricestreetlamp.greaterThanOrEqual=" + DEFAULT_PRICESTREETLAMP);

        // Get all the streetlampList where pricestreetlamp is greater than or equal to UPDATED_PRICESTREETLAMP
        defaultStreetlampShouldNotBeFound("pricestreetlamp.greaterThanOrEqual=" + UPDATED_PRICESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPricestreetlampIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pricestreetlamp is less than or equal to DEFAULT_PRICESTREETLAMP
        defaultStreetlampShouldBeFound("pricestreetlamp.lessThanOrEqual=" + DEFAULT_PRICESTREETLAMP);

        // Get all the streetlampList where pricestreetlamp is less than or equal to SMALLER_PRICESTREETLAMP
        defaultStreetlampShouldNotBeFound("pricestreetlamp.lessThanOrEqual=" + SMALLER_PRICESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPricestreetlampIsLessThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pricestreetlamp is less than DEFAULT_PRICESTREETLAMP
        defaultStreetlampShouldNotBeFound("pricestreetlamp.lessThan=" + DEFAULT_PRICESTREETLAMP);

        // Get all the streetlampList where pricestreetlamp is less than UPDATED_PRICESTREETLAMP
        defaultStreetlampShouldBeFound("pricestreetlamp.lessThan=" + UPDATED_PRICESTREETLAMP);
    }

    @Test
    @Transactional
    public void getAllStreetlampsByPricestreetlampIsGreaterThanSomething() throws Exception {
        // Initialize the database
        streetlampRepository.saveAndFlush(streetlamp);

        // Get all the streetlampList where pricestreetlamp is greater than DEFAULT_PRICESTREETLAMP
        defaultStreetlampShouldNotBeFound("pricestreetlamp.greaterThan=" + DEFAULT_PRICESTREETLAMP);

        // Get all the streetlampList where pricestreetlamp is greater than SMALLER_PRICESTREETLAMP
        defaultStreetlampShouldBeFound("pricestreetlamp.greaterThan=" + SMALLER_PRICESTREETLAMP);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultStreetlampShouldBeFound(String filter) throws Exception {
        restStreetlampMockMvc.perform(get("/api/streetlamps?sort=id,desc&" + filter))
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
            .andExpect(jsonPath("$.[*].imagepathstreetlamp").value(hasItem(DEFAULT_IMAGEPATHSTREETLAMP)))
            .andExpect(jsonPath("$.[*].stockstreetlamp").value(hasItem(DEFAULT_STOCKSTREETLAMP)))
            .andExpect(jsonPath("$.[*].pricestreetlamp").value(hasItem(DEFAULT_PRICESTREETLAMP.doubleValue())));

        // Check, that the count call also returns 1
        restStreetlampMockMvc.perform(get("/api/streetlamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultStreetlampShouldNotBeFound(String filter) throws Exception {
        restStreetlampMockMvc.perform(get("/api/streetlamps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restStreetlampMockMvc.perform(get("/api/streetlamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
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
