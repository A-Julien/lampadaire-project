package com.lampaderum.service.mapper;


import com.lampaderum.domain.*;
import com.lampaderum.service.dto.RoworderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Roworder} and its DTO {@link RoworderDTO}.
 */
@Mapper(componentModel = "spring", uses = {StreetlampMapper.class})
public interface RoworderMapper extends EntityMapper<RoworderDTO, Roworder> {

    @Mapping(source = "streetlamp.id", target = "streetlampId")
    RoworderDTO toDto(Roworder roworder);

    @Mapping(source = "streetlampId", target = "streetlamp")
    Roworder toEntity(RoworderDTO roworderDTO);

    default Roworder fromId(Long id) {
        if (id == null) {
            return null;
        }
        Roworder roworder = new Roworder();
        roworder.setId(id);
        return roworder;
    }
}
