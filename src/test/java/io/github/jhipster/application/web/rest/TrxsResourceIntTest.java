package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.MVtasApp;

import io.github.jhipster.application.domain.Trxs;
import io.github.jhipster.application.repository.TrxsRepository;
import io.github.jhipster.application.service.TrxsService;
import io.github.jhipster.application.service.dto.TrxsDTO;
import io.github.jhipster.application.service.mapper.TrxsMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TrxsResource REST controller.
 *
 * @see TrxsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MVtasApp.class)
public class TrxsResourceIntTest {

    private static final String DEFAULT_PV_DATA = "AAAAAAAAAA";
    private static final String UPDATED_PV_DATA = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_PV = 1L;
    private static final Long UPDATED_ID_PV = 2L;

    @Autowired
    private TrxsRepository trxsRepository;

    @Autowired
    private TrxsMapper trxsMapper;

    @Autowired
    private TrxsService trxsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrxsMockMvc;

    private Trxs trxs;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrxsResource trxsResource = new TrxsResource(trxsService);
        this.restTrxsMockMvc = MockMvcBuilders.standaloneSetup(trxsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Trxs createEntity(EntityManager em) {
        Trxs trxs = new Trxs()
            .pvData(DEFAULT_PV_DATA)
            .idPV(DEFAULT_ID_PV);
        return trxs;
    }

    @Before
    public void initTest() {
        trxs = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrxs() throws Exception {
        int databaseSizeBeforeCreate = trxsRepository.findAll().size();

        // Create the Trxs
        TrxsDTO trxsDTO = trxsMapper.toDto(trxs);
        restTrxsMockMvc.perform(post("/api/trxs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trxsDTO)))
            .andExpect(status().isCreated());

        // Validate the Trxs in the database
        List<Trxs> trxsList = trxsRepository.findAll();
        assertThat(trxsList).hasSize(databaseSizeBeforeCreate + 1);
        Trxs testTrxs = trxsList.get(trxsList.size() - 1);
        assertThat(testTrxs.getPvData()).isEqualTo(DEFAULT_PV_DATA);
        assertThat(testTrxs.getIdPV()).isEqualTo(DEFAULT_ID_PV);
    }

    @Test
    @Transactional
    public void createTrxsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trxsRepository.findAll().size();

        // Create the Trxs with an existing ID
        trxs.setId(1L);
        TrxsDTO trxsDTO = trxsMapper.toDto(trxs);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrxsMockMvc.perform(post("/api/trxs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trxsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trxs in the database
        List<Trxs> trxsList = trxsRepository.findAll();
        assertThat(trxsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTrxs() throws Exception {
        // Initialize the database
        trxsRepository.saveAndFlush(trxs);

        // Get all the trxsList
        restTrxsMockMvc.perform(get("/api/trxs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trxs.getId().intValue())))
            .andExpect(jsonPath("$.[*].pvData").value(hasItem(DEFAULT_PV_DATA.toString())))
            .andExpect(jsonPath("$.[*].idPV").value(hasItem(DEFAULT_ID_PV.intValue())));
    }

    @Test
    @Transactional
    public void getTrxs() throws Exception {
        // Initialize the database
        trxsRepository.saveAndFlush(trxs);

        // Get the trxs
        restTrxsMockMvc.perform(get("/api/trxs/{id}", trxs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trxs.getId().intValue()))
            .andExpect(jsonPath("$.pvData").value(DEFAULT_PV_DATA.toString()))
            .andExpect(jsonPath("$.idPV").value(DEFAULT_ID_PV.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTrxs() throws Exception {
        // Get the trxs
        restTrxsMockMvc.perform(get("/api/trxs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrxs() throws Exception {
        // Initialize the database
        trxsRepository.saveAndFlush(trxs);
        int databaseSizeBeforeUpdate = trxsRepository.findAll().size();

        // Update the trxs
        Trxs updatedTrxs = trxsRepository.findOne(trxs.getId());
        // Disconnect from session so that the updates on updatedTrxs are not directly saved in db
        em.detach(updatedTrxs);
        updatedTrxs
            .pvData(UPDATED_PV_DATA)
            .idPV(UPDATED_ID_PV);
        TrxsDTO trxsDTO = trxsMapper.toDto(updatedTrxs);

        restTrxsMockMvc.perform(put("/api/trxs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trxsDTO)))
            .andExpect(status().isOk());

        // Validate the Trxs in the database
        List<Trxs> trxsList = trxsRepository.findAll();
        assertThat(trxsList).hasSize(databaseSizeBeforeUpdate);
        Trxs testTrxs = trxsList.get(trxsList.size() - 1);
        assertThat(testTrxs.getPvData()).isEqualTo(UPDATED_PV_DATA);
        assertThat(testTrxs.getIdPV()).isEqualTo(UPDATED_ID_PV);
    }

    @Test
    @Transactional
    public void updateNonExistingTrxs() throws Exception {
        int databaseSizeBeforeUpdate = trxsRepository.findAll().size();

        // Create the Trxs
        TrxsDTO trxsDTO = trxsMapper.toDto(trxs);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTrxsMockMvc.perform(put("/api/trxs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trxsDTO)))
            .andExpect(status().isCreated());

        // Validate the Trxs in the database
        List<Trxs> trxsList = trxsRepository.findAll();
        assertThat(trxsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTrxs() throws Exception {
        // Initialize the database
        trxsRepository.saveAndFlush(trxs);
        int databaseSizeBeforeDelete = trxsRepository.findAll().size();

        // Get the trxs
        restTrxsMockMvc.perform(delete("/api/trxs/{id}", trxs.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Trxs> trxsList = trxsRepository.findAll();
        assertThat(trxsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Trxs.class);
        Trxs trxs1 = new Trxs();
        trxs1.setId(1L);
        Trxs trxs2 = new Trxs();
        trxs2.setId(trxs1.getId());
        assertThat(trxs1).isEqualTo(trxs2);
        trxs2.setId(2L);
        assertThat(trxs1).isNotEqualTo(trxs2);
        trxs1.setId(null);
        assertThat(trxs1).isNotEqualTo(trxs2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrxsDTO.class);
        TrxsDTO trxsDTO1 = new TrxsDTO();
        trxsDTO1.setId(1L);
        TrxsDTO trxsDTO2 = new TrxsDTO();
        assertThat(trxsDTO1).isNotEqualTo(trxsDTO2);
        trxsDTO2.setId(trxsDTO1.getId());
        assertThat(trxsDTO1).isEqualTo(trxsDTO2);
        trxsDTO2.setId(2L);
        assertThat(trxsDTO1).isNotEqualTo(trxsDTO2);
        trxsDTO1.setId(null);
        assertThat(trxsDTO1).isNotEqualTo(trxsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(trxsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(trxsMapper.fromId(null)).isNull();
    }
}
