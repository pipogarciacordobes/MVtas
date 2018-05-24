package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.PVDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing PV.
 */
public interface PVService {

    /**
     * Save a pV.
     *
     * @param pVDTO the entity to save
     * @return the persisted entity
     */
    PVDTO save(PVDTO pVDTO);

    /**
     * Get all the pVS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PVDTO> findAll(Pageable pageable);

    /**
     * Get the "id" pV.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PVDTO findOne(Long id);

    /**
     * Delete the "id" pV.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
