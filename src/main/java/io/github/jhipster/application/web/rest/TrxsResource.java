package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.TrxsService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.TrxsDTO;
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
 * REST controller for managing Trxs.
 */
@RestController
@RequestMapping("/api")
public class TrxsResource {

    private final Logger log = LoggerFactory.getLogger(TrxsResource.class);

    private static final String ENTITY_NAME = "trxs";

    private final TrxsService trxsService;

    public TrxsResource(TrxsService trxsService) {
        this.trxsService = trxsService;
    }

    /**
     * POST  /trxs : Create a new trxs.
     *
     * @param trxsDTO the trxsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trxsDTO, or with status 400 (Bad Request) if the trxs has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trxs")
    @Timed
    public ResponseEntity<TrxsDTO> createTrxs(@RequestBody TrxsDTO trxsDTO) throws URISyntaxException {
        log.debug("REST request to save Trxs : {}", trxsDTO);
        if (trxsDTO.getId() != null) {
            throw new BadRequestAlertException("A new trxs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrxsDTO result = trxsService.save(trxsDTO);
        return ResponseEntity.created(new URI("/api/trxs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trxs : Updates an existing trxs.
     *
     * @param trxsDTO the trxsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trxsDTO,
     * or with status 400 (Bad Request) if the trxsDTO is not valid,
     * or with status 500 (Internal Server Error) if the trxsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trxs")
    @Timed
    public ResponseEntity<TrxsDTO> updateTrxs(@RequestBody TrxsDTO trxsDTO) throws URISyntaxException {
        log.debug("REST request to update Trxs : {}", trxsDTO);
        if (trxsDTO.getId() == null) {
            return createTrxs(trxsDTO);
        }
        TrxsDTO result = trxsService.save(trxsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trxsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trxs : get all the trxs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of trxs in body
     */
    @GetMapping("/trxs")
    @Timed
    public ResponseEntity<List<TrxsDTO>> getAllTrxs(Pageable pageable) {
        log.debug("REST request to get a page of Trxs");
        Page<TrxsDTO> page = trxsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/trxs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /trxs/:id : get the "id" trxs.
     *
     * @param id the id of the trxsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trxsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/trxs/{id}")
    @Timed
    public ResponseEntity<TrxsDTO> getTrxs(@PathVariable Long id) {
        log.debug("REST request to get Trxs : {}", id);
        TrxsDTO trxsDTO = trxsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(trxsDTO));
    }

    /**
     * DELETE  /trxs/:id : delete the "id" trxs.
     *
     * @param id the id of the trxsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trxs/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrxs(@PathVariable Long id) {
        log.debug("REST request to delete Trxs : {}", id);
        trxsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
