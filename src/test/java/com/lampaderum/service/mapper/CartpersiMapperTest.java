package com.lampaderum.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CartpersiMapperTest {

    private CartpersiMapper cartpersiMapper;

    @BeforeEach
    public void setUp() {
        cartpersiMapper = new CartpersiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cartpersiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cartpersiMapper.fromId(null)).isNull();
    }
}
