package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ComerciosService;
import io.github.jhipster.application.domain.Comercios;
import io.github.jhipster.application.repository.ComerciosRepository;
import io.github.jhipster.application.service.dto.ComerciosDTO;
import io.github.jhipster.application.service.mapper.ComerciosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Comercios.
 */
@Service
@Transactional
public class ComerciosServiceImpl implements ComerciosService {

    private final Logger log = LoggerFactory.getLogger(ComerciosServiceImpl.class);

    private final ComerciosRepository comerciosRepository;

    private final ComerciosMapper comerciosMapper;

    public ComerciosServiceImpl(ComerciosRepository comerciosRepository, ComerciosMapper comerciosMapper) {
        this.comerciosRepository = comerciosRepository;
        this.comerciosMapper = comerciosMapper;
    }

    /**
     * Save a comercios.
     *
     * @param comerciosDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ComerciosDTO save(ComerciosDTO comerciosDTO) {
        log.debug("Request to save Comercios : {}", comerciosDTO);
        Comercios comercios = comerciosMapper.toEntity(comerciosDTO);
        comercios = comerciosRepository.save(comercios);
        return comerciosMapper.toDto(comercios);
    }

    /**
     * Get all the comercios.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComerciosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comercios");
        return comerciosRepository.findAll(pageable)
            .map(comerciosMapper::toDto);
    }

    /**
     * Get one comercios by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ComerciosDTO findOne(Long id) {
        log.debug("Request to get Comercios : {}", id);
        Comercios comercios = comerciosRepository.findOne(id);
        return comerciosMapper.toDto(comercios);
    }

    /**
     * Delete the comercios by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comercios : {}", id);
        comerciosRepository.delete(id);
    }
}
