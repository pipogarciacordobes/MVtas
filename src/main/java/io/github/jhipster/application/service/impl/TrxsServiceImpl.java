package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.TrxsService;
import io.github.jhipster.application.domain.Trxs;
import io.github.jhipster.application.repository.TrxsRepository;
import io.github.jhipster.application.service.dto.TrxsDTO;
import io.github.jhipster.application.service.mapper.TrxsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Trxs.
 */
@Service
@Transactional
public class TrxsServiceImpl implements TrxsService {

    private final Logger log = LoggerFactory.getLogger(TrxsServiceImpl.class);

    private final TrxsRepository trxsRepository;

    private final TrxsMapper trxsMapper;

    public TrxsServiceImpl(TrxsRepository trxsRepository, TrxsMapper trxsMapper) {
        this.trxsRepository = trxsRepository;
        this.trxsMapper = trxsMapper;
    }

    /**
     * Save a trxs.
     *
     * @param trxsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TrxsDTO save(TrxsDTO trxsDTO) {
        log.debug("Request to save Trxs : {}", trxsDTO);
        Trxs trxs = trxsMapper.toEntity(trxsDTO);
        trxs = trxsRepository.save(trxs);
        return trxsMapper.toDto(trxs);
    }

    /**
     * Get all the trxs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TrxsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Trxs");
        return trxsRepository.findAll(pageable)
            .map(trxsMapper::toDto);
    }

    /**
     * Get one trxs by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TrxsDTO findOne(Long id) {
        log.debug("Request to get Trxs : {}", id);
        Trxs trxs = trxsRepository.findOne(id);
        return trxsMapper.toDto(trxs);
    }

    /**
     * Delete the trxs by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Trxs : {}", id);
        trxsRepository.delete(id);
    }
}
