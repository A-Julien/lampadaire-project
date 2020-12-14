package com.lampaderum.service.mapper;


import com.lampaderum.domain.*;
import com.lampaderum.service.dto.CreditcardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Creditcard} and its DTO {@link CreditcardDTO}.
 */
@Mapper(componentModel = "spring", uses = {ApplicationUserMapper.class})
public interface CreditcardMapper extends EntityMapper<CreditcardDTO, Creditcard> {

    @Mapping(source = "applicationUser.id", target = "applicationUserId")
    CreditcardDTO toDto(Creditcard creditcard);

    @Mapping(source = "applicationUserId", target = "applicationUser")
    Creditcard toEntity(CreditcardDTO creditcardDTO);

    default Creditcard fromId(Long id) {
        if (id == null) {
            return null;
        }
        Creditcard creditcard = new Creditcard();
        creditcard.setId(id);
        return creditcard;
    }
}
