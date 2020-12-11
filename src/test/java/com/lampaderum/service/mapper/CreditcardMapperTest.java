package com.lampaderum.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CreditcardMapperTest {

    private CreditcardMapper creditcardMapper;

    @BeforeEach
    public void setUp() {
        creditcardMapper = new CreditcardMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(creditcardMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(creditcardMapper.fromId(null)).isNull();
    }
}
