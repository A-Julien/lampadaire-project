package com.lampaderum.service;

import com.lampaderum.domain.ApplicationUser;
import com.lampaderum.repository.ApplicationUserRepository;
import com.lampaderum.service.dto.ApplicationUserDTO;
import com.lampaderum.service.mapper.ApplicationUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Service Implementation for managing {@link ApplicationUser}.
 */
@Service
@Transactional
public class ApplicationUserService {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserService.class);

    private final ApplicationUserRepository applicationUserRepository;

    private final ApplicationUserMapper applicationUserMapper;

    public ApplicationUserService(ApplicationUserRepository applicationUserRepository, ApplicationUserMapper applicationUserMapper) {
        this.applicationUserRepository = applicationUserRepository;
        this.applicationUserMapper = applicationUserMapper;
    }

    /**
     * Save a applicationUser.
     *
     * @param applicationUserDTO the entity to save.
     * @return the persisted entity.
     */
    public ApplicationUserDTO save(ApplicationUserDTO applicationUserDTO) {
        log.debug("Request to save ApplicationUser : {}", applicationUserDTO);
        ApplicationUser applicationUser = applicationUserMapper.toEntity(applicationUserDTO);
        applicationUser = applicationUserRepository.save(applicationUser);
        return applicationUserMapper.toDto(applicationUser);
    }

    /**
     * Get all the applicationUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ApplicationUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApplicationUsers");
        return applicationUserRepository.findAll(pageable)
            .map(applicationUserMapper::toDto);
    }


    /**
     * Get one applicationUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ApplicationUserDTO> findOne(Long id) {
        log.debug("Request to get ApplicationUser : {}", id);
        return applicationUserRepository.findById(id)
            .map(applicationUserMapper::toDto);
    }

    /**
     * Get one applicationUser by userID.
     *
     * @param id the id of the user.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ApplicationUserDTO> findOneByUserId(Long id) {

        log.debug("Request to get ApplicationUser by UserId : {}", id);
        Long plop = null;
        Stream<ApplicationUserDTO> app = applicationUserRepository.findAll().stream().map(applicationUserMapper::toDto);
        Object[] plip= app.toArray();
        for (int i=0;i<plip.length;i++)
        {
            if (id==((ApplicationUserDTO) plip[i]).getUserId())
            {
                plop=((ApplicationUserDTO) plip[i]).getId();
            }
        }
        return applicationUserRepository.findById(plop)
            .map(applicationUserMapper::toDto);
    }

    /**
     * Delete the applicationUser by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ApplicationUser : {}", id);
        applicationUserRepository.deleteById(id);
    }
}
