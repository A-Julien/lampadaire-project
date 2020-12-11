package com.lampaderum.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RowcartMapperTest {

    private RowcartMapper rowcartMapper;

    @BeforeEach
    public void setUp() {
        rowcartMapper = new RowcartMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(rowcartMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rowcartMapper.fromId(null)).isNull();
    }
}
