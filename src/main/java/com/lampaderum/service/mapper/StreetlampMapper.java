package com.lampaderum.service.mapper;


import com.lampaderum.domain.*;
import com.lampaderum.service.dto.StreetlampDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Streetlamp} and its DTO {@link StreetlampDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StreetlampMapper extends EntityMapper<StreetlampDTO, Streetlamp> {



    default Streetlamp fromId(Long id) {
        if (id == null) {
            return null;
        }
        Streetlamp streetlamp = new Streetlamp();
        streetlamp.setId(id);
        return streetlamp;
    }
}
