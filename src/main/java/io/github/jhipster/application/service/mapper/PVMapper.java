package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PVDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PV and its DTO PVDTO.
 */
@Mapper(componentModel = "spring", uses = {TrxsMapper.class})
public interface PVMapper extends EntityMapper<PVDTO, PV> {

    @Mapping(source = "trxs.id", target = "trxsId")
    PVDTO toDto(PV pV);

    @Mapping(target = "idComercios", ignore = true)
    @Mapping(source = "trxsId", target = "trxs")
    PV toEntity(PVDTO pVDTO);

    default PV fromId(Long id) {
        if (id == null) {
            return null;
        }
        PV pV = new PV();
        pV.setId(id);
        return pV;
    }
}
