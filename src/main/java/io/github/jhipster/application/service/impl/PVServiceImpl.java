package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.PVService;
import io.github.jhipster.application.domain.PV;
import io.github.jhipster.application.repository.PVRepository;
import io.github.jhipster.application.service.dto.PVDTO;
import io.github.jhipster.application.service.mapper.PVMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing PV.
 */
@Service
@Transactional
public class PVServiceImpl implements PVService {

    private final Logger log = LoggerFactory.getLogger(PVServiceImpl.class);

    private final PVRepository pVRepository;

    private final PVMapper pVMapper;

    public PVServiceImpl(PVRepository pVRepository, PVMapper pVMapper) {
        this.pVRepository = pVRepository;
        this.pVMapper = pVMapper;
    }

    /**
     * Save a pV.
     *
     * @param pVDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PVDTO save(PVDTO pVDTO) {
        log.debug("Request to save PV : {}", pVDTO);
        PV pV = pVMapper.toEntity(pVDTO);
        pV = pVRepository.save(pV);
        return pVMapper.toDto(pV);
    }

    /**
     * Get all the pVS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PVDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PVS");
        return pVRepository.findAll(pageable)
            .map(pVMapper::toDto);
    }

    /**
     * Get one pV by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PVDTO findOne(Long id) {
        log.debug("Request to get PV : {}", id);
        PV pV = pVRepository.findOne(id);
        return pVMapper.toDto(pV);
    }

    /**
     * Delete the pV by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PV : {}", id);
        pVRepository.delete(id);
    }
}
