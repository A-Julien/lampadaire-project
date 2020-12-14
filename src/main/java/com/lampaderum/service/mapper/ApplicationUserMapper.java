package com.lampaderum.service.mapper;


import com.lampaderum.domain.*;
import com.lampaderum.service.dto.ApplicationUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ApplicationUser} and its DTO {@link ApplicationUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CartpersiMapper.class})
public interface ApplicationUserMapper extends EntityMapper<ApplicationUserDTO, ApplicationUser> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "cartpersi.id", target = "cartpersiId")
    ApplicationUserDTO toDto(ApplicationUser applicationUser);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "sorders", ignore = true)
    @Mapping(target = "removeSorder", ignore = true)
    @Mapping(target = "creditcards", ignore = true)
    @Mapping(target = "removeCreditcard", ignore = true)
    @Mapping(source = "cartpersiId", target = "cartpersi")
    ApplicationUser toEntity(ApplicationUserDTO applicationUserDTO);

    default ApplicationUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId(id);
        return applicationUser;
    }
}
