package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Comercios;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Comercios entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComerciosRepository extends JpaRepository<Comercios, Long> {

}
