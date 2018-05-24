package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TrxsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Trxs and its DTO TrxsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TrxsMapper extends EntityMapper<TrxsDTO, Trxs> {


    @Mapping(target = "idPVS", ignore = true)
    Trxs toEntity(TrxsDTO trxsDTO);

    default Trxs fromId(Long id) {
        if (id == null) {
            return null;
        }
        Trxs trxs = new Trxs();
        trxs.setId(id);
        return trxs;
    }
}
