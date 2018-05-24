package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Trxs;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Trxs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrxsRepository extends JpaRepository<Trxs, Long> {

}
