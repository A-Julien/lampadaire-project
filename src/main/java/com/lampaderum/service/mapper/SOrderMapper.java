package com.lampaderum.service.mapper;


import com.lampaderum.domain.*;
import com.lampaderum.service.dto.SOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SOrder} and its DTO {@link SOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {ApplicationUserMapper.class})
public interface SOrderMapper extends EntityMapper<SOrderDTO, SOrder> {

    @Mapping(source = "applicationUser.id", target = "applicationUserId")
    SOrderDTO toDto(SOrder sOrder);

    @Mapping(target = "roworders", ignore = true)
    @Mapping(target = "removeRoworder", ignore = true)
    @Mapping(source = "applicationUserId", target = "applicationUser")
    SOrder toEntity(SOrderDTO sOrderDTO);

    default SOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        SOrder sOrder = new SOrder();
        sOrder.setId(id);
        return sOrder;
    }
}
