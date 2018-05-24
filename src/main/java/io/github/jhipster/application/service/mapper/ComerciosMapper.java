package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ComerciosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Comercios and its DTO ComerciosDTO.
 */
@Mapper(componentModel = "spring", uses = {PVMapper.class})
public interface ComerciosMapper extends EntityMapper<ComerciosDTO, Comercios> {

    @Mapping(source = "pV.id", target = "pVId")
    ComerciosDTO toDto(Comercios comercios);

    @Mapping(source = "pVId", target = "pV")
    Comercios toEntity(ComerciosDTO comerciosDTO);

    default Comercios fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comercios comercios = new Comercios();
        comercios.setId(id);
        return comercios;
    }
}
