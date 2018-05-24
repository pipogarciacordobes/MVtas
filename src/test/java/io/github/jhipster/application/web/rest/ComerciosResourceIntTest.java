package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.MVtasApp;

import io.github.jhipster.application.domain.Comercios;
import io.github.jhipster.application.repository.ComerciosRepository;
import io.github.jhipster.application.service.ComerciosService;
import io.github.jhipster.application.service.dto.ComerciosDTO;
import io.github.jhipster.application.service.mapper.ComerciosMapper;
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
 * Test class for the ComerciosResource REST controller.
 *
 * @see ComerciosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MVtasApp.class)
public class ComerciosResourceIntTest {

    private static final String DEFAULT_COMERCIOS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMERCIOS_NAME = "BBBBBBBBBB";

    @Autowired
    private ComerciosRepository comerciosRepository;

    @Autowired
    private ComerciosMapper comerciosMapper;

    @Autowired
    private ComerciosService comerciosService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restComerciosMockMvc;

    private Comercios comercios;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComerciosResource comerciosResource = new ComerciosResource(comerciosService);
        this.restComerciosMockMvc = MockMvcBuilders.standaloneSetup(comerciosResource)
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
    public static Comercios createEntity(EntityManager em) {
        Comercios comercios = new Comercios()
            .comerciosName(DEFAULT_COMERCIOS_NAME);
        return comercios;
    }

    @Before
    public void initTest() {
        comercios = createEntity(em);
    }

    @Test
    @Transactional
    public void createComercios() throws Exception {
        int databaseSizeBeforeCreate = comerciosRepository.findAll().size();

        // Create the Comercios
        ComerciosDTO comerciosDTO = comerciosMapper.toDto(comercios);
        restComerciosMockMvc.perform(post("/api/comercios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comerciosDTO)))
            .andExpect(status().isCreated());

        // Validate the Comercios in the database
        List<Comercios> comerciosList = comerciosRepository.findAll();
        assertThat(comerciosList).hasSize(databaseSizeBeforeCreate + 1);
        Comercios testComercios = comerciosList.get(comerciosList.size() - 1);
        assertThat(testComercios.getComerciosName()).isEqualTo(DEFAULT_COMERCIOS_NAME);
    }

    @Test
    @Transactional
    public void createComerciosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = comerciosRepository.findAll().size();

        // Create the Comercios with an existing ID
        comercios.setId(1L);
        ComerciosDTO comerciosDTO = comerciosMapper.toDto(comercios);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComerciosMockMvc.perform(post("/api/comercios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comerciosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comercios in the database
        List<Comercios> comerciosList = comerciosRepository.findAll();
        assertThat(comerciosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllComercios() throws Exception {
        // Initialize the database
        comerciosRepository.saveAndFlush(comercios);

        // Get all the comerciosList
        restComerciosMockMvc.perform(get("/api/comercios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comercios.getId().intValue())))
            .andExpect(jsonPath("$.[*].comerciosName").value(hasItem(DEFAULT_COMERCIOS_NAME.toString())));
    }

    @Test
    @Transactional
    public void getComercios() throws Exception {
        // Initialize the database
        comerciosRepository.saveAndFlush(comercios);

        // Get the comercios
        restComerciosMockMvc.perform(get("/api/comercios/{id}", comercios.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(comercios.getId().intValue()))
            .andExpect(jsonPath("$.comerciosName").value(DEFAULT_COMERCIOS_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingComercios() throws Exception {
        // Get the comercios
        restComerciosMockMvc.perform(get("/api/comercios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComercios() throws Exception {
        // Initialize the database
        comerciosRepository.saveAndFlush(comercios);
        int databaseSizeBeforeUpdate = comerciosRepository.findAll().size();

        // Update the comercios
        Comercios updatedComercios = comerciosRepository.findOne(comercios.getId());
        // Disconnect from session so that the updates on updatedComercios are not directly saved in db
        em.detach(updatedComercios);
        updatedComercios
            .comerciosName(UPDATED_COMERCIOS_NAME);
        ComerciosDTO comerciosDTO = comerciosMapper.toDto(updatedComercios);

        restComerciosMockMvc.perform(put("/api/comercios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comerciosDTO)))
            .andExpect(status().isOk());

        // Validate the Comercios in the database
        List<Comercios> comerciosList = comerciosRepository.findAll();
        assertThat(comerciosList).hasSize(databaseSizeBeforeUpdate);
        Comercios testComercios = comerciosList.get(comerciosList.size() - 1);
        assertThat(testComercios.getComerciosName()).isEqualTo(UPDATED_COMERCIOS_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingComercios() throws Exception {
        int databaseSizeBeforeUpdate = comerciosRepository.findAll().size();

        // Create the Comercios
        ComerciosDTO comerciosDTO = comerciosMapper.toDto(comercios);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restComerciosMockMvc.perform(put("/api/comercios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comerciosDTO)))
            .andExpect(status().isCreated());

        // Validate the Comercios in the database
        List<Comercios> comerciosList = comerciosRepository.findAll();
        assertThat(comerciosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteComercios() throws Exception {
        // Initialize the database
        comerciosRepository.saveAndFlush(comercios);
        int databaseSizeBeforeDelete = comerciosRepository.findAll().size();

        // Get the comercios
        restComerciosMockMvc.perform(delete("/api/comercios/{id}", comercios.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Comercios> comerciosList = comerciosRepository.findAll();
        assertThat(comerciosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Comercios.class);
        Comercios comercios1 = new Comercios();
        comercios1.setId(1L);
        Comercios comercios2 = new Comercios();
        comercios2.setId(comercios1.getId());
        assertThat(comercios1).isEqualTo(comercios2);
        comercios2.setId(2L);
        assertThat(comercios1).isNotEqualTo(comercios2);
        comercios1.setId(null);
        assertThat(comercios1).isNotEqualTo(comercios2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComerciosDTO.class);
        ComerciosDTO comerciosDTO1 = new ComerciosDTO();
        comerciosDTO1.setId(1L);
        ComerciosDTO comerciosDTO2 = new ComerciosDTO();
        assertThat(comerciosDTO1).isNotEqualTo(comerciosDTO2);
        comerciosDTO2.setId(comerciosDTO1.getId());
        assertThat(comerciosDTO1).isEqualTo(comerciosDTO2);
        comerciosDTO2.setId(2L);
        assertThat(comerciosDTO1).isNotEqualTo(comerciosDTO2);
        comerciosDTO1.setId(null);
        assertThat(comerciosDTO1).isNotEqualTo(comerciosDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(comerciosMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(comerciosMapper.fromId(null)).isNull();
    }
}
