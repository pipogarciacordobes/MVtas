package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.PVService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.PVDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PV.
 */
@RestController
@RequestMapping("/api")
public class PVResource {

    private final Logger log = LoggerFactory.getLogger(PVResource.class);

    private static final String ENTITY_NAME = "pV";

    private final PVService pVService;

    public PVResource(PVService pVService) {
        this.pVService = pVService;
    }

    /**
     * POST  /pvs : Create a new pV.
     *
     * @param pVDTO the pVDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pVDTO, or with status 400 (Bad Request) if the pV has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pvs")
    @Timed
    public ResponseEntity<PVDTO> createPV(@RequestBody PVDTO pVDTO) throws URISyntaxException {
        log.debug("REST request to save PV : {}", pVDTO);
        if (pVDTO.getId() != null) {
            throw new BadRequestAlertException("A new pV cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PVDTO result = pVService.save(pVDTO);
        return ResponseEntity.created(new URI("/api/pvs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pvs : Updates an existing pV.
     *
     * @param pVDTO the pVDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pVDTO,
     * or with status 400 (Bad Request) if the pVDTO is not valid,
     * or with status 500 (Internal Server Error) if the pVDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pvs")
    @Timed
    public ResponseEntity<PVDTO> updatePV(@RequestBody PVDTO pVDTO) throws URISyntaxException {
        log.debug("REST request to update PV : {}", pVDTO);
        if (pVDTO.getId() == null) {
            return createPV(pVDTO);
        }
        PVDTO result = pVService.save(pVDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pVDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pvs : get all the pVS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of pVS in body
     */
    @GetMapping("/pvs")
    @Timed
    public ResponseEntity<List<PVDTO>> getAllPVS(Pageable pageable) {
        log.debug("REST request to get a page of PVS");
        Page<PVDTO> page = pVService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pvs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pvs/:id : get the "id" pV.
     *
     * @param id the id of the pVDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pVDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pvs/{id}")
    @Timed
    public ResponseEntity<PVDTO> getPV(@PathVariable Long id) {
        log.debug("REST request to get PV : {}", id);
        PVDTO pVDTO = pVService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pVDTO));
    }

    /**
     * DELETE  /pvs/:id : delete the "id" pV.
     *
     * @param id the id of the pVDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pvs/{id}")
    @Timed
    public ResponseEntity<Void> deletePV(@PathVariable Long id) {
        log.debug("REST request to delete PV : {}", id);
        pVService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
