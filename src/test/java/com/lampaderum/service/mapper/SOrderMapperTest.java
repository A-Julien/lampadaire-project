package com.lampaderum.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SOrderMapperTest {

    private SOrderMapper sOrderMapper;

    @BeforeEach
    public void setUp() {
        sOrderMapper = new SOrderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(sOrderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sOrderMapper.fromId(null)).isNull();
    }
}
