package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.ComerciosService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.ComerciosDTO;
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
 * REST controller for managing Comercios.
 */
@RestController
@RequestMapping("/api")
public class ComerciosResource {

    private final Logger log = LoggerFactory.getLogger(ComerciosResource.class);

    private static final String ENTITY_NAME = "comercios";

    private final ComerciosService comerciosService;

    public ComerciosResource(ComerciosService comerciosService) {
        this.comerciosService = comerciosService;
    }

    /**
     * POST  /comercios : Create a new comercios.
     *
     * @param comerciosDTO the comerciosDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new comerciosDTO, or with status 400 (Bad Request) if the comercios has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/comercios")
    @Timed
    public ResponseEntity<ComerciosDTO> createComercios(@RequestBody ComerciosDTO comerciosDTO) throws URISyntaxException {
        log.debug("REST request to save Comercios : {}", comerciosDTO);
        if (comerciosDTO.getId() != null) {
            throw new BadRequestAlertException("A new comercios cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComerciosDTO result = comerciosService.save(comerciosDTO);
        return ResponseEntity.created(new URI("/api/comercios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /comercios : Updates an existing comercios.
     *
     * @param comerciosDTO the comerciosDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated comerciosDTO,
     * or with status 400 (Bad Request) if the comerciosDTO is not valid,
     * or with status 500 (Internal Server Error) if the comerciosDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/comercios")
    @Timed
    public ResponseEntity<ComerciosDTO> updateComercios(@RequestBody ComerciosDTO comerciosDTO) throws URISyntaxException {
        log.debug("REST request to update Comercios : {}", comerciosDTO);
        if (comerciosDTO.getId() == null) {
            return createComercios(comerciosDTO);
        }
        ComerciosDTO result = comerciosService.save(comerciosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, comerciosDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /comercios : get all the comercios.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of comercios in body
     */
    @GetMapping("/comercios")
    @Timed
    public ResponseEntity<List<ComerciosDTO>> getAllComercios(Pageable pageable) {
        log.debug("REST request to get a page of Comercios");
        Page<ComerciosDTO> page = comerciosService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comercios");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /comercios/:id : get the "id" comercios.
     *
     * @param id the id of the comerciosDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the comerciosDTO, or with status 404 (Not Found)
     */
    @GetMapping("/comercios/{id}")
    @Timed
    public ResponseEntity<ComerciosDTO> getComercios(@PathVariable Long id) {
        log.debug("REST request to get Comercios : {}", id);
        ComerciosDTO comerciosDTO = comerciosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(comerciosDTO));
    }

    /**
     * DELETE  /comercios/:id : delete the "id" comercios.
     *
     * @param id the id of the comerciosDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/comercios/{id}")
    @Timed
    public ResponseEntity<Void> deleteComercios(@PathVariable Long id) {
        log.debug("REST request to delete Comercios : {}", id);
        comerciosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
