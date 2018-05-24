package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ComerciosDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Comercios.
 */
public interface ComerciosService {

    /**
     * Save a comercios.
     *
     * @param comerciosDTO the entity to save
     * @return the persisted entity
     */
    ComerciosDTO save(ComerciosDTO comerciosDTO);

    /**
     * Get all the comercios.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ComerciosDTO> findAll(Pageable pageable);

    /**
     * Get the "id" comercios.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ComerciosDTO findOne(Long id);

    /**
     * Delete the "id" comercios.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
