package com.lampaderum.service.mapper;


import com.lampaderum.domain.*;
import com.lampaderum.service.dto.CartpersiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cartpersi} and its DTO {@link CartpersiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CartpersiMapper extends EntityMapper<CartpersiDTO, Cartpersi> {


    @Mapping(target = "rowcarts", ignore = true)
    @Mapping(target = "removeRowcart", ignore = true)
    Cartpersi toEntity(CartpersiDTO cartpersiDTO);

    default Cartpersi fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cartpersi cartpersi = new Cartpersi();
        cartpersi.setId(id);
        return cartpersi;
    }
}
