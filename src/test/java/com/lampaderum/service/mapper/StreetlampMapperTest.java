package com.lampaderum.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StreetlampMapperTest {

    private StreetlampMapper streetlampMapper;

    @BeforeEach
    public void setUp() {
        streetlampMapper = new StreetlampMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(streetlampMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(streetlampMapper.fromId(null)).isNull();
    }
}
