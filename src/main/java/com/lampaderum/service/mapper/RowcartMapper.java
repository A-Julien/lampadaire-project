package com.lampaderum.service.mapper;


import com.lampaderum.domain.*;
import com.lampaderum.service.dto.RowcartDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Rowcart} and its DTO {@link RowcartDTO}.
 */
@Mapper(componentModel = "spring", uses = {StreetlampMapper.class, CartpersiMapper.class})
public interface RowcartMapper extends EntityMapper<RowcartDTO, Rowcart> {

    @Mapping(source = "streetlamp.id", target = "streetlampId")
    @Mapping(source = "cartpersi.id", target = "cartpersiId")
    RowcartDTO toDto(Rowcart rowcart);

    @Mapping(source = "streetlampId", target = "streetlamp")
    @Mapping(source = "cartpersiId", target = "cartpersi")
    Rowcart toEntity(RowcartDTO rowcartDTO);

    default Rowcart fromId(Long id) {
        if (id == null) {
            return null;
        }
        Rowcart rowcart = new Rowcart();
        rowcart.setId(id);
        return rowcart;
    }
}
