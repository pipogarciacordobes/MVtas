package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.MVtasApp;

import io.github.jhipster.application.domain.PV;
import io.github.jhipster.application.repository.PVRepository;
import io.github.jhipster.application.service.PVService;
import io.github.jhipster.application.service.dto.PVDTO;
import io.github.jhipster.application.service.mapper.PVMapper;
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
 * Test class for the PVResource REST controller.
 *
 * @see PVResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MVtasApp.class)
public class PVResourceIntTest {

    private static final String DEFAULT_PV_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_PV_NUMERO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_COMERCIO = 1L;
    private static final Long UPDATED_ID_COMERCIO = 2L;

    @Autowired
    private PVRepository pVRepository;

    @Autowired
    private PVMapper pVMapper;

    @Autowired
    private PVService pVService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPVMockMvc;

    private PV pV;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PVResource pVResource = new PVResource(pVService);
        this.restPVMockMvc = MockMvcBuilders.standaloneSetup(pVResource)
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
    public static PV createEntity(EntityManager em) {
        PV pV = new PV()
            .pvNumero(DEFAULT_PV_NUMERO)
            .idComercio(DEFAULT_ID_COMERCIO);
        return pV;
    }

    @Before
    public void initTest() {
        pV = createEntity(em);
    }

    @Test
    @Transactional
    public void createPV() throws Exception {
        int databaseSizeBeforeCreate = pVRepository.findAll().size();

        // Create the PV
        PVDTO pVDTO = pVMapper.toDto(pV);
        restPVMockMvc.perform(post("/api/pvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pVDTO)))
            .andExpect(status().isCreated());

        // Validate the PV in the database
        List<PV> pVList = pVRepository.findAll();
        assertThat(pVList).hasSize(databaseSizeBeforeCreate + 1);
        PV testPV = pVList.get(pVList.size() - 1);
        assertThat(testPV.getPvNumero()).isEqualTo(DEFAULT_PV_NUMERO);
        assertThat(testPV.getIdComercio()).isEqualTo(DEFAULT_ID_COMERCIO);
    }

    @Test
    @Transactional
    public void createPVWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pVRepository.findAll().size();

        // Create the PV with an existing ID
        pV.setId(1L);
        PVDTO pVDTO = pVMapper.toDto(pV);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPVMockMvc.perform(post("/api/pvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pVDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PV in the database
        List<PV> pVList = pVRepository.findAll();
        assertThat(pVList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPVS() throws Exception {
        // Initialize the database
        pVRepository.saveAndFlush(pV);

        // Get all the pVList
        restPVMockMvc.perform(get("/api/pvs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pV.getId().intValue())))
            .andExpect(jsonPath("$.[*].pvNumero").value(hasItem(DEFAULT_PV_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].idComercio").value(hasItem(DEFAULT_ID_COMERCIO.intValue())));
    }

    @Test
    @Transactional
    public void getPV() throws Exception {
        // Initialize the database
        pVRepository.saveAndFlush(pV);

        // Get the pV
        restPVMockMvc.perform(get("/api/pvs/{id}", pV.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pV.getId().intValue()))
            .andExpect(jsonPath("$.pvNumero").value(DEFAULT_PV_NUMERO.toString()))
            .andExpect(jsonPath("$.idComercio").value(DEFAULT_ID_COMERCIO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPV() throws Exception {
        // Get the pV
        restPVMockMvc.perform(get("/api/pvs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePV() throws Exception {
        // Initialize the database
        pVRepository.saveAndFlush(pV);
        int databaseSizeBeforeUpdate = pVRepository.findAll().size();

        // Update the pV
        PV updatedPV = pVRepository.findOne(pV.getId());
        // Disconnect from session so that the updates on updatedPV are not directly saved in db
        em.detach(updatedPV);
        updatedPV
            .pvNumero(UPDATED_PV_NUMERO)
            .idComercio(UPDATED_ID_COMERCIO);
        PVDTO pVDTO = pVMapper.toDto(updatedPV);

        restPVMockMvc.perform(put("/api/pvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pVDTO)))
            .andExpect(status().isOk());

        // Validate the PV in the database
        List<PV> pVList = pVRepository.findAll();
        assertThat(pVList).hasSize(databaseSizeBeforeUpdate);
        PV testPV = pVList.get(pVList.size() - 1);
        assertThat(testPV.getPvNumero()).isEqualTo(UPDATED_PV_NUMERO);
        assertThat(testPV.getIdComercio()).isEqualTo(UPDATED_ID_COMERCIO);
    }

    @Test
    @Transactional
    public void updateNonExistingPV() throws Exception {
        int databaseSizeBeforeUpdate = pVRepository.findAll().size();

        // Create the PV
        PVDTO pVDTO = pVMapper.toDto(pV);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPVMockMvc.perform(put("/api/pvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pVDTO)))
            .andExpect(status().isCreated());

        // Validate the PV in the database
        List<PV> pVList = pVRepository.findAll();
        assertThat(pVList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePV() throws Exception {
        // Initialize the database
        pVRepository.saveAndFlush(pV);
        int databaseSizeBeforeDelete = pVRepository.findAll().size();

        // Get the pV
        restPVMockMvc.perform(delete("/api/pvs/{id}", pV.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PV> pVList = pVRepository.findAll();
        assertThat(pVList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PV.class);
        PV pV1 = new PV();
        pV1.setId(1L);
        PV pV2 = new PV();
        pV2.setId(pV1.getId());
        assertThat(pV1).isEqualTo(pV2);
        pV2.setId(2L);
        assertThat(pV1).isNotEqualTo(pV2);
        pV1.setId(null);
        assertThat(pV1).isNotEqualTo(pV2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PVDTO.class);
        PVDTO pVDTO1 = new PVDTO();
        pVDTO1.setId(1L);
        PVDTO pVDTO2 = new PVDTO();
        assertThat(pVDTO1).isNotEqualTo(pVDTO2);
        pVDTO2.setId(pVDTO1.getId());
        assertThat(pVDTO1).isEqualTo(pVDTO2);
        pVDTO2.setId(2L);
        assertThat(pVDTO1).isNotEqualTo(pVDTO2);
        pVDTO1.setId(null);
        assertThat(pVDTO1).isNotEqualTo(pVDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pVMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pVMapper.fromId(null)).isNull();
    }
}
