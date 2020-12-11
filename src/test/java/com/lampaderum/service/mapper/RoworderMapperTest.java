package com.lampaderum.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RoworderMapperTest {

    private RoworderMapper roworderMapper;

    @BeforeEach
    public void setUp() {
        roworderMapper = new RoworderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(roworderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(roworderMapper.fromId(null)).isNull();
    }
}
