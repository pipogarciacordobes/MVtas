package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TrxsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Trxs.
 */
public interface TrxsService {

    /**
     * Save a trxs.
     *
     * @param trxsDTO the entity to save
     * @return the persisted entity
     */
    TrxsDTO save(TrxsDTO trxsDTO);

    /**
     * Get all the trxs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TrxsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" trxs.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TrxsDTO findOne(Long id);

    /**
     * Delete the "id" trxs.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
